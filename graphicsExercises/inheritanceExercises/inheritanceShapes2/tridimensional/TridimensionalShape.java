package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional;

import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.Shape;

public abstract class TridimensionalShape implements Shape {

	public abstract double volume ();

	//test final
	public String toString () {
		return "Tridimensional shape\n";
	}
}
