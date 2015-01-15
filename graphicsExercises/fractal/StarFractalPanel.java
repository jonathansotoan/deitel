//Exercise 15.19
package deitel.graphicsExercises.fractal;

import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Vector;
import javax.swing.JPanel;

public class StarFractalPanel extends JPanel {
	//private Vector lines;
	private int level;
	
	public StarFractalPanel (int initialLevel) {
		level = initialLevel;
		/*lines = new Vector (5);
		lines.add (new Line2D.Double (200, 200, 400, 200));
		lines.add (new Line2D.Double (400, 200, 450, 350));
		lines.add (new Line2D.Double (200, 200, 150, 350));
		lines.add (new Line2D.Double (450, 350, 300, 500));
		lines.add (new Line2D.Double (150, 350, 300, 500));*/
	}
	
	public final void setLevel (int newLevel) {
		level = newLevel < 0 ? 0 : newLevel;
		repaint ();
	}
	
	public final int getLevel () {
		return level;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		Graphics2D g2d = (Graphics2D) g;
		drawFractal (level, 200, 200, 400, 200, g2d);
		drawFractal (level, 400, 200, 450, 350, g2d);
		drawFractal (level, 200, 200, 150, 350, g2d);
		drawFractal (level, 450, 350, 300, 500, g2d);
		drawFractal (level, 150, 350, 300, 500, g2d);
	}
	
	public void drawFractal (final int level,
										final double xA,
										final double yA,
										final double xB,
										final double yB,
										final Graphics2D g2d) {
		if (level == 0)
			g2d.draw (new Line2D.Double (xA, yA, xB, yB));
		else {
         final double xC = ( xA + xB ) / 2;
         final double yC = ( yA + yB ) / 2;
         
         final double xD = xA + ( xC - xA ) / 2 - ( yC - yA ) / 2;
         final double yD = yA + ( yC - yA ) / 2 + ( xC - xA ) / 2;
         
         drawFractal( level - 1, xD, yD, xA, yA, g2d );
         drawFractal( level - 1, xD, yD, xC, yC, g2d );
         drawFractal( level - 1, xD, yD, xB, yB, g2d ); 
		}
	}
}
