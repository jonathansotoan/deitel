// Cap. 6
import java.util.Scanner;

public class MCDTest {
	
	public static void main (String args[]) {
		char option;
		Scanner input = new Scanner (System.in);

		do {
			System.out.println ("Insert 1 number");
			int n1 = input.nextInt ();
			System.out.println ("Insert other number");
			int n2 = input.nextInt ();
			
			if (n1 == 0)
				for (int j = 0; j < 10000; j++)
					System.out.printf ("%d, ", Calculating.mcdE(j, j * j / 2));
			else if (n2 == 0)
				for (int i = 0; i < 10000; i++)
					System.out.printf ("%d, ", Calculating.mcd(i, i * i / 2));
			else {
				System.out.printf ("The MCD between %d and %d is %d\n", n1, n2, Calculating.mcd (n1, n2));
				System.out.printf ("Again: %d\n", Calculating.mcdE (n1, n2));
			}
						
				System.out.println ("\nInsert 1 to continue, 0 to exit");
				option = input.next ().charAt (0);
		} while (option != '0');
	}
}
