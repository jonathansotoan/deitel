//Exercise 10.1
import java.awt.Graphics;

public class MyLine extends MyShape {

	public MyLine () {
	}
	
	public MyLine (int x1, int y1, int x2, int y2, Color color) {
		super (x1, y1, x2, y2, color);
	}
	
	public void draw (Graphics g) {
		g.setColor (getColor());
		g.drawLine (getX1(), getY1(), getX2(), getY2());
	}
}
