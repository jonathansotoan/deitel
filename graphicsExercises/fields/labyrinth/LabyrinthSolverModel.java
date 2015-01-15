//Exercise 15.20
package deitel.graphicsExercises.fields.labyrinth;

import java.awt.Point;
import java.util.Random;

public class LabyrinthSolverModel {
	private Labyrinth labyrinth;
	private char identifier;

	public LabyrinthSolverModel () {
		this (createRandomLabyrinth (new char[12][12], '#', '.'), 'x');
	}

	public LabyrinthSolverModel (Labyrinth labyrinthToResolve, char pathIdentifier) {
		labyrinth = labyrinthToResolve;
		identifier = pathIdentifier != labyrinth.getWallSymbol () &&
							pathIdentifier != labyrinth.getFreeSpaceSymbol () &&
							pathIdentifier != '-' ? pathIdentifier : 'x';
	}
	
	public final char getIdentifier () {
		return identifier;
	}
	
	@Override
	public String toString () {
		String results = String.format ("Start point: %s End point: %s\n",
													labyrinth.getStartPoint (),
													labyrinth.getEndPoint ());
		
		for (byte j = 0; j < labyrinth.getRows (); ++j) {
			for (byte k = 0; k < labyrinth.getColumns (); ++k)
				results += labyrinth.getValueAt (j, k) + " ";
				
			results = results.concat ("\n");
		}
		
		return results + "The labyrinth is " + (isResolved () ? "" : "not ") + "resolved";
	}
	
	public final void solve () {
		solve (labyrinth.getStartPoint ().x, labyrinth.getStartPoint ().y);
	}
	
	private final void solve (final int row, final int column) {
		if (column == labyrinth.getEndPoint ().y && row == labyrinth.getEndPoint ().x)
			labyrinth.setValueAt (row, column, getIdentifier ());
		else
			if (labyrinth.getValueAt (row - 1, column) == labyrinth.getFreeSpaceSymbol ()) {
				labyrinth.setValueAt (row, column, getIdentifier ());
				solve (row - 1, column);
			} else if (labyrinth.getValueAt (row, column + 1) == labyrinth.getFreeSpaceSymbol ()) {
				labyrinth.setValueAt (row, column, getIdentifier ());
				solve (row, column + 1);
			} else if (labyrinth.getValueAt (row + 1, column) == labyrinth.getFreeSpaceSymbol ()) {
				labyrinth.setValueAt (row, column, getIdentifier ());
				solve (row + 1, column);
			} else if (labyrinth.getValueAt (row, column - 1) == labyrinth.getFreeSpaceSymbol ()) {
				labyrinth.setValueAt (row, column, getIdentifier ());
				solve (row, column - 1);
			} else {
				labyrinth.setValueAt (row, column, '-');
				Point identifierPosition = whereIs (row, column, getIdentifier ());
				
				if (identifierPosition.x != -1) {
					labyrinth.setValueAt (identifierPosition.x, identifierPosition.y, labyrinth.getFreeSpaceSymbol ());
					solve (identifierPosition.x, identifierPosition.y);
				}
			}
	}
	
	private final Point whereIs (int row, int column, char value) {
		if (labyrinth.getValueAt (row - 1, column) == value)
			return new Point (row - 1, column);

		if (labyrinth.getValueAt (row + 1, column) == value)
			return new Point (row + 1, column);
			
		if (labyrinth.getValueAt (row, column - 1) == value)
			return new Point (row, column - 1);
		
		if (labyrinth.getValueAt (row, column + 1) == value)
			return new Point (row, column + 1);
		
		//else
		return new Point (-1, -1);//If the current labyrinth hasn't solution
	}
	
	public final boolean isResolved () {
		if (labyrinth.getValueAt (labyrinth.getEndPoint ().x, labyrinth.getEndPoint ().y) == getIdentifier ())
			return true;
		else
			return false;
	}
	
	public static Labyrinth createRandomLabyrinth (char[][] field, final char wall, final char freeSpace) {
		Random ran = new Random ();
		Point initPoint, finalPoint;
		
		if (ran.nextBoolean ())
			initPoint = new Point (0, ran.nextInt (field[0].length - 2) + 1);
		else
			initPoint = new Point (ran.nextInt (field.length - 2) + 1, 0);
		
		if (ran.nextBoolean ())
			finalPoint = new Point (field[0].length - 1, ran.nextInt (field[0].length - 2) + 1);
		else
			finalPoint = new Point (ran.nextInt (field.length - 2) + 1, field.length - 1);
		
		//Note: the freeSpace has 75% posibilites to be selected (except in the borders)
		for (byte j = 0; j < field.length; ++j)
			for (byte k = 0; k < field[0].length; ++k)
				if (j == 0 || j == field.length - 1 || k == 0 || k == field[j].length - 1)
					field[j][k] = wall;//this instruction is to set wall at the border
				else if (ran.nextBoolean ())
					if (ran.nextBoolean ())
						field[j][k] = freeSpace;
					else
						field[j][k] = wall;
				else
					field[j][k] = freeSpace;
		
		field[initPoint.x][initPoint.y] = freeSpace;
		field[finalPoint.x][finalPoint.y] = freeSpace;
		
		return new Labyrinth (field, wall, freeSpace, initPoint.x, initPoint.y, finalPoint.x, finalPoint.y);
	}
}
