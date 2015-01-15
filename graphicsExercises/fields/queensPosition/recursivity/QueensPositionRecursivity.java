//Exercise 15.15
package deitel.graphicsExercises.fields.queensPosition.recursivity;

public class QueensPositionRecursivity {
	private char[][] stateField;// 'q' it means that there is a qeen
											// 'e' it is empty

	public QueensPositionRecursivity () {
		stateField = new char[8][8];
		reinitField ();
	}

	/**
	 * This method reinits the state field.
	 */
	public void reinitField () {
		reinitField ((byte) 0, (byte) 0);
	}
	
	private void reinitField (byte row, byte column) {
		if (column >= stateField[0].length)
			reinitField ((byte) (row + 1), (byte) 0);
		else if (row < stateField.length) {
			stateField[row][column] = 'e';
			reinitField ((byte) row, (byte) (column + 1));
		}
	}
	
	public String toString () {
		String results = "";
		for (byte j = 0; j < stateField.length; ++j) {
			for (byte k = 0; k < stateField[0].length; ++k)
				results += stateField[j][k] + " ";
			
			results = results.concat ("\n");
		}
		
		return results;
	}
	
	public void putAllQueens () {
		putAllQueens ((byte) 0, (byte) 0, (byte) 8);
	}
	
	private void putAllQueens (final byte row, final byte column, final byte numberOfQueens) {
		if (numberOfQueens > 0) {
			if (column >= stateField[0].length)
				putAllQueens ((byte) (row + 1), (byte) 0, numberOfQueens);
			else if (row < stateField.length && stateField[row][column] == 'e') {
				stateField[row][column] = 'q';
				
				if (isCorrect ()) {
					putAllQueens (row, (byte) (column + 1), (byte) (numberOfQueens - 1));
					
					if (!isComplete ()) {
						stateField[row][column] = 'e';
						putAllQueens (row, (byte) (column + 1), numberOfQueens);
					}
				} else {
					stateField[row][column] = 'e';
					putAllQueens (row, (byte) (column + 1), numberOfQueens);
				}
			}
		}
	}
	
	public boolean isCorrect () {
		//this check the queens in the field horizontally
		for (byte j = 0, numberOfQueens = 0; j < stateField.length; ++j, numberOfQueens = 0) {
			for (byte k = 0; k < stateField[j].length; ++k)
				if (stateField[j][k] == 'q')
					++numberOfQueens;
			
			if (numberOfQueens > 1)
				return false;
		}
		
		//this check the queens in the field vertically
		for (byte j = 0, numberOfQueens = 0; j < stateField[0].length; ++j, numberOfQueens = 0) {
			for (byte k = 0; k < stateField.length; ++k)
				if (stateField[k][j] == 'q')
					++numberOfQueens;
			
			if (numberOfQueens > 1)
				return false;
		}
		
		//this check the queens in the field diagonal (from left-top to right-bottom)
		for (byte j = 0; j < stateField.length; ++j) {
			if (!isCorrectDiagonalLeftToRight ((byte) 0, j))
				return false;
			
			if (!isCorrectDiagonalLeftToRight (j, (byte) 0))
				return false;
		}
		
		//this check the queens in the field diagonal (from right-top to left-bottom)
		for (byte j = 0; j < stateField.length; ++j) {
			if (!isCorrectDiagonalRightToLeft ((byte) 0, j))
				return false;
			
			if (!isCorrectDiagonalRightToLeft (j, (byte) (stateField.length - 1)))
				return false;
		}
		
		return true;
	}
	
	//this check the queens in the field diagonal (from left-top to right-bottom)
	private boolean isCorrectDiagonalLeftToRight (final byte initialRow, final byte initialColumn) {
		return isCorrectDiagonal (initialRow, initialColumn, 'r');
	}
	
	//this check the queens in the field diagonal (from right-top to left-bottom)
	private boolean isCorrectDiagonalRightToLeft (final byte initialRow, final byte initialColumn) {
		return isCorrectDiagonal (initialRow, initialColumn, 'l');
	}
	
	//This is the core method that calculate if the queens position is correct
	private boolean isCorrectDiagonal (byte initialRow, byte initialColumn, final char direction) {
		byte numberOfQueens = 0;
		
		for (; initialRow < stateField.length && initialColumn < stateField[initialRow].length && initialColumn >= 0; ++initialRow, initialColumn = direction == 'r' ? (byte) (initialColumn + 1) : (byte) (initialColumn - 1))
			if (stateField[initialRow][initialColumn] == 'q')
				++numberOfQueens;
		
		return numberOfQueens < 2;
	}
	
	//returns true if the field has 8 queens
	public boolean isComplete () {
		byte numberOfQueens = 0;
	
		for (byte j = 0; j < stateField.length; ++j)
			for (byte k = 0; k < stateField[j].length; ++k)
				if (stateField[j][k] == 'q')
					++numberOfQueens;
		
		return numberOfQueens == 8;
	}
	
	public static void main (String args[]) {
		QueensPositionRecursivity qpr = new QueensPositionRecursivity ();
		qpr.putAllQueens ();
		System.out.println (qpr);
	}
}
