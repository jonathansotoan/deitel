package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional;

import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Triangle;

import static java.lang.Math.pow;

public class Tetrahedron extends TridimensionalShape {
	private Triangle triangle;

	public Tetrahedron (double lengthSide) {
		triangle = new Triangle (lengthSide, lengthSide, lengthSide);
	}
	
	public final void setLengthSide (double lengthSide) {
		triangle.setSide1 (lengthSide);
		triangle.setSide2 (lengthSide);
		triangle.setBase (lengthSide);
		triangle.setHeight (Triangle.cathete (lengthSide, lengthSide / 2));
	}
	
	public final double getLengthSide () {
		return triangle.getSide1 ();
	}
	
	public final double getHeight () {
		return Triangle.cathete (triangle.getHeight (), triangle.getHeight () / 2);
	}
	
	public double volume () {
		return (triangle.area () * getHeight ()) / 3;
	}
	
	public double area () {
		return 5 * triangle.area ();
	}
	
	public String toString () {
		return String.format ("%sTetrahedron with length side: %,.2f", super.toString (), triangle.getSide1 ());
	}
}
