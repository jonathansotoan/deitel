package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional;

import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Square;

public class Cube extends TridimensionalShape {
	private Square square;

	public Cube (int lengthSide) {
		square = new Square (lengthSide);
	}
	
	public final void setLengthSide (int newLengthSide) {
		square.setLengthSide (newLengthSide);
	}
	
	public final int getLengthSide () {
		return square.getLengthSide ();
	}
	
	public double volume () {
		return square.getLengthSide () * square.area ();
	}
	
	public double area () {
		return 6 * square.area ();
	}
	
	public String toString () {
		return String.format ("%sCube with lenght side: %d", super.toString (), square.getLengthSide ());
	}
}
