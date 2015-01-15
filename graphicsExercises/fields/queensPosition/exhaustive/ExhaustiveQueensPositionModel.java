// ejercicio 7.25
public class ExhaustiveQueensPositionModel {
	private char stateField[][] = new char[8][8];// 'q' it means that there is a qeen
													// 'e' it is empty
													// 'b' it is blocked
	
	/**
	 * This is the constructor, it inits the stateField array with 'e' value (empty).
	 */
	public ExhaustiveQueensPositionModel () {
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
	public boolean isCorrectPositioning () {
		boolean isCorrect = true;
	
		// it checks if there is more than 2 queens horizontally
		for (byte j = 0; j < stateField.length && isCorrect; ++j)
			for (byte k = 0, numberOfQueens = 0; k < stateField[j].length && isCorrect; ++k)
				if (stateField[j][k] == 'q') {
					++numberOfQueens;

					if (numberOfQueens == 2)
						isCorrect = false;
				}

		// it checks if there is more than 2 queens vertically
		for (byte j = 0; isCorrect && j < stateField.length; ++j)
			for (byte k = 0, numberOfQueens = 0; k < stateField[j].length && isCorrect; ++k)
				if (stateField[k][j] == 'q') {
					++numberOfQueens;

					if (numberOfQueens == 2)
						isCorrect = false;
				}

		// it checks if there is more than 2 queens diagonally (from left-top to right-bottom)
		for (byte j = 0; isCorrect && j < stateField.length; ++j) {
			isCorrect = checkDiagonal ((byte) 0, j, 'l');
			isCorrect = checkDiagonal (j, (byte) 0, 'l');
		}

		// it checks if there is more than 2 queens diagonally (from right-top to left-bottom)
		for (byte j = (byte) (stateField.length - 1); isCorrect && j >= 0; --j) {
			isCorrect = checkDiagonal ((byte) 0, j, 'r');
			isCorrect = checkDiagonal (j, (byte) 7, 'r');
		}
			
		return isCorrect;
	}
	
	private boolean checkDiagonal (byte initialRow, byte initialColumn, char operation) {
		boolean diagonal = true;
		
		for (byte numberOfQueens = 0;
				diagonal
					&& initialRow < stateField.length
					&& initialColumn < stateField[initialRow].length
					&& initialColumn >= 0;
				++initialRow, initialColumn = operation == 'l'? (byte)(initialColumn + 1): (byte)(initialColumn - 1))
			if (stateField[initialRow][initialColumn] == 'q') {
				++numberOfQueens;
				
				if (numberOfQueens == 2)
					diagonal = false;
			}
			
		
		return diagonal;
	}
	
	public void setFieldValue (byte row, byte column, char value) {
		if (row >= 0
			&& row < stateField.length
			&& column >= 0
			&& column < stateField[row].length
			&& (value == 'e' || value == 'q' || value == 'b'))
			stateField[row][column] = value;
	}
	
	public char[][] getStateField () {

		return stateField;
	}
}
