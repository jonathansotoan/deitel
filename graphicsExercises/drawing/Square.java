package deitel.graphicsExercises.drawing;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Square extends BidimensionalShape {

	public Square () {
	}

	public Square (int x, int y, boolean isFilled, Paint paint, Stroke stroke) {
		super (x, y, isFilled, paint, stroke);
	}

	public Square (int x1, int y1, int x2, int y2, boolean isFilled, Paint paint, Stroke stroke) {
		super (min (x1, x2), min (y1, y2), max (x1, x2), max (y1, y2), isFilled, paint, stroke);
	}
	
	public void draw (Graphics2D g2d) {
		super.draw (g2d);
		Rectangle2D rect = new Rectangle2D.Double (initialPoint.x,
																	initialPoint.y,
																	finalPoint.x - initialPoint.x,
																	finalPoint.y - initialPoint.y);
	
		if (isFilled ())
			g2d.fill (rect);
		else
			g2d.draw (rect);
	}
}
