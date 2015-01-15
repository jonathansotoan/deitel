// Cap. 7 Arreglos pag. 298
import javax.swing.JFrame;

public class Spiral {

	public static void main (String args[]) {
		JFrame wndw = new JFrame ("It is a Spiral test");
	
		SpiralPanel sp = new SpiralPanel ();
	
		wndw.add (sp);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (300, 300);
		wndw.setVisible (true);
	}
}
