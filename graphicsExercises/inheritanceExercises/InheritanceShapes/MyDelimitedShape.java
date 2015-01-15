//Exercise 10.1
public abstract class MyDelimitedShape extends MyShape {
	private boolean fill;

	public MyDelimitedShape () {
		setFill (false);
	}

	public MyDelimitedShape (int x1, int y1, int x2, int y2, Color color, boolean fill) {
		super (x1, y1, x2, y2, color);
		setFill (fill);
	}
	
	public final void setFill (boolean newFillValue) {
		fill = newFillValue;
	}
	
	public final boolean isFill () {
		return fill;
	}
}
