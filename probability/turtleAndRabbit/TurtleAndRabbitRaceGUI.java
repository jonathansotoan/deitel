package deitel.probability.turtleAndRabbit;//2917136

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TurtleAndRabbitRaceGUI extends JPanel implements ActionListener {
	private Rabbit rbt;
	private Turtle trtl;
	private Timer timer;

	public TurtleAndRabbitRaceGUI () {
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
		g2d.draw (new Line2D.Double (0, 0, 0, 130));
		g2d.draw (new Line2D.Double (700, 0, 700, 130));
		
		for (byte j = 1; j <= 70; ++j) {
			if (rbt.position == j) {
				g2d.draw (new Ellipse2D.Double (10 * j, 20, 50, 50));
				g.drawString ("Rabbit", 10 * j + 7, 50);
			}
			
			if (trtl.position == j) {
				g2d.draw (new Rectangle2D.Double (10 * j, 80, 50, 50));
				g.drawString ("Turtle", 10 * j + 7, 110);
			}
		}
		
		if (rbt.position == 70 || trtl.position == 70)
			timer.stop ();
	}

	public void actionPerformed (ActionEvent ae) {
		repaint ();
	}

	public static void main (String args[]) {
		JFrame wndw = new JFrame ("Turtle and rabit race");
		TurtleAndRabbitRaceGUI tarrGUI = new TurtleAndRabbitRaceGUI ();
		
		wndw.add (tarrGUI);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (750, 180);
		wndw.setVisible (true);
	}
}
