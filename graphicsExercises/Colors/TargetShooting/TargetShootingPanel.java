/* Seccion 6.13 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class TargetShootingPanel extends JPanel {
	Random numAlAzar = new Random ();

	public void paintComponent (Graphics g) {
		super.paintComponent (g);

		Color colores[] = {new Color (numAlAzar.nextInt (256), numAlAzar.nextInt (256), numAlAzar.nextInt (256)),
			new Color (numAlAzar.nextInt (256), numAlAzar.nextInt (256), numAlAzar.nextInt (256))};
		
		for (short j = 0; getWidth () - 20 * j > 19 || getHeight () - 20 * j > 19; j++) {
			g.setColor (colores[j % 2]);
			
			if (getWidth () < getHeight ())
				g.fillOval (10 * j, ((getHeight () - getWidth ()) / 2) + 10 * j, getWidth () - 20 * j, getWidth () - 20 * j);
			else if (getHeight () < getWidth () || getWidth () == getHeight ())
				g.fillOval (((getWidth () - getHeight ()) / 2) + 10 * j, 10 * j, getHeight () - 20 * j, getHeight () - 20 * j);
		}
	}
}
