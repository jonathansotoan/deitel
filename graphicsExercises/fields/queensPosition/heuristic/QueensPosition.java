// ejercicio 7.24
import javax.swing.JFrame;
import java.util.Scanner;

public class QueensPosition {
	
	public static void main (String args[]) {
		Scanner in = new Scanner (System.in);
	
		System.out.printf ("Please, insert\n%s\n%s\n",
								"0 to show a window with the field (includying the queens)",
								"1 to show all possible results (using all 64 positions)");
		char selectedOption = in.next ().charAt (0);

		System.out.println ("Insert the number of queen that you want; sorry if we can't do it.");
		byte numberOfQueens = (byte) in.nextInt ();

		if (selectedOption == '0') {
			JFrame wndw = new JFrame ("It's a queens position test");
			QueensPositionPanel qpp = new QueensPositionPanel (numberOfQueens);

			wndw.add (qpp);
			wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			wndw.setSize (500, 500);
			wndw.setVisible (true);
		} else {
			QueensPositionModel qpm = new QueensPositionModel ();
			
			for (byte j = 0; j < 8; ++j, qpm.reinitField ())
				for (byte k = 0; k < 8; ++k, qpm.reinitField ()) {
					qpm.putAllQueens (numberOfQueens, (byte) j, (byte) k);
					System.out.printf ("Start position: %d, %d. Number of queens: %d\n",
												j,
												k,
												qpm.getCurrentNumberOfQueens ());
				}
		}
	}
}
