

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.GeneralPath;

/**
 * A simple demo of how to create a rectangular sprite.
 * 
 * Michael Terry
 */
public class Bucket extends Sprite {
    private GeneralPath path = new GeneralPath();
    /**
     * Creates a rectangle based at the origin with the specified
     * width and height
     */
    public Bucket() {
        super(4, 113, 218);
        this.initialize();
    }
    /**
     * Creates a rectangle based at the origin with the specified
     * width, height, and parent
     */
    public Bucket(Sprite parentSprite) {
        super(parentSprite);
        this.initialize();
    }
    
    private void initialize() {
	int[] xPoints = {115, 95, 145, 165};
        int[] yPoints = {200, 210, 250, 240};
	path.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            path.lineTo(xPoints[i], yPoints[i]);
        }
	path.closePath();
    }
    
    /**
     * Test if our rectangle contains the point specified.
     */
    public boolean pointInside(Point2D p) {
        AffineTransform fullTransform = this.getFullTransform();
        AffineTransform inverseTransform = null;
        try {
            inverseTransform = fullTransform.createInverse();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        Point2D newPoint = (Point2D)p.clone();
        inverseTransform.transform(newPoint, newPoint);
        return path.contains(newPoint);
    }

    protected void drawSprite(Graphics2D g) {
        g.setColor(Color.ORANGE);
	g.fill(path);
        g.draw(path);
	g.setColor(Color.BLACK);
	g.draw(path);
        //g.draw(this.getFullTransform().createTransformedShape(rect));
    }
    
    public String toString() {
        return "boomSprite: " + path;
    }
}
