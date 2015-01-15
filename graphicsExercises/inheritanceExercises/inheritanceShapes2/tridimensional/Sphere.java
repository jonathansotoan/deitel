package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional;

import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Circle;

import static java.lang.Math.PI;

public class Sphere extends TridimensionalShape {
	private Circle circle;

	public Sphere (int radius) {
		circle = new Circle (radius);
	}
	
	public final void setRadius (int newRadius) {
		circle.setRadius (newRadius);
	}
	
	public final int getRadius () {
		return circle.getRadius ();
	}
	
	public double volume () {
		return (area () * getRadius ()) / 3;
	}
	
	public double area () {
		return 4 * circle.area ();
	}
	
	public String toString () {
		return String.format ("%sSphere with radius: %d", super.toString (), getRadius ());
	}
}
