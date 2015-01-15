import java.awt.Graphics;
import javax.swing.JPanel;

public class TurtleGraphicsPanel extends JPanel {
	// false means that the plume is up
	private boolean plume;
	// it indicates the cardinal direction (North = 0, West = 1, South = 2 or East = 3)
	/****************************************************
	*                      North                        *
	*                        |                          *
	*                West  -   -  East                  *
	*                        |                          *
	*                      South                        *
	****************************************************/
	private byte currentCardinalDirection = 3;
	private byte currentPosition[] = {0, 0};
	private boolean field[][] = new boolean[20][20];

	public void setCommand (int... commands) {
		
		switch (commands[0]) {
			case 1:
				plume = false;
				break;
			case 2:
				plume = true;
				break;
			case 3:
				if (currentCardinalDirection == 0)
					currentCardinalDirection = 3;
				else
					--currentCardinalDirection;
				break;
			case 4:
				if (currentCardinalDirection == 3)
					currentCardinalDirection = 0;
				else
					++currentCardinalDirection;
				break;
			case 5:
				for (byte j = 0; j < commands[1]; ++j) {
					switch (currentCardinalDirection) {
						case 0:
							if (currentPosition[1] > 0)
								--currentPosition[1];
							break;
						case 1:
							if (currentPosition[0] > 0)
								--currentPosition[0];
							break;
						case 2:
							if (currentPosition[1] < field.length - 1)
								++currentPosition[1];
							break;
						case 3:
							if (currentPosition[0] < field[0].length - 1)
								++currentPosition[0];
							break;
					}

					field[currentPosition[0]][currentPosition[1]] =
									plume ? true : field[currentPosition[0]][currentPosition[1]];

				}
				break;
			default:
				System.out.println ("It's a bad command");
		}
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		for (byte i = 0; i < field.length; ++i) {
			for (byte j = 0; j < field.length; ++j) {
				if (field[i][j])
					g.fillRect (i * 20, j * 20, 20, 20);
				else
					g.drawRect (i * 20, j * 20, 20, 20);
			}
		}
	}
}
