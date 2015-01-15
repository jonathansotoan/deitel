/* Figura 5.28 */

import javax.swing.JFrame;

public class Circles {
	
	public static void main (String args[]) {
		JFrame frame = new JFrame ("CirclesPanel class test");
		
		frame.add (new CirclesPanel ());
		frame.setSize (260, 260);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
	}
}
