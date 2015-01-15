import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PanelDibujo extends JPanel {
	char opcion;
	
	public PanelDibujo (char opcion) {
		this.opcion = opcion;
	}
	
	public void definirOpcion (char opcion) {
		this.opcion = opcion;
	}

	public void paintComponent (Graphics g) {
			switch (opcion) {
				case '1':
					for (short j = 0; j < 20; j++)
						g.drawLine (0, 0, (getWidth () / 20) * j, getHeight () - (getHeight () / 20) * j);
					break;
					
				case '2':
					for (short j = 0; j < 20; j++) {
						g.drawLine (0, 0, (getWidth () / 20) * j, getHeight () - (getHeight () / 20) * j);
						g.drawLine (0, getHeight (), (getWidth () / 20) * j, (getHeight () / 20) * j);
						g.drawLine (getWidth (), 0, (getWidth () / 20) * j, (getHeight () / 20) * j);
						g.drawLine (getWidth (), getHeight (), (getWidth () / 20) * j, getHeight () - (getHeight () / 20) * j);
					}
					break;
					
				case '3':
					for (short j = 0; j < 20; j++)
						g.drawLine (0, (getHeight () / 20) * j, (getWidth () / 20) * (j + 1), getHeight ());
					break;
					
				case '4':
					for (short j = 0; j < 20; j++) {
						g.drawLine (0, (getHeight () / 20) * j, (getWidth () / 20) * j, getHeight ());
						g.drawLine ((getWidth () / 20) * j, getHeight (), getWidth (), (getHeight () / 20) * (20 - j));
						g.drawLine (getWidth (), (getHeight () / 20) * (20 - j), (getWidth () / 20) * (20 - j), 0);
						g.drawLine ((getWidth () / 20) * (20 - j), 0, 0, (getHeight () / 20) * j);
					}
					break;
			}
	}
}
