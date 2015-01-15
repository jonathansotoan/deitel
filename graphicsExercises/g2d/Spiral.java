package deitel.graphicsExercises.g2d;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spiral extends JPanel {
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		int[] pointsX = new int[127];
		int[] pointsY = new int[127];
		
		for (byte j = 0; j < pointsX.length; ++j) {
			if ((j - 2) % 4 == 0)//2 and 6
				pointsX[j] = getWidth () / 2;
			else if ((j + 4) % 8 == 0) {//3 and 4
				pointsX[j] = getWidth () / 2 - (j / 8 + 1) * 50;
				pointsX[j - 1] = getWidth () / 2 - (2 * (getWidth () / 2 - pointsX[j])) / 3;
			} else if (j % 8 == 0) {//0 and 1
				pointsX[j] = getWidth () / 2 + (j / 8 + 1) * 50;
				
				if (j + 1 < pointsX.length)//1
					pointsX[j + 1] = getWidth () / 2 + ((j / 8 + 1) * 100) / 3;
			} else if ((j + 3) % 8 == 0)//5
				pointsX[j] = getWidth () / 2 - (getWidth () - 2 * pointsX[j - 1]) / 3;
			else if ((j - 7) % 8 == 0)//7
				pointsX[j] = (2 * pointsX[j - 7] + 100 - getWidth ()) / 3 + getWidth () / 2;
		}
		
		for (byte j = 0; j < pointsY.length; ++j) {
			if (j % 4 == 0)//0 and 4
				pointsY[j] = getHeight () / 2;
			else if ((j + 2) % 8 == 0) {//5 and 6
				pointsY[j] = getHeight () / 2 - (j / 8 + 1) * 50;
				pointsY[j - 1] = getHeight () / 2 - ((j / 8 + 1) * 100) / 3;
			} else if ((j - 2) % 8 == 0) {//1 and 2
				pointsY[j] = getHeight () / 2 + (j / 8 + 1) * 50;
				pointsY[j - 1] = getHeight () / 2 + ((j / 8 + 1) * 100) / 3;
			} else if ((j - 3) % 8 == 0)//3
				pointsY[j] = getHeight () / 2 + (2 * pointsY[j - 1] - getHeight ()) / 3;
			else if ((j - 7) % 8 == 0)//7
				pointsY[j] = getHeight () / 2 - (getHeight () - 2 * pointsY[j - 1]) / 3;
		}
		
		g.drawPolyline (pointsX, pointsY, pointsX.length);
	}
	
	public static void main (String args[]) {
		Spiral s = new Spiral ();
		JFrame wndw = new JFrame ("Spiral with polylines");
		
		wndw.add (s);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (502, 529);
		wndw.setVisible (true);
	}
}
