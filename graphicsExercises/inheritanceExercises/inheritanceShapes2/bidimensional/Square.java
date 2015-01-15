package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional;

import java.awt.Graphics;

public class Square extends BidimensionalShape {
	private int lengthSide;

	public Square (int lengthSide) {
		super (0, 0);
		setLengthSide (lengthSide);
	}

	public Square (int lengthSide, int x, int y) {
		super (x, y);
		setLengthSide (lengthSide);
	}
	
	public final void setLengthSide (int newLengthSide) {
		lengthSide = newLengthSide < 0 ? 0 : newLengthSide;
	}
	
	public final int getLengthSide () {
		return lengthSide;
	}
	
	public double perimeter () {
		return 4 * lengthSide;
	}
	
	public double area () {
		return lengthSide * lengthSide;
	}
	
	public void draw (Graphics g, boolean isFilled) {
		if (isFilled)
			g.fillRect (getX (), getY (), lengthSide, lengthSide);
		else
			g.drawRect (getX (), getY (), lengthSide, lengthSide);
	}
	
	public String toString () {
		return String.format ("%sSquare with lenght side: %d", super.toString (), getLengthSide ());
	}
}
