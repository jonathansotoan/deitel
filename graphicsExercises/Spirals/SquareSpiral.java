// Cap. 7 Arreglos pag. 298
import javax.swing.JFrame;

public class SquareSpiral {

	public static void main (String args[]) {
		JFrame wndw = new JFrame ("It is a square spiral test");
		SquareSpiralPanel ssp = new SquareSpiralPanel ();
		
		
		wndw.add (ssp);
		
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (300, 300);
		wndw.setVisible (true);
	}
}
