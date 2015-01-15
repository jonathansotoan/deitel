package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional;

import java.awt.Graphics;
import java.awt.Point;

import static java.lang.Math.sqrt;
import static java.lang.Math.round;

public class Triangle extends BidimensionalShape {
	private double side1;
	private double side2;
	private double base;
	private double height;

	public Triangle (double side1, double side2, double base) {
		super (0, 0);
		init (side1, side2, base);
	}

	public Triangle (double side1, double side2, double base, int x, int y) {
		super (x, y);
		init (side1, side2, base);
	}
	
	private void init (double side1, double side2, double base) {
		this.side1 = side1 < 0 ? 0 : side1;
		this.side2 = side2 < 0 ? 0 : side2;
		this.base = base < 0 ? 0 : base;

		if ((int)(hypotenuse (side1, base) * 100) == (int) (side2 * 100))
			setHeight (side1);
		else if ((int)(hypotenuse (side2, base) * 100) == (int) (side1 * 100))
			setHeight (side2);
		else
			setHeight (cathete (side1, base / 2));
	}
	
	public final void setSide1 (double newSide1) {
		if (newSide1 >= 0)
			init (newSide1, side2, base);
	}
	
	public final double getSide1 () {
		return side1;
	}
	
	public final void setSide2 (double newSide2) {
		if (newSide2 >= 0)
			init (side1, newSide2, base);
	}
	
	public final double getSide2 () {
		return side2;
	}
	
	public final void setBase (double newBase) {
		if (newBase >= 0)
			init (side1, side2, newBase);
	}
	
	public final double getBase () {
		return base;
	}
	
	public final void setHeight (double newHeight) {
		height = newHeight < 0 ? 0 : newHeight;
	}
	
	public final double getHeight () {
		return height;
	}
	
	public String toString () {
		return String.format ("%sTriangle with\nSide1: %,.2f\nSide2: %,.2f\nBase: %,.2f\nHeight: %,.2f",
										super.toString (),
										getSide1 (),
										getSide2 (),
										getBase (),
										getHeight ());
	}
	
	public double perimeter () {
		return side1 + side2 + base;
	}
	
	public double area () {
		return (base * height) / 2;
	}
	
	public void draw (Graphics g, boolean isFilled) {
		int max = isFilled ? 20 : 1;
		
		for (int j = 0; j < max && getY () + j != (int) round (getY () + getHeight () - j); ++j) {
			int lastX = (int) round (getX () + base / 2);
			int lastY = getY ();
		
			g.drawLine (lastX - j,
							lastY + j,
							lastX = (int) round (round (getX () + base / 2) + cathete (getSide2 (), getHeight ())) - j,
							lastY = (int) round (getY () + getHeight ()) - j);

			g.drawLine (lastX, lastY, lastX = (int) (lastX - base) + j, lastY - j);
			g.drawLine (lastX, lastY - j, (int) round (getX () + base / 2) - j, getY () + j);
		}
	}
	
	public static double cathete (double hypotenuse, double cathete) {
		return sqrt (hypotenuse * hypotenuse - cathete * cathete);
	}
	
	public static double hypotenuse (double cathete1, double cathete2) {
		return sqrt (cathete1 * cathete1 + cathete2 * cathete2);
	}
}
