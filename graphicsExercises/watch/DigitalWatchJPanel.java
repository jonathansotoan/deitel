package deitel.graphicsExercises.watch;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DigitalWatchJPanel extends JPanel {
	private CurrentTime currentTime;
	private Dimension preferredSize;

	public DigitalWatchJPanel() {
		super();
		currentTime = new CurrentTime(null, null, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(currentTime.toString(), 0, 10);
	}

	@Override
	public Dimension getPreferredSize() {
		return getGraphics() == null ? new Dimension(52, 16) :  new Dimension(
			getGraphics().getFontMetrics().charWidth(':') * 3 + getGraphics().getFontMetrics().charWidth('0') * 6,
			getGraphics().getFontMetrics().getHeight()
		);
	}

	public static void main(String[] args) {
		JFrame wndw = new JFrame("DigitalWatchJPanel testing");
		DigitalWatchJPanel watch = new DigitalWatchJPanel();
		wndw.add(watch);
		wndw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wndw.setVisible(true);
		wndw.pack();
	}
}
