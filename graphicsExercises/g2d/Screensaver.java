package deitel.graphicsExercises.g2d;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Screensaver extends JPanel implements ActionListener {
	private int numberOfLines;
	private Random ran = new Random ();
	private int currentNumberOfLines;

	public Screensaver (int numberOfLines) {
		setNumberOfLines (numberOfLines);
		currentNumberOfLines = 0;
		Timer timer = new Timer (1000, this);
		timer.start ();
		
		final JTextField TF = new JTextField (3);
		add (new JLabel ("Number of shapes"));
		add (TF);
		TF.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent ae) {
				setNumberOfLines (Integer.parseInt (TF.getText ()));
			}
		});
	}
	
	public final void setNumberOfLines (int newNumberOfLines) {
		 numberOfLines = newNumberOfLines < 0 ? 0 : newNumberOfLines;
	}
	
	public final int getNumberOfLines () {
		return numberOfLines;
	}

	public void paintComponent (Graphics g) {
		if (++currentNumberOfLines <= numberOfLines) {
			int x = ran.nextInt (getWidth () + 1);
			int y = ran.nextInt (getHeight () + 1);
			g.setColor (new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)));
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint (new GradientPaint (x,
														y,
														g.getColor (),
														ran.nextInt (getWidth ()),
														ran.nextInt (getHeight ()),
														new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)),
														ran.nextBoolean ()));
			
			switch (ran.nextInt (4)) {
				case 0://Line
					g.drawLine (x, y, ran.nextInt (getWidth () + 1), ran.nextInt (getHeight () + 1));
					break;
				
				case 1://Cirlce
					Ellipse2D ellipse = new Ellipse2D.Double (x,
																			y,
																			ran.nextInt (getWidth () - x),
																			ran.nextInt (getHeight () - y));
					
					if (ran.nextBoolean ())
						g2d.draw (ellipse);
					else
						g2d.fill (ellipse);
					break;
				
				case 2://Rect
					Rectangle2D rectangle = new Rectangle2D.Double (x,
																					y,
																					ran.nextInt (getWidth () - x),
																					ran.nextInt (getHeight () - y));
				
					if (ran.nextBoolean ())
						g2d.draw (rectangle);
					else
						g2d.fill (rectangle);
					break;
				
				case 3://Polygon
					byte end = (byte) ran.nextInt (12);
					int[] pointsX = new int[end];
					int[] pointsY = new int[end];
				
					for (byte j = 0; j < end; ++j) {
						pointsX[j] = ran.nextInt (getWidth () + 1);
						pointsY[j] = ran.nextInt (getHeight () + 1);
					}
					
					if (ran.nextBoolean ())
						((Graphics2D) g).draw (new Polygon (pointsX, pointsY, pointsX.length));
					else
						((Graphics2D) g).fill (new Polygon (pointsX, pointsY, pointsX.length));
			}
		} else {
			currentNumberOfLines = 0;
			super.paintComponent (g);
		}
	}
	
	public void actionPerformed (ActionEvent ae) {
		repaint ();
	}
}
