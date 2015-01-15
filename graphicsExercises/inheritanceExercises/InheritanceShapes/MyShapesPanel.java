//Exercise 10.1
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class MyShapesPanel extends JPanel {
	private MyShape[] shapes;

	public MyShapesPanel (int numberOfShapes) {
		Random ran = new Random ();
		shapes = new MyShape[numberOfShapes];
		
		for (int j = 0; j < shapes.length; ++j)
			switch (j % 3) {
				case 0:
					shapes[j] = new MyLine (ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)));
					break;
				
				case 1:
					shapes[j] = new MyRect (ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)),
													ran.nextBoolean ());
					break;
				
				case 2:
					shapes[j] = new MyOval (ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													ran.nextInt (300),
													new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)),
													ran.nextBoolean ());
					break;
			}
	}
	
	public void paintComponent (Graphics g) {
		for (int j = 0; j < shapes.length; ++j)
			shapes[j].draw (g);
	}
	
	public String toString () {
		return String.format ("Lines: %d, rects: %d, ovals: %d",
										shapes.length % 3 > 0 ? shapes.length / 3 + 1 : shapes.length / 3,
										shapes.length % 3 == 2 ? shapes.length / 3 + 1 : shapes.length / 3,
										shapes.length / 3);
	}
}
