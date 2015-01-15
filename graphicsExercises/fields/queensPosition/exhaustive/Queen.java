// Ejercicio 7.25
public class Queen {

	public byte[] position = {-1, -1};
	private char[][] field;
	
	public Queen (char[][] field) {
		this.field = field;
	}
	
	public Queen (char[][] field, byte initialRow, byte initialColumn) {
		this.field = field;
		
		this.field[initialRow][initialColumn] = 'q';
		position[0] = initialRow;
		position[1] = initialColumn;
	}
	
	public void setPosition (byte row, byte column) {
		
		if (field[row][column] == 'e') {
			if (position[0] != -1)
				field[position[0]][position[1]] = 'e';
		
			this.field[row][column] = 'q';
			position[0] = row;
			position[1] = column;
		}
	}
	
	public void deletePosition () {
		if (position[0] != -1) {
			field[position[0]][position[1]] = 'e';
			position[0] = (byte) -1;
			position[1] = (byte) -1;
		}
	}
}
