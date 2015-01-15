package deitel.graphicsExercises.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

public class Shape implements Externalizable {
	protected Point initialPoint;
	protected Point finalPoint;
	private Paint paint;
	private Stroke stroke;
	
	public Shape () {
	}
	
	public Shape (int x1, int y1, int x2, int y2, Paint paint, Stroke stroke) {
		initialPoint = new Point (x1, y1);
		finalPoint = new Point (x2, y2);
		setPaint (paint);
		setStroke (stroke);
	}
	
	public final void setStroke (Stroke newStroke) {
		stroke = newStroke == null ? new BasicStroke (2.0f) : newStroke;
	}
	
	public final Stroke getStroke () {
		return stroke;
	}
	
	public final void setPaint (Paint newPaint) {
		paint = newPaint;
	}
	
	public final Paint getPaint () {
		return paint;
	}
	
	public final void setFinalPoint (int x, int y) {
		finalPoint.x = x < 0 ? 0 : x;
		finalPoint.y = y < 0 ? 0 : y;
	}
	
	public void draw (Graphics2D g2d) {
		g2d.setStroke (stroke);
		g2d.setPaint (getPaint ());
	}
	
	public void writeExternal (final ObjectOutput ou) throws IOException {
		ou.writeObject (initialPoint);//it writes the initialPoint (Point)
		ou.writeObject (finalPoint);//it writes the finalPoint
		ou.writeBoolean (paint instanceof Color);//this boolean is to indicate that the paint object is a color
		
		if (paint instanceof Color)
			ou.writeObject ((Color) paint);//it writes the color
		else if (paint instanceof GradientPaint) {
			//these instructions write each parameter of GradientPaint's constructor
			GradientPaint gp = (GradientPaint) paint;
			
			ou.writeObject (gp.getPoint1 ());
			ou.writeObject (gp.getColor1 ());
			ou.writeObject (gp.getPoint2 ());
			ou.writeObject (gp.getColor2 ());
			ou.writeBoolean (gp.isCyclic ());
		} else
			System.err.println ("The current paint object is not supported");
		
		if (stroke instanceof BasicStroke) {
			//these instructions write each parameter of BasicStroke's constructor
			BasicStroke bs = (BasicStroke) stroke;
			
			ou.writeBoolean (bs.getDashArray () == null);//this sentence determinates if the basic stroke should be created with 3 or 6 parameters
			ou.writeFloat (bs.getLineWidth ());
			ou.writeInt (bs.getEndCap ());
			ou.writeInt (bs.getLineJoin ());
			
			if (bs.getDashArray () != null) {
				ou.writeFloat (bs.getMiterLimit ());
				ou.writeObject (bs.getDashArray ());
				ou.writeFloat (bs.getDashPhase ());
			}
		} else
			System.err.println ("The current stroke object is not supported");
	}
	
	public void readExternal (final ObjectInput oi) throws IOException, ClassNotFoundException {
		initialPoint = (Point) oi.readObject ();
		finalPoint = (Point) oi.readObject ();
		
		if (oi.readBoolean ())
			setPaint ((Color) oi.readObject ());
		else
			setPaint (new GradientPaint ((Point2D) oi.readObject (),
													(Color) oi.readObject (),
													(Point2D) oi.readObject (),
													(Color) oi.readObject (),
													oi.readBoolean ()));
		
		if (oi.readBoolean ())
			setStroke (new BasicStroke (oi.readFloat (),
													oi.readInt (),
													oi.readInt ()));
		else
			setStroke (new BasicStroke (oi.readFloat (),
													oi.readInt (),
													oi.readInt (),
													oi.readFloat (),
													(float[]) oi.readObject (),
													oi.readFloat ()));
	}
}
