//Exercise 10.1
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InheritanceShapesTest {

	public static void main (String args[]) {
		Scanner in = new Scanner (System.in);
		JFrame wndw = new JFrame ("Inheritance shapes test");
		JLabel label;
		
		System.out.println ("Please insert the number of shapes that you like");
		MyShapesPanel msp = new MyShapesPanel (in.nextInt ());
		label = new JLabel (msp.toString ());

		wndw.add (msp, BorderLayout.CENTER);
		wndw.add (label, BorderLayout.SOUTH);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (300, 300);
		wndw.setVisible (true);
	}
}
