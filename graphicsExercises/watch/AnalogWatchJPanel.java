package deitel.graphicsExercises.watch;

import deitel.graphicsExercises.drawing.Circle;
import deitel.graphicsExercises.drawing.Line;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DigitalWatchJPanel extends JPanel {
	private BasicStroke stroke;
	private Color background;
	private Color frameColor;
	private Color handsColor;
	private Color linesColor;
	private Color numbersColor;

	/**
	 * Creates a watch with the specified width and set of colors.
	 * <p/>
	 * @param	width			the width of the watch's lines
	 * @param	frameColor		the color that the outer frame will have (the circle)
	 * @param	handsColor		the color of the hands that indicates the hour
	 * @param	linesColor		the color of the lines that the hands point to
	 * @param	numbersColor	the color of the numbers that the lines point to
	 */
	public DigitalWatchJPanel(float width, Color background, Color frameColor, Color handsColor, Color linesColor, Color numbersColor) {
		stroke = new BasicStroke(width);
		this.background = background;
		this.frameColor = frameColor;
		this.handsColor = handsColor;
		this.linesColor = linesColor;
		this.numbersColor = numbersColor;
	}

	/**
	 * Creates a black watch with white background, and a width of 5.
	 */
	public DigitalWatchJPanel() {
		stroke = new BasicStroke(5.0f);
		background = Color.WHITE;
		frameColor = handsColor = linesColor = numbersColor = Color.BLACK;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int watchSize = Math.min(getWidth(), getHeight());

		// lines of 12, 3, 6 and 9
		new Line(watchSize / 2, 0, watchSize / 2, watchSize, linesColor, stroke).draw(g2d);
		new Line(0, watchSize / 2, watchSize, watchSize / 2, linesColor, stroke).draw(g2d);

		// lines of 1, 2, 4, 5, 7, 8, 10, 11
		new Line(3 * watchSize / 4, 0, watchSize / 4, watchSize, linesColor, stroke).draw(g2d);
		new Line(watchSize, watchSize / 4, 0, 3 * watchSize / 4, linesColor, stroke).draw(g2d);
		new Line(watchSize / 4, 0, 3 * watchSize / 4, watchSize, linesColor, stroke).draw(g2d);
		new Line(0, watchSize / 4, watchSize, 3 * watchSize / 4, linesColor, stroke).draw(g2d);

		// frame
		new Circle(0, 0, watchSize, watchSize, false, frameColor, stroke).draw(g2d);
	}

	public static void main(String[] args) {
		JFrame wndw = new JFrame("DigitalWatchJPanel testing");
		DigitalWatchJPanel watch = new DigitalWatchJPanel();
		wndw.add(watch);
		wndw.setSize(300, 300);
		wndw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wndw.setVisible(true);
	}
}
