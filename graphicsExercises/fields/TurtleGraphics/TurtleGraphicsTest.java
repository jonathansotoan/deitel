import javax.swing.JFrame;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TurtleGraphicsTest {

	public static void main (String args[]) {
		TurtleGraphicsPanel tg = new TurtleGraphicsPanel ();
		JFrame wndw = new JFrame ("It's a turtle graphics test");
		Scanner input = new Scanner (System.in);
		
		System.out.printf ("Insert a move command to the turtle:\n%s\n%s\n%s\n%s\n%s\n%s\n",
									"1 Set plume up (Don't paint)",
									"2 Set plume down (it'll paint)",
									"3 Turn right",
									"4 Turn left",
									"5,X Move X spaces forward",
									"9 End input");
		
		String option1;
		do {
			StringTokenizer options = new StringTokenizer (input.next (), ",");
			option1 = options.nextToken ();
			if (option1.equals ("5"))
				tg.setCommand (Integer.parseInt (option1), Integer.parseInt (options.nextToken ()));
			else
				tg.setCommand (Integer.parseInt (option1));
		} while (!option1.equals ("9"));
		
		wndw.add (tg);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (300, 300);
		wndw.setVisible (true);
	}
}
