// ejercicio 7.22
import java.awt.Graphics;
import javax.swing.JPanel;

public class HorseMovesPanel extends JPanel {
	HorseMovesModel hmm;
	
	public HorseMovesPanel (byte initialRow, byte initialColumn) {
		hmm = new HorseMovesModel (initialRow, initialColumn);
		hmm.makeAllMoves ();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		for (byte i = 0; i < 8; ++i) {
			for (byte j = 0; j < 8; ++j) {
				if (hmm.getField ()[i][j])
					g.fillRect (j * 50, i * 50, 50, 50);
				else
					g.drawRect (j * 50, i * 50, 50, 50);
			}
		}
	}
}
