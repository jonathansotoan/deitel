package deitel.graphicsExercises.circles.rainbow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rainbow extends JPanel {
	public static final byte OVAL = 0;
	public static final byte ELLIPSE = 1;
	
	private final byte QUANTITY;
	private final byte TYPE;

	public Rainbow (byte quantity, byte type) {
		setBackground (Color.WHITE);
		QUANTITY = quantity < 0 ? 0 : quantity;
		TYPE = type == 0 || type == 1 ? type : 0;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Random ran = new Random ();
		
		for (byte j = QUANTITY; j > 0; --j) {
			g.setColor (new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)));
			
			if (TYPE == 0)
				g.fillOval (getWidth () / 2 - j * 10, getHeight () / 2 - j * 10, j * 20, j * 20);
			else
				((Graphics2D) g).fill (new Ellipse2D.Double (getWidth () / 2 - j * 10, getHeight () / 2 - j * 10, j * 20, j * 20));
		}
		
		g.clearRect (0, getHeight () / 2, getWidth (), getHeight ());
	}
	
	public static void main (String args[]) {
		JFrame wndw = new JFrame ("Rainbow");
		Rainbow rnbw = new Rainbow ((byte) 8, (byte) 0);
		Rainbow rnbw2 = new Rainbow ((byte) 8, (byte) 1);
		
		wndw.setLayout (new GridLayout (2, 1, 10, 10));
		wndw.add (rnbw);
		wndw.add (rnbw2);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (400, 400);
		wndw.setVisible (true);
	}
}