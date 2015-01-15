// Ejercicio 7.25
import javax.swing.JFrame;

public class ExhaustiveQueensPosition {

	static ExhaustiveQueensPositionModel eqpm = new ExhaustiveQueensPositionModel ();
	static int numberOfCorrectPositioning = 0;

	public static void main (String args[]) {
		setNextQueen ((byte) 8);
		System.out.println ("Final number of corrects positioning: " + numberOfCorrectPositioning);
	}
	
	public static void setNextQueen (byte numberOfQueens) {
		if (numberOfQueens > 0) {
			Queen qn = new Queen (eqpm.getStateField ());
			
			for (byte a = 0; a < 8; ++a)
				for (byte b = 0; b < 8; ++b)
					if (eqpm.getStateField ()[a][b] == 'e') {
						qn.setPosition (a, b);
						setNextQueen ((byte) (numberOfQueens - 1));
			
						if (getCurrentNumberOfQueens () == 8 && eqpm.isCorrectPositioning ()) {
							++numberOfCorrectPositioning;
							printField ();
						}
						if (numberOfQueens > 5)
							System.out.printf ("numberOfQueens var = %d, numberOfCorrectPositioning = %d\n",
													numberOfQueens,
													numberOfCorrectPositioning);
					}

			qn.deletePosition ();
		}
	}
	
	public static void printField () {
		
		for (byte j = 0; j < eqpm.getStateField ().length; ++j) {
			for (byte k = 0; k < eqpm.getStateField ()[0].length; ++k)
				System.out.print (eqpm.getStateField ()[j][k] + " ");

			System.out.println ();
		}
		
		System.out.println ();
	}
	
	public static byte getCurrentNumberOfQueens () {
		byte numberOfQueens = 0;
		
		for (byte j = 0; j < eqpm.getStateField ().length; ++j)
			for (byte k = 0; k < eqpm.getStateField ()[j].length; ++k)
				if (eqpm.getStateField ()[j][k] == 'q')
					++numberOfQueens;
					
		return numberOfQueens;
	}
}
