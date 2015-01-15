// ejercicio 7.25
import java.util.Random;

/**
 * This class simulates the eight queens or "ocho reinas" (in spanish) problem.
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 0.01 12/06/2013
 * @see QueensPosition
 * @see QueensPositionPanel
 */
public class RandomQueensPositionModel {
	char[][] stateField = new char[8][8];// 'q' it means that there is a qeen
													// 'e' it is empty
													// 'b' it is blocked, it is impossible put a queen here

	/**
	 * This is the constructor, it inits the stateField array with 'e' value (empty).
	 */
	public RandomQueensPositionModel () {
		reinitField ();
	}
	
	/**
	 * This reinits the state field.
	 */
	public void reinitField () {
		for (byte j = 0; j < stateField.length; ++j)
			for (byte k = 0; k < stateField[0].length; ++k)
				stateField[j][k] = 'e';
	}
	
	/**
	 * It'll set blocked squares.
	 *
	 * @param xPosition as a <code><b>byte</b></code> that defines the queen's x axis position.
	 * @param yPosition as a <code><b>byte</b></code> that defines the queen's y axis position.
	 */
	private void blockedSquares (byte xPosition, byte yPosition) {
		// it counts (or sets) the number of blocked squares (horizontal)
		for (byte j = 0; j < stateField.length; ++j)
			if (stateField[xPosition][j] == 'e' && j != yPosition)
				stateField[xPosition][j] = 'b';

		// it counts (or sets) the number of blocked squares (vertical)
		for (byte j = 0; j < stateField[xPosition].length; ++j)
			if (stateField[j][yPosition] == 'e' && j != xPosition)
				stateField[j][yPosition] = 'b';

		// it counts (or sets) the number of blocked squares (diagonal from left-top to right-bottom)
		for (byte j = xPosition - yPosition > 0 ? (byte) (xPosition - yPosition): 0,
						k = xPosition - yPosition < 0 ? (byte) (-1 * (xPosition - yPosition)): 0;
				j < stateField.length && k < stateField[j].length;
				++j, ++k)
			if (stateField[j][k] == 'e' && j != xPosition)// we don't need compare  k != yPosition. it is enough
				stateField[j][k] = 'b';

		// it counts (or sets) the number of blocked squares (diagonal from right-top to left-bottom)
		for (byte k = xPosition + yPosition > 7 ? (byte) 7: (byte) (xPosition + yPosition),
						j = (byte) (xPosition + yPosition - k);
				j <= 7 && k >= 0;
				++j, --k)
			if (stateField[j][k] == 'e' && j != xPosition)// we don't need compare  k != yPosition. it is enough
				stateField[j][k] = 'b';
	}
	
	/**
	 * Put the specified queens into the optimal position begining in the specified position.
	 *
	 * @param numberOfQueens as <code><b>byte</b></code> that defines the number of queens.
	 * @param initialRow as <code><b>byte</b></code> that defines the initial row to the first queen.
	 * @param initialColumn as <code><b>byte</b></code> that defines the initial column to the first queen.
	 */
	public void putAllQueens (byte numberOfQueens) {
		long numberOfAttempts = 0;
		Random randomValue = new Random ();
	
		System.out.println ("Please wait, we're calculating...");
		while (getCurrentNumberOfQueens () != 8) {
			byte randomRow = (byte) randomValue.nextInt (8);
			byte randomColumn = (byte) randomValue.nextInt (8);

			if (stateField[randomRow][randomColumn] == 'e') {
				stateField[randomRow][randomColumn] = 'q';
				blockedSquares (randomRow, randomColumn);
				++numberOfAttempts;
				
				if (numberOfAttempts % 8 == 0)
					reinitField ();
			}
		}
		
		System.out.println ("Number of attempts: " + numberOfAttempts);
	}
	
	/**
	 * Returns the current number of queens in the field.
	 *
	 * @returns a number as <code><b>byte</b></code> which is the current number of queens in the field.
	 */
	public byte getCurrentNumberOfQueens () {
		byte numberOfQueens = 0;
		
		for (char[] columns: stateField)
			for (char value: columns)
				if (value == 'q')
					++numberOfQueens;
		
		return numberOfQueens;
	}
}
