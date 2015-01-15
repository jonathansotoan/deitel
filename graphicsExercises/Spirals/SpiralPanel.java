// Cap. 7 Arreglos pag. 298
import java.awt.Graphics;
import javax.swing.JPanel;

public class SpiralPanel extends JPanel {

	public void paintComponent (Graphics g) {
		super.paintComponent( g );
		
		final int INCREASEVALUE = 20;
		int[] lastPoints = {(getWidth() - INCREASEVALUE) / 2, (getHeight() - INCREASEVALUE) / 2};

		for (short j = 1; INCREASEVALUE * j < getWidth () && INCREASEVALUE * j < getHeight (); ++j) {
			g.drawArc (lastPoints[0],
							lastPoints[1],
							INCREASEVALUE * j,
							INCREASEVALUE * j,
							0,
							180);
			
			g.drawArc (lastPoints[0] - INCREASEVALUE / 2, lastPoints[1], j * (INCREASEVALUE * (3 / 2)), j * (INCREASEVALUE * (3 / 2)), 0, -180);
			
			lastPoints[0] -= INCREASEVALUE / 2;
			lastPoints[1] -= INCREASEVALUE / 2;
		}
	}
}
