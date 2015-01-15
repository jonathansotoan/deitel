/* Seccion 6.13 */

import javax.swing.JFrame;

public class RandomShapes {

	public static void main (String args[]) {
		RandomShapesPanel rsp = new RandomShapesPanel ();
		JFrame wndw = new JFrame ("This is a random shapes test");
		
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setVisible (true);
		wndw.setSize (300, 300);
		
		wndw.add (rsp);
	}
}
