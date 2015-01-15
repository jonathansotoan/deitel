//Exercise 15.20
package deitel.graphicsExercises.fields.labyrinth;

import java.awt.Point;

public class Labyrinth {
	private char[][] field;
	private char wallSymbol;
	private char freeSpaceSymbol;
	private final Point START_POINT;
	private final Point END_POINT;

	public Labyrinth () {
		this (new char[12][12], '#', '.', 0, 1, 11, 10);
	}

	public Labyrinth (final char[][] field,
							final char wallSymbol,
							final char freeSpaceSymbol,
							final int xS,
							final int yS,
							final int xE,
							final int yE) {
		setWallSymbol (wallSymbol);
		setFreeSpaceSymbol (freeSpaceSymbol);
		setField (field);
		START_POINT = new Point (xS, yS);
		END_POINT = new Point (xE, yE);
	}
	
	public final void setField (final char[][] newField) throws IllegalArgumentException {
		if (isCorrectField (newField, getWallSymbol (), getFreeSpaceSymbol ()))
			field = newField;
		else
			throw new IllegalArgumentException ("The field's symbols is incorrect");
	}
	
	public final char[][] getField () {
		return field;
	}
	
	public final static boolean isCorrectField (final char[][] fieldChecked, final char wallSym, final char freeSpaceSym) {
		for (byte j = 0; j < fieldChecked.length; ++j)
			for (byte k = 0; k < fieldChecked[j].length; ++k)
				if (fieldChecked[j][k] != wallSym && fieldChecked[j][k] != freeSpaceSym)
					return false;
		
		return true;
	}
	
	public final void setWallSymbol (final char newWallSymbol) {
		wallSymbol = newWallSymbol;
	}
	
	public final char getWallSymbol () {
		return wallSymbol;
	}
	
	public final void setFreeSpaceSymbol (final char newFreeSpaceSymbol) {
		freeSpaceSymbol = newFreeSpaceSymbol;
	}
	
	public final char getFreeSpaceSymbol () {
		return freeSpaceSymbol;
	}
	
	public final Point getStartPoint () {
		return START_POINT;
	}
	
	public final Point getEndPoint () {
		return END_POINT;
	}
	
	public final int getRows () {
		return field.length;
	}
	
	public final int getColumns () {
		return field[0].length;
	}
	
	public final char getValueAt (final int row, final int column) {
		if (row >= getRows () || row < 0 || column >= getColumns () || column < 0)
			return getWallSymbol ();
		else
			return field[row][column];
	}
	
	public final void setValueAt (final int row, final int column, final char newValue) {
		field[row][column] = newValue;
	}
}
