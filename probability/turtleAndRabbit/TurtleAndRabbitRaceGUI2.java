package deitel.probability.turtleAndRabbit;//2917136

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TurtleAndRabbitRaceGUI2 extends JPanel implements ActionListener {
	private Rabbit rbt;
	private Turtle trtl;
	private Timer timer;

	public TurtleAndRabbitRaceGUI2 () {
		rbt = new Rabbit ();
		trtl = new Turtle ();
		timer = new Timer (500, this);
		timer.start ();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		rbt.makeArandomMove ();
		trtl.makeArandomMove ();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke (new BasicStroke (5.0f));
		g.setColor (Color.GREEN);
		g2d.fill (new Arc2D.Double (0, 0, getWidth (), getHeight () * 2, 0, 180, Arc2D.OPEN));
		
		for (byte j = 1; j <= 70; ++j) {
			if (rbt.position == j) {
				g2d.setPaint (new GradientPaint (getWidth () / 2 - 25,
															getHeight () - j * getHeight () / 70,
															Color.LIGHT_GRAY,
															getWidth () / 2 + 25,
															getHeight () - j * getHeight () / 70 + 50,
															Color.WHITE,
															false));

				g2d.fill (new Ellipse2D.Double (getWidth () / 2 - 25, getHeight () - j * (getHeight () / 70), 50, 50));
				g.setColor (Color.BLACK);
				g.drawString ("Rabbit", getWidth () / 2 - 18, getHeight () - j * getHeight () / 70 + 30);
			}
			
			if (trtl.position == j) {
				g2d.setPaint (new GradientPaint (getWidth () / 2 - 25,
															getHeight () - j * getHeight () / 70,
															new Color (102, 51, 0),
															getWidth () / 2 + 25,
															getHeight () - j * getHeight () / 70 + 50,
															Color.WHITE,
															false));
															
				g2d.fill (new Rectangle2D.Double (getWidth () / 2 - 25, getHeight () - j * getHeight () / 70, 50, 50));
				g.setColor (Color.BLACK);
				g.drawString ("Turtle", getWidth () / 2 - 18, getHeight () - j * getHeight () / 70 + 30);
			}
		}
		
		String results = "";
		if (rbt.position > 69 && trtl.position > 69) {
			results = "This is a draw";
			timer.stop ();
		} else if (rbt.position > 69 || trtl.position > 69) {
			results = "The winner is the ";
			results += rbt.position > 69 ? "rabbit" : "turtle";
			timer.stop ();
		}
		
		g.drawString (results, getWidth () / 2 - 100, getHeight () / 2 - 100);
	}

	public void actionPerformed (ActionEvent ae) {
		repaint ();
	}

	public static void main (String args[]) {
		JFrame wndw = new JFrame ("Turtle and rabit race");
		TurtleAndRabbitRaceGUI2 tarrGUI = new TurtleAndRabbitRaceGUI2 ();
		
		wndw.add (tarrGUI);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (750, 750);
		wndw.setVisible (true);
	}
}
