// ejercicio 7.22
import javax.swing.JFrame;

public class HorseMoves {

	public static void main (String args[]) {
		HorseMovesPanel hmp = new HorseMovesPanel ((byte) 0, (byte) 0);
		JFrame wndw = new JFrame ("It's an horse moves test");
		
		wndw.add (hmp);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (500, 500);
		wndw.setVisible (true);
	}
}
