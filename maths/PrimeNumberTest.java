// Cap. 6
import javax.swing.JOptionPane;

public class PrimeNumberTest {

	public static void main (String args[]) {
		
		char option = JOptionPane.showInputDialog ("Insert:\nThe number 1 to execute the most expensive method to get all prime numbers between 0 and 1'000.000.\n2 to execute the fast method to get all prime numbers between 0 and 1'000.000").charAt (0);

		System.out.println ("The prime numbers between 0 and 1000000 are...");
		
		if (option == '1') {
			for (int i = 0; i < 1000000; i++)
				if (Calculating.isPrimeNumber (i))
					System.out.printf ("%d, ", i);
		} else {
			for (int j = 0; j < 1000000; j++)
				if (Calculating.isPrimeNumberOptimized (j))
					System.out.printf ("%d, ", j);
		}
	}
}
