package deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.bidimensional;

import java.awt.Graphics;
import java.awt.Point;
import deitel.graphicsExercises.inheritanceExercises.inheritanceShapes2.Shape;

public abstract class BidimensionalShape implements Shape {
	private Point point;
	
	public BidimensionalShape (int x, int y) {
		point = new Point (x, y);
	}
	
	public void setX (int x) {
		point.x = x < 0 ? 0 : x;
	}
	
	public int getX () {
		return point.x;
	}
	
	public void setY (int y) {
		point.y = y < 0 ? 0 : y;
	}
	
	public int getY () {
		return point.y;
	}
	
	public String toString () {
		return "Bidimensional shape\n";
	}

	public abstract double perimeter ();
	
	public abstract void draw (Graphics g, boolean isFilled);
}
