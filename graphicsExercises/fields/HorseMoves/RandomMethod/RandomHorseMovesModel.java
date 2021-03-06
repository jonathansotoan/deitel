// ejercicio 7.23
import java.util.Random;

public class RandomHorseMovesModel {
	private byte horizontal[] = {2, 1, -1, -2, -2, -1, 1, 2};
	private byte vertical[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	private boolean field[][] = new boolean[8][8];
	private byte currentPosition[] = new byte[2];

	// it sets the horse's initial position
	public RandomHorseMovesModel (byte initialRow, byte initialColumn) {
		currentPosition[0] = initialRow;
		currentPosition[1] = initialColumn;
		field[initialRow][initialColumn] = true;
	}
	
	// it's an empty constructor
	public RandomHorseMovesModel () {
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
		
	// short version
	public boolean isPossibleMove (byte numberOfMove, byte xPosition, byte yPosition) {
		return isPossibleMove (horizontal[numberOfMove], vertical[numberOfMove], xPosition, yPosition);
	}
	
	// Make a specific move
	public void makeAMove (byte horizontalMove, byte verticalMove) {
		currentPosition[0] += horizontalMove;
		currentPosition[1] += verticalMove;
		field[currentPosition[0]][currentPosition[1]] = true;
	}
		
	// short version
	public void makeAMove (byte numberOfMove) {
		makeAMove (horizontal[numberOfMove], vertical[numberOfMove]);
	}
	
	// returns the number of successful moves. The moves should be 64
	public byte makeAllMoves () {
		byte squaresVisited = 1;
		Random randomNumber = new Random ();
	
		for (byte j = 0; j < 64; ++j) {
			byte nextStep = (byte) randomNumber.nextInt (8);
			boolean[] possibleMoves = {true, true, true, true, true, true, true, true};
			
			while (hasMorePossibleMoves (possibleMoves)) {
				if (!isPossibleMove (nextStep, currentPosition[0], currentPosition[1])) {
					possibleMoves[nextStep] = false;
					nextStep = (byte) randomNumber.nextInt (8);
				} else {
					makeAMove (nextStep);
					++squaresVisited;
				}
			}
		}
		
		return squaresVisited;
	}
	
	// returns true if the possibleMoves array has at least a true value
	public boolean hasMorePossibleMoves (boolean[] possibleMoves) {
		boolean moves = false;
		
		for (byte j = 0; j < possibleMoves.length; ++j) {
			if (possibleMoves[j]) {
				moves = true;
				break;
			}
		}
		
		return moves;
	}
	
	// it's like a reinstance. It can be used like a "setRow" and "setColumn" method
	public void restartAll (byte row, byte column) {
		for (byte j = 0; j < 8; ++j) {
			for (byte k = 0; k < 8; ++k) {
				field[j][k] = false;
			}
		}
		
		currentPosition[0] = row;
		currentPosition[1] = column;
		field[currentPosition[0]][currentPosition[1]] = true;
	}
}
