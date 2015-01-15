package deitel.graphicsExercises.drawing;

import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

public class BidimensionalShape extends Shape {
	private boolean isFilled;
	
	public BidimensionalShape () {
	}
	
	public BidimensionalShape (int x1, int y1, int x2, int y2, boolean isFilled, Paint paint, Stroke stroke) {
		super (x1, y1, x2, y2, paint, stroke);
		setFillable (isFilled);
	}
	
	public BidimensionalShape (int x, int y, boolean isFilled, Paint paint, Stroke stroke) {
		super (x, y, 0, 0, paint, stroke);
		setFillable (isFilled);
	}
	
	public final void setFillable (boolean fillValue) {
		isFilled = fillValue;
	}
	
	public final boolean isFilled () {
		return isFilled;
	}
	
	@Override
	public void writeExternal (final ObjectOutput ou) throws IOException {
		super.writeExternal (ou);
		
		ou.writeBoolean (isFilled ());
	}
	
	@Override
	public void readExternal (final ObjectInput oi) throws IOException, ClassNotFoundException {
		super.readExternal (oi);
		
		setFillable (oi.readBoolean ());
	}
}
