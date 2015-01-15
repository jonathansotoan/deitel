// Cap. 7 Arreglos pag. 298
import java.awt.Graphics;
import javax.swing.JPanel;

public class SquareSpiralPanel extends JPanel {
	enum Direction {UP, DOWN, LEFT, RIGHT};

	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		int[] lastPoint = {getWidth() / 2, getHeight() /2};
		final short INCREASEVALUE = 10;
		Direction nextDir = Direction.DOWN;

		for (short j = 1; INCREASEVALUE * j < getWidth () && INCREASEVALUE * j < getHeight (); ++j) {
			
			if (nextDir == Direction.LEFT) {
				g.drawLine (lastPoint[0],
								lastPoint[1],
								lastPoint[0] = lastPoint[0] - INCREASEVALUE * j,
								lastPoint[1]);
				nextDir = Direction.UP;
			} else if (nextDir == Direction.RIGHT) {
				g.drawLine (lastPoint[0],
								lastPoint[1],
								lastPoint[0] = lastPoint[0] + INCREASEVALUE * j,
								lastPoint[1]);
				nextDir = Direction.DOWN;
			} else if (nextDir == Direction.UP) {
				g.drawLine (lastPoint[0],
								lastPoint[1],
								lastPoint[0],
								lastPoint[1] = lastPoint[1] + INCREASEVALUE * j);
				nextDir = Direction.RIGHT;
			} else if (nextDir == Direction.DOWN) {
				g.drawLine (lastPoint[0],
								lastPoint[1],
								lastPoint[0],
								lastPoint[1] = lastPoint[1] - INCREASEVALUE * j);
				nextDir = Direction.LEFT;
			}
		}
	}
}
