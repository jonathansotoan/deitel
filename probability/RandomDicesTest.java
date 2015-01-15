// Pagina 312

public class RandomDicesTest {

	public static void main (String args[]) {
		Craps c = new Craps();
		short[] sum = new short[11];
		
		for (int j = 0; j <= 36000; ++j)
			++sum[c.tirarDados () - 2];
			
		System.out.println ("Suma Frecuencia");
		
		for (byte i = 2; i < 13; i++)
			System.out.printf ("%4d %10d\n", i, sum[i - 2]);
	}
}
