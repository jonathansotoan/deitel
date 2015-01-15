package deitel.graphicsExercises.drawing;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.Stroke;

import java.io.ObjectOutput;
import java.io.IOException;

public class Line extends Shape {

	public Line () {
	}

	public Line (int x, int y, Paint paint, Stroke stroke) {
		super (x, y, 0, 0, paint, stroke);
	}
	
	public Line (int x1, int y1, int x2, int y2, Paint paint, Stroke stroke) {
		super (x1, y1, x2, y2, paint, stroke);
	}
	
	public void draw (Graphics2D g2d) {
		super.draw (g2d);
		g2d.draw (new Line2D.Double (initialPoint.x, initialPoint.y, finalPoint.x, finalPoint.y));
	}
	
	/*@Override
	public void writeExternal (final ObjectOutput ou) throws IOException {
		ou.writeByte (DrawingPanel.LINE);
		super.writeExternal (ou);
	}*/
}
