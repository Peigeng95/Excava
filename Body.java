

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
public class Body extends Sprite {
    private GeneralPath path = new GeneralPath();
    private GeneralPath pathwindow = new GeneralPath();
    private Rectangle2D rect = new Rectangle2D.Double(230, 300, 250, 30);
    /**
     * Creates a rectangle based at the origin with the specified
     * width and height
     */
    public Body() {
        super(1,0,0);
        this.initialize();
    }
    /**
     * Creates a rectangle based at the origin with the specified
     * width, height, and parent
     */
    public Body(Sprite parentSprite) {
        super(parentSprite);
        this.initialize();
    }
    
    private void initialize() {
	int[] xPoints = {300, 350, 350, 450, 450, 250, 250};
        int[] yPoints = {200, 200, 250, 250, 300, 300, 270};
	int[] xwindow = {300, 335, 335, 265, 265};
	int[] ywindow = {215, 215, 285, 285, 270};
	path.moveTo(xPoints[0], yPoints[0]);
	pathwindow.moveTo(xwindow[0], ywindow[0]);
        for (int i = 1; i < xPoints.length; i++) {
            path.lineTo(xPoints[i], yPoints[i]);
        }
	for (int i = 1; i < xwindow.length; i++) {
	    pathwindow.lineTo(xwindow[i], ywindow[i]);
	}
	path.closePath();
	pathwindow.closePath();
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
        return (path.contains(newPoint) || rect.contains(newPoint));
    }

    protected void drawSprite(Graphics2D g) {
	g.draw(path);
        g.setColor(Color.ORANGE);
	g.fill(path);
        g.draw(path);
	g.setColor(Color.WHITE);
	g.fill(pathwindow);
	g.draw(pathwindow);
	g.setColor(Color.BLACK);
	g.draw(path);
	g.draw(pathwindow);
	g.fill(rect);
	g.draw(rect);
        //g.draw(this.getFullTransform().createTransformedShape(rect));
    }
    
    public String toString() {
        return "bodySprite: " + path;
    }
}
