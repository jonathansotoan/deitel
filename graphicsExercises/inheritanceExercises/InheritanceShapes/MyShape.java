//Exercise 10.1
import java.awt.Color;
import java.awt.Graphics;

public abstract class MyShape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	
	public MyShape () {
		this (0, 0, 0, 0, Color.BLACK);
	}
	
	public MyShape (int x1, int y1, int x2, int y2, Color color) {
		setX1 (x1);
		setY1 (y1);
		setX2 (x2);
		setY2 (y2);
		setColor (color);
	}

	public final void setX1 (int newX1) {
		if (newX1 < 0)
			x1 = 0;
		else if (Math.min (newX1, getX2 ()) == newX1)
			x1 = newX1;
		else {
			x1 = getX2 ();
			setX2 (newX1);
		}
	}
	
	public final int getX1 () {
		return x1;
	}
	
	public final void setY1 (int newY1) {
		if (newY1 < 0)
			y1 = 0;
		else if (Math.min (newY1, getY2 ()) == newY1)
			y1 = newY1;
		else {
			y1 = getY2 ();
			setY2 (newY1);
		}
	}
	
	public final int getY1 () {
		return y1;
	}
	
	public final void setX2 (int newX2) {
		if (newX2 < 0)
			x2 = 0;
		else if (Math.max (newX2, getX1 ()) == newX2)
			x2 = newX2;
		else {
			x2 = getX1 ();
			setX1 (newX2);
		}
	}
	
	public final int getX2 () {
		return x2;
	}
	
	public final void setY2 (int newY2) {
		if (newY2 < 0)
			y2 = 0;
		else if (Math.max (newY2, getY1 ()) == newY2)
			y2 = newY2;
		else {
			y2 = getY1 ();
			setY1 (newY2);
		}
	}
	
	public final int getY2 () {
		return y2;
	}
	
	public final void setColor (Color newColor) {
		color = newColor;
	}
	
	public final Color getColor () {
		return color;
	}
	
	public abstract void draw (Graphics g);
}
