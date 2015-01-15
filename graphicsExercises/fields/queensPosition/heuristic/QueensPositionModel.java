// ejercicio 7.24
/**
 * This class simulates the eight queens or "ocho reinas" (in spanish) problem.
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 0.01 12/06/2013
 * @see QueensPosition
 * @see QueensPositionPanel
 */
public class QueensPositionModel {
	char[][] stateField = new char[8][8];// 'q' it means that there is a qeen
													// 'e' it is empty
													// 'b' it is blocked, it is impossible put a queen here

	/**
	 * This is the constructor, it inits the stateField array with 'e' value (empty).
	 */
	public QueensPositionModel () {
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
	 * Returns the optimal position for the next queen.
	 *
	 * @returns a <code><b>byte</b></code> array[2] with the optimal position's x and y coordinates.
	 */
	private byte[] getOptimalPosition () {
		byte[] optimalPosition = {-1, -1};
		
		for (byte j = 0; j < stateField.length && optimalPosition[0] == -1; ++j)
			for (byte k = 0; k < stateField[j].length && optimalPosition[0] == -1; ++k)
				if (stateField[j][k] == 'e') {
					// it assumes that the first empty position is the optimal position
					optimalPosition[0] = j;
					optimalPosition[1] = k;
				}

		if (optimalPosition[0] != -1)
			for (byte j = 0; j < stateField.length; ++j)
				for (byte k = 0; k < stateField[j].length; ++k)
					if (stateField[j][k] == 'e')
						optimalPosition = getBetterPosition (optimalPosition[0], optimalPosition[1], j, k);
		
		return optimalPosition;
	}
	
	/**
	 * Returns the position which block less squares.
	 *
	 * @param xPosition1 as <code><b>byte</b></code> which defines the position number 1's X axis position.
	 * @param yPosition1 as <code><b>byte</b></code> which defines the position number 1's X axis position.
	 * @param xPosition2 as <code><b>byte</b></code> which defines the position number 2's X axis position.
	 * @param yPosition2 as <code><b>byte</b></code> which defines the position number 2's X axis position.
	 * @returns an array[2] with better position.
	 */
	private byte[] getBetterPosition (byte xPosition1, byte yPosition1, byte xPosition2, byte yPosition2) {
		byte[] betterPosition = new byte[2];
	
		if (blockedSquares (xPosition1, yPosition1, 'g') < blockedSquares (xPosition2, yPosition2, 'g')) {
			betterPosition[0] = xPosition1;
			betterPosition[1] = yPosition1;
		} else {
			betterPosition[0] = xPosition2;
			betterPosition[1] = yPosition2;
		}

		return betterPosition;
	}
	
	/**
	 * if operation = 'g' (get) it will return the number of possible blocked squares;
	 * if it isn't, it will return -1 and it'll make the real squares block.
	 *
	 * @param xPosition as a <code><b>byte</b></code> that defines the queen's x axis position.
	 * @param yPosition as a <code><b>byte</b></code> that defines the queen's y axis position.
	 * @param operation as a <code><b>char</b></code> that defines the operation that will be executed (get or 
	 * 	set the number of possible blocked squares).
	 * @returns a byte with the number of blocked squares or -1 if the operation is different that 'g' (it means 
	 * 	that you want to set a queen in the specified position)
	 */
	private byte blockedSquares (byte xPosition, byte yPosition, char operation) {
		byte count = 1;// it starts in 1 because the cicles don't sum the position (xPosition, yPosition)

		// it counts (or sets) the number of blocked squares (horizontal)
		for (byte j = 0; j < stateField.length; ++j)
			if (stateField[xPosition][j] == 'e' && j != yPosition)
				if (operation == 'g')
					++count;
				else
					stateField[xPosition][j] = 'b';

		// it counts (or sets) the number of blocked squares (vertical)
		for (byte j = 0; j < stateField[xPosition].length; ++j)
			if (stateField[j][yPosition] == 'e' && j != xPosition)
				if (operation == 'g')
					++count;
				else
					stateField[j][yPosition] = 'b';

		// it counts (or sets) the number of blocked squares (diagonal from left-top to right-bottom)
		for (byte j = xPosition - yPosition > 0 ? (byte) (xPosition - yPosition): 0,
						k = xPosition - yPosition < 0 ? (byte) (-1 * (xPosition - yPosition)): 0;
				j < stateField.length && k < stateField[j].length;
				++j, ++k)
			if (stateField[j][k] == 'e' && j != xPosition)// we don't need compare  k != yPosition. it is enough
				if (operation == 'g')
					++count;
				else
					stateField[j][k] = 'b';

		// it counts (or sets) the number of blocked squares (diagonal from right-top to left-bottom)
		for (byte k = xPosition + yPosition > 7 ? (byte) 7: (byte) (xPosition + yPosition),
						j = (byte) (xPosition + yPosition - k);
				j <= 7 && k >= 0;
				++j, --k)
			if (stateField[j][k] == 'e' && j != xPosition)// we don't need compare  k != yPosition. it is enough
				if (operation == 'g')
					++count;
				else
					stateField[j][k] = 'b';
		
		if (operation == 'g')
			return count;
		else
			return -1;
	}
	
	/**
	 * Put the specified queens into the optimal position begining in the specified position.
	 *
	 * @param numberOfQueens as <code><b>byte</b></code> that defines the number of queens.
	 * @param initialRow as <code><b>byte</b></code> that defines the initial row to the first queen.
	 * @param initialColumn as <code><b>byte</b></code> that defines the initial column to the first queen.
	 */
	public void putAllQueens (byte numberOfQueens, byte initialRow, byte initialColumn) {
		byte[] nextStep = {initialRow, initialColumn};
	
		for (byte j = 0; j < numberOfQueens; ++j, nextStep = getOptimalPosition ())
			if (nextStep[0] != -1) {
				stateField[nextStep[0]][nextStep[1]] = 'q';
				blockedSquares (nextStep[0], nextStep[1], 'b');
			}
	}

	/**
	 * Put the specified queens into the optimal position.
	 *
	 * @param numberOfQueens as <code><b>byte</b></code> that defines the number of queens.
	 */
	public void putAllQueens (byte numberOfQueens) {
		byte[] op = getOptimalPosition ();
		putAllQueens (numberOfQueens, op[0], op[1]);
	}
	
	/**
	 * Returns the current state field.
	 *
	 * @returns an char <code><b>array bidimensional</b></code> with the current state field 'e' means empty, 'b' 
	 * 	means blocked and 'q' means that there is a queen.
	 */
	public char[][] getStateField () {
		return stateField;
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
