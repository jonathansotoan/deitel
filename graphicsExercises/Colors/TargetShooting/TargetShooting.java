/* Seccion 6.13 */

import javax.swing.JFrame;

public class TargetShooting {
	
	public static void main (String args[]) {
		TargetShootingPanel tsp = new TargetShootingPanel ();
		JFrame windw = new JFrame ("I'ts a target shooting test");
		
		windw.add (tsp);
		windw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		windw.setVisible (true);
		windw.setSize (300, 300);
	}
}
