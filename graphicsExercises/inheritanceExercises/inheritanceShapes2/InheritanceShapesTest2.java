package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2;

import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.BidimensionalShape;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Triangle;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Circle;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional.Square;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional.TridimensionalShape;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional.Tetrahedron;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional.Sphere;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.tridimensional.Cube;

public class InheritanceShapesTest2 {

	public static void main (String args[]) {
		Shape[] shapes = new Shape[8];
		
		shapes[0] = new Circle (7);
		shapes[1] = new Square (10);
		shapes[2] = new Triangle (9, 10.2956, 5);
		shapes[3] = new Triangle (12, 12, 12);
		shapes[4] = new Triangle (10, 10, 12);
		shapes[5] = new Sphere (5);
		shapes[6] = new Cube (8);
		shapes[7] = new Tetrahedron (4);
		
		for (Shape currentShape: shapes) {
			System.out.printf ("\n%s\nArea: %,.2f\n", currentShape, currentShape.area ());

			if (currentShape instanceof BidimensionalShape)
				System.out.printf ("Perimeter: %,.4f\n", ((BidimensionalShape) currentShape).perimeter ());
			else if (currentShape instanceof TridimensionalShape)
				System.out.printf ("Volume: %,.4f\n", ((TridimensionalShape) currentShape).volume ());
		}
	}
}
