package deitel.graphicsExercises.g2d;

import javax.swing.JFrame;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class ScreensaverTest {

	public static void main (String args[]) {
		JFrame wndw = new JFrame ("Screensaver");
		
		wndw.add (new Screensaver (1000));
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (500, 500);
		wndw.setExtendedState (MAXIMIZED_BOTH);
		wndw.setVisible (true);
	}
}
