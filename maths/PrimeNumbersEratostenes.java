// ejercicio 7.27, la criba de Eratóstenes
public class PrimeNumbersEratostenes {
	public boolean[] primeNumbers;
	
	public PrimeNumbersEratostenes (short size) {
		primeNumbers = new boolean[size];
		
		for (short j = 2; j < size; ++j)
			primeNumbers[j] = true;
	}
	
	public void foundPrimeNumbers () {
		for (short j = 2; j < primeNumbers.length; ++j)
			if (primeNumbers[j])
				for (short k = 2; j * k < primeNumbers.length; ++k)
					primeNumbers[j * k] = false;
	}
	
	public static void main (String args[]) {
		PrimeNumbersEratostenes pne = new PrimeNumbersEratostenes ((short) 32000);
		pne.foundPrimeNumbers ();
		
		for (short j = 0; j < pne.primeNumbers.length; ++j)
			if (pne.primeNumbers[j])
				System.out.printf ("The %d number is a prime number.\n", j);
	}
}
