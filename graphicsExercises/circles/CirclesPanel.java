import javax.swing.JPanel;
import java.awt.Graphics;

public class CirclesPanel extends JPanel {
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		int halfWidth = getWidth () / 2;
		int halfHeight = getHeight () / 2;
		for (int j = 10; halfWidth - j / 2 > 0 && halfHeight - j / 2 > 0 ; j += 10) {
			g.drawOval (halfWidth - j / 2, halfHeight - j / 2, j, j);
		}
	}
}
