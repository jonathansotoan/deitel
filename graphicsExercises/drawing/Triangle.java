package deitel.graphicsExercises.drawing;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Stroke;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Triangle extends BidimensionalShape {

	public Triangle () {
	}

	public Triangle (int x, int y, boolean isFilled, Paint paint, Stroke stroke) {
		super (x, y, isFilled, paint, stroke);
	}
	
	public Triangle (int x1, int y1, int x2, int y2, boolean isFilled, Paint paint, Stroke stroke) {
		super (min (x1, x2), min (y1, y2), max (x1, x2), max (y1, y2), isFilled, paint, stroke);
	}
	
	public void draw (Graphics2D g2d) {
		super.draw (g2d);
		int[] pointsX = {(initialPoint.x + finalPoint.x) / 2, finalPoint.x, initialPoint.x};
		int[] pointsY = {initialPoint.y, finalPoint.y, finalPoint.y};
		Polygon polygon = new Polygon (pointsX, pointsY, 3);
		
		if (isFilled ())
			g2d.fill (polygon);
		else
			g2d.draw (polygon);
	}
}
