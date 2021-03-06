// ejercicio 7.24
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class draws the queens position model in a panel.
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 0.01 12/06/2013
 * @see QueensPosition
 * @see QueensPositionModel
 */
public class QueensPositionPanel extends JPanel {
	QueensPositionModel qpm = new QueensPositionModel ();

	/**
	 * this calls QueensPositionPanel class' putAllQueens method with the specified queens.
	 *
	 * @param numberOfQueens as a number <code><b>byte</b></code> which defines the number of queens that will be 
	 * in te field.
	 */
	public QueensPositionPanel (byte numberOfQueens) {
		qpm.putAllQueens (numberOfQueens);
	}
	
	/**
	 * This method is called when the panel is being refreshed.
	 *
	 * @param g as <code></b>Graphics</b></code>.
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		for (byte i = 0; i < 8; ++i)
			for (byte j = 0; j < 8; ++j)
				if (qpm.getStateField ()[i][j] == 'q')
					g.fillRect (j * 50, i * 50, 50, 50);
				else
					g.drawRect (j * 50, i * 50, 50, 50);
	}
}
