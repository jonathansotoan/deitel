// ejercicio 7.22 y 7.26
public class HeuristicHorseMovesModel {
	private byte horizontal[] = {2, 1, -1, -2, -2, -1, 1, 2};
	private byte vertical[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	private boolean field[][] = new boolean[8][8];
	private byte accessibility[][] = {{2, 3, 4, 4, 4, 4, 3, 2}, {3, 4, 6, 6, 6, 6, 4, 3},
												{4, 6, 8, 8, 8, 8, 6, 4}, {4, 6, 8, 8, 8, 8, 6, 4}, {4, 6, 8, 8, 8, 8, 6, 4},
												{4, 6, 8, 8, 8, 8, 6, 4},{3, 4, 6, 6, 6, 6, 4, 3}, {2, 3, 4, 4, 4, 4, 3, 2}};
	private byte backup[][] = new byte[8][8];
	private byte currentPosition[] = new byte[2];
	private byte initialPosition[] = {-1, -1};

	// it sets the horse's initial position
	public HeuristicHorseMovesModel (byte initialRow, byte initialColumn) {
		makeBackup ();
		initialPosition[0] = initialRow;
		initialPosition[1] = initialColumn;
		currentPosition[0] = initialRow;
		currentPosition[1] = initialColumn;
		field[initialRow][initialColumn] = true;
	}
	
	// empty constructor
	public HeuristicHorseMovesModel () {
		makeBackup ();
	}
	
	// it makes an accessibility array backup, which used in restartAll method
	public void makeBackup () {
		for (byte j = 0; j < 8; ++j) {
			for (byte k = 0; k < 8; ++k) {
				backup[j][k] = accessibility[j][k];
			}
		}
	}
	
	// return true if the move is possible
	public boolean isPossibleMove (byte horizontalMove, byte verticalMove, byte xPosition, byte yPosition) {
		if (xPosition + horizontalMove >= 0 &&
				xPosition + horizontalMove <= 7 &&
				yPosition + verticalMove >= 0 &&
				yPosition + verticalMove <= 7 &&
				!field[xPosition + horizontalMove][yPosition + verticalMove]) {
			return true;
		} else
			return false;
	}
	
	// Make a specific move
	public void makeAMove (byte horizontalMove, byte verticalMove) {
		// Refresh accessibility
		for (byte j = 0; j < 8; ++j) {
			if (isPossibleMove (j, currentPosition[0], currentPosition[1]))
				--accessibility[currentPosition[0] + horizontal[j]][currentPosition[1] + vertical[j]];
		}
		
		currentPosition[0] += horizontalMove;
		currentPosition[1] += verticalMove;
		field[currentPosition[0]][currentPosition[1]] = true;
	}
		
	// short version
	public void makeAMove (byte numberOfMove) {
		makeAMove (horizontal[numberOfMove], vertical[numberOfMove]);
	}
		
	// short version
	public boolean isPossibleMove (byte numberOfMove, byte xPosition, byte yPosition) {
		return isPossibleMove (horizontal[numberOfMove], vertical[numberOfMove], xPosition, yPosition);
	}
		
	// short version
	public byte possiblePositionX (byte numberOfMove, byte xPosition) {
		return (byte) (xPosition + horizontal[numberOfMove]);
	}
	
	// short version
	public byte possiblePositionY (byte numberOfMove, byte yPosition) {
		return (byte) (yPosition + vertical[numberOfMove]);
	}
	
	// the moves should be 64
	public void makeAllMoves () {
		boolean isClosed = false;
		byte squaresVisited = 1;
	
		for (byte j = 0; j < 64; ++j) {
			byte nextStep = getNextStep (currentPosition[0], currentPosition[1]);
			
			if (nextStep != -1) {
				makeAMove (nextStep);
				++squaresVisited;
			}
		}
		
		for (byte j = 0; j < 8 && !isClosed; ++j)
			if (currentPosition[0] + horizontal[j] == initialPosition[0]
				&& currentPosition[1] + vertical[j] == initialPosition[1])
				isClosed = true;
		
		System.out.printf ("The last position is %d, %d; is a closed walk? %b and the squares visited are %d\n",
									currentPosition[0],
									currentPosition[1],
									isClosed,
									squaresVisited);
	}

	// return the most optimal move's number (vertical[number] and horizontal[number])
	public byte getNextStep (byte xPosition, byte yPosition) {
		byte optimalMove = 9;
		byte nextStep = -1;
	
		for (byte k = 0; k < 8; ++k) {
			if (isPossibleMove (k, xPosition, yPosition) &&
				accessibility[possiblePositionX (k,xPosition)][possiblePositionY (k, yPosition)] < optimalMove) {
				
				nextStep = k;
				optimalMove = accessibility[possiblePositionX (k, xPosition)][possiblePositionY (k, yPosition)];
			}
		}
		
		return nextStep;
	}
	
	// it's like a reinstance. It can be used like a "setRow" and "setColumn" method
	public void restartAll (byte row, byte column) {
		for (byte j = 0; j < 8; ++j)
			for (byte k = 0; k < 8; ++k)
				field[j][k] = false;
		
		currentPosition[0] = row;
		currentPosition[1] = column;
		field[currentPosition[0]][currentPosition[1]] = true;
		
		if (initialPosition[0] == -1) {
			initialPosition[0] = row;
			initialPosition[1] = column;
		}

		for (byte j = 0; j < 8; ++j)
			for (byte k = 0; k < 8; ++k)
				accessibility[j][k] = backup[j][k];
	}
}
