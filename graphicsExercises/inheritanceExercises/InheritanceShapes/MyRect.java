//Exercise 10.1
import java.awt.Graphics;

public class MyRect extends MyDelimitedShape {

	public MyRect () {
	}
	
	public MyRect (int x1, int y1, int x2, int y2, Color color, boolean fill) {
		super (x1, y1, x2, y2, color, fill);
	}
	
	public void draw (Graphics g) {
		g.setColor (getColor ());
		
		if (isFill())
			g.fillRect (getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
		else
			g.drawRect (getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
	}
}
