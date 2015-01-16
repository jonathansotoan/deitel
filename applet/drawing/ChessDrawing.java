import java.awt.Graphics;
import javax.swing.JApplet;

/* <applet code="ChessDrawing.class" width="140" height="150"></applet> */
public class ChessDrawing extends JApplet {
	public static final byte COLUMNS = 16;
	public static final byte ROWS = 8;
	public static final char ON_BLACK_CHAR = '*';
	
	@Override
	public void paint(Graphics g) {
		for(byte row = 0; row < ROWS; ++row)
			for(byte column = 0; column < COLUMNS; ++column)
				if((row + column) % 2 == 0)
					g.drawString(
						ON_BLACK_CHAR + "",
						25 + (column * g.getFontMetrics().charWidth(ON_BLACK_CHAR)),
						25 + (row * g.getFontMetrics().getHeight())
					);
	}
}