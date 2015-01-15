/* Seccion 6.13 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class RandomShapesPanel extends JPanel {
	Random randomNums = new Random ();

	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		for (short j = 0; j < 10; j++) {
			g.setColor (new Color (randomNums.nextInt (256), randomNums.nextInt (256), randomNums.nextInt (256)));
			if (randomNums.nextInt (2) == 0)
				g.fillRect (randomNums.nextInt (getWidth () - 20),
					randomNums.nextInt (getHeight () - 20),
					randomNums.nextInt (getWidth () / 2) + 5,
					randomNums.nextInt (getHeight () / 2) + 5);
			else
				g.fillOval (randomNums.nextInt (getWidth () - 20),
					randomNums.nextInt (getHeight () - 20),
					randomNums.nextInt (getWidth () / 2) + 5,
					randomNums.nextInt (getHeight () / 2) + 5);
		}
	}
}
