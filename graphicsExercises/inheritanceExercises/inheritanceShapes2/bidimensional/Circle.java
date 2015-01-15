package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional;

import java.awt.Graphics;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Circle extends BidimensionalShape {
	private int radius;

	public Circle (int radius) {
		super (0, 0);
		setRadius (radius);
	}

	public Circle (int radius, int x, int y) {
		super (x, y);
		setRadius (radius);
	}
	
	public final void setRadius (int newRadius) {
		radius = newRadius < 0 ? 0 : newRadius;
	}
	
	public final int getRadius () {
		return radius;
	}
	
	public double perimeter () {
		return 2 * radius * PI;
	}
	
	public double area () {
		return PI * Math.pow (radius, 2);
	}
	
	public void draw (Graphics g, boolean isFilled) {
		if (isFilled)
			g.fillOval (getX (), getY (), 2 * radius, 2 * radius);
		else
			g.drawOval (getX (), getY (), 2 * radius, 2 * radius);
	}
	
	public String toString () {
		return String.format ("%sCircle with radius: %d", super.toString (), getRadius ());
	}
}
