package deitel.graphicsExercises.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.Stroke;

import java.io.ObjectOutput;
import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class Circle extends BidimensionalShape {

	public Circle () {
	}
	
	public Circle (int x, int y, boolean isFilled, Paint paint, Stroke stroke) {
		super (x, y, isFilled, paint, stroke);
	}
	
	public Circle (int x1, int y1, int x2, int y2, boolean isFilled, Paint paint, Stroke stroke) {
		super (min (x1, x2), min (y1, y2), max (x1, x2), max (y1, y2), isFilled, paint, stroke);
	}
	
	public void draw (Graphics2D g2d) {
		super.draw (g2d);
		Ellipse2D ellipse = new Ellipse2D.Double (initialPoint.x,
																initialPoint.y,
																finalPoint.x - initialPoint.x,
																finalPoint.y - initialPoint.y);
	
		if (isFilled ())
			g2d.fill (ellipse);
		else
			g2d.draw (ellipse);
	}
	
	/*@Override
	public void writeExternal (final ObjectOutput ou) throws IOException {
		ou.writeInt (DrawingPanel.CIRCLE);
		super.writeExternal (ou);
	}*/
}
