
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Excavator {

	/**
	 * Test code.
	 */

	public static void main(String[] args) {		
		JFrame f = new JFrame();
		SpriteCanvas canvas = new SpriteCanvas();
		canvas.addSprite(Excavator.makeSprite());


		f.setJMenuBar(Excavator.makeMenuBar(canvas));
		f.getContentPane().add(canvas);
		f.getContentPane().setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	/* Make a sample sprite for testing purposes. */
	private static Sprite makeSprite() {

		Sprite secondArm = new Boom();
		Sprite firstArm = new Body();
		Sprite thirdArm = new Arm();
		Sprite fourthArm = new Bucket();
/*
		Sprite fifthArm = new RectangleSprite(50, 50);

		firstArm.transform(AffineTransform.getTranslateInstance(250, 250));
		secondArm.transform(AffineTransform.getTranslateInstance(-30, 100));


		thirdArm.transform(AffineTransform.getTranslateInstance(-150, -100));
		thirdArm.transform(AffineTransform.getRotateInstance(Math.PI / 180 * 30));

		fourthArm.transform(AffineTransform.getTranslateInstance(20, 0));
		//fourthArm.transform(AffineTransform.getScaleInstance(4, 3));
		fourthArm.transform(AffineTransform.getRotateInstance(Math.PI / 180 * 90));
		fifthArm.transform(AffineTransform.getTranslateInstance(90, -30));
		//fifthArm.transform(AffineTransform.getScaleInstance(4, 3));

		firstArm.addChild(secondArm);
		firstArm.addChild(thirdArm);
		thirdArm.addChild(fourthArm);
		fourthArm.addChild(fifthArm);
		*/
		firstArm.addChild(secondArm);
		secondArm.addChild(thirdArm);
		thirdArm.addChild(fourthArm);
		return firstArm;
	}

	/* Menu with recording and playback. */
	private static JMenuBar makeMenuBar(final SpriteCanvas canvas) {
		JMenuBar mbar = new JMenuBar();
		JMenu script1= new JMenu("File");
		JMenu script = new JMenu("Scripting");
		final JMenuItem record = new JMenuItem("Start recording");
		final JMenuItem play = new JMenuItem("Start script");

		final JMenuItem exit = new JMenuItem("quit");
		final JMenuItem reset = new JMenuItem("reset");
		script.add(record);
		script.add(play);
		script1.add(reset);
		script1.add(exit);
		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (record.getText().equals("Start recording")) {
					record.setText("Stop recording");
					canvas.startRecording();
				} else if (record.getText().equals("Stop recording")) {
					record.setText("Start recording");
					canvas.stopRecording();
				} else {
					assert false;
				}
			}
		});

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (play.getText().equals("Start script")) {
					play.setText("Stop script");
					record.setEnabled(false);
					canvas.startDemo();
				} else if (play.getText().equals("Stop script")) {
					play.setText("Start script");
					record.setEnabled(true);
					canvas.stopRecording();
				} else {
					assert false;
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			dispose();
				JFrame f = new JFrame();
		f.setJMenuBar(Excavator.makeMenuBar(canvas));
		f.getContentPane().add(canvas);
		f.getContentPane().setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setVisible(true);
			}
		});
		mbar.add(script1);
		mbar.add(script);

		return mbar;
	}

}
