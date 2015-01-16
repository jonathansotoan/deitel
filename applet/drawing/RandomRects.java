import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JApplet;

/**
 * Draws random rects over the applet ensuring that the top-left corner is
 * inside the applet area.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 16/01/15
 */
/* <applet code="RandomRects.class" width="800" height="600"></applet> */
public class RandomRects extends JApplet {
	public static final byte RECTS_QUANTITY = 7;
	private Random randomGenerator;

	@Override
	public void init() {
		randomGenerator = new Random();
	}
	
	@Override
	public void paint(Graphics g) {
		for(byte j = 0; j < RECTS_QUANTITY; ++j)
			drawRect(
				g,
				new Rectangle(
					randomGenerator.nextInt(g.getClipBounds().width),
					randomGenerator.nextInt(g.getClipBounds().height),
					randomGenerator.nextInt(g.getClipBounds().width / 2),
					randomGenerator.nextInt(g.getClipBounds().height / 2)
				),
				randomGenerator.nextBoolean()
			);
	}

	/**
	 * Draws a rectangle.
	 * <p/>
	 * @param	g	the Graphics object used to draw the Rectangle
	 * @param	rect	the Rectangle that is going to be drawn
	 * @param	filled	whether the rect must be filled or not
	 */
	private void drawRect(Graphics g, Rectangle rect, boolean filled) {
		if(filled)
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		else
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
}