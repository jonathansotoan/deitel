// Cap. 6
package deitel.maths;

public class Calculating {

	// Here starts MCD calculator
	public static int mcd (int number1, int number2) {
		int[] divisors1 = getAllFactors (number1);
		int[] divisors2 = getAllFactors (number2);
		int mcd = -1;
		
		for (int i = divisors1.length - 1; mcd == -1 && i >= 0; --i) {
			for (int j = divisors2.length - 1; mcd == -1 && j >= 0; --j)
				if (divisors1[i] == divisors2[j])
					mcd = divisors1[i];
		}
		
		return mcd;
	}
	
	//MCD método Euclidiano
	public static int mcdE (int number1, int number2) {
		if (number2 == 0)
			return number1;
		else if (number1 < number2)
			return mcdE (number1, number2 % number1);
		else
			return mcdE (number2, number1 % number2);
	}
	// Here ends MCD calculator
	
	// Here starts prime calculator
	public static boolean isPrimeNumber (int number) {
		boolean checkFactor = true;//it is used to check if it has already found a prime number
	
		for (int j = 2; j < number && checkFactor; j++)
			if (number % j == 0)
				checkFactor = false;
		
		return checkFactor;
	}
	
	public static boolean isPrimeNumberOptimized (int number) {
		boolean checkFactor = true;//it is used to check if it has already found a prime number
		double sqrt = Math.sqrt (number);
	
		for (int j = 2; j <= sqrt && checkFactor; j++)
			if (number % j == 0)
				checkFactor = false;
		
		return checkFactor;
	}
	// Here ends prime calculator

	// Here starts perfect number calculator
	public static boolean isPerfectNumber (int number) {
		int[] factors = getAllFactors (number);
		short total = 0;
		
		for (short j = 0; j < factors.length; j++)
			total += factors[j];
			
		if (total == number && number != 1)
			return true;
		else
			return false;
	}
	
	public static void printPerfectNumber (int number) {
		
		if (isPerfectNumber (number)) {
			int[] factors = getAllFactors (number);

			System.out.printf ("The number %d is a perfect number because ", number);
			
			for (short j = 0; j < factors.length - 2; j++)
				System.out.printf ("%d + ", factors[j]);
				
			System.out.printf ("%d = %d\n", factors[factors.length - 2], number);
		}
	}
	
	public static int[] getAllFactors (int number) {
		int size = 1;//Used to storage the array size

		for (int j = 2; j <= number; j++) {
			if (number % j == 0)
				++size;
		}
		
		int[] factors = new int[size];
		size = 1;//now, it's used as a factors array index
		factors[0] = 1;
		
		for (int j = 2; j <= number; j++) {
			if (number % j == 0) {
				factors[size] = j;
				++size;
			}
		}
		
		return factors;
	}
	// Here ends perfect number calculator
	
	public static boolean isPalindrome (String sentence) {
		return isPalindrome (sentence.replaceAll (" ", ""), 0);
	}
	
	private static boolean isPalindrome (String sentence, final int index) {
		if (index == sentence.length () - index - 1 || index == sentence.length () - index - 2)
			return true;
		else if (sentence.charAt (index) == sentence.charAt (sentence.length () - index - 1))
			return isPalindrome (sentence, index + 1);
		else
			return false;
	}
	
	public static String inverseString (String str) {
		return new String (inverseCharArray (str.toCharArray (), (byte) 0));
	}
	
	private static char[] inverseCharArray (char[] array, final byte index) {
		if (index >= array.length - index - 1)
			return array;
		else {
			char temp = array[index];
			array[index] = array[array.length - index - 1];
			array[array.length - index - 1] = temp;
			return inverseCharArray (array, (byte) (index + 1));
		}
	}
	
	public static int minRecursive (final int[] array) {
		return minRecursive (array, 1, array[0]);
	}
	
	private static int minRecursive (final int[] array, final int index, final int lowestNumber) {
		if (index == array.length)
			return lowestNumber;
		else if (lowestNumber > array[index])
			return minRecursive (array, index + 1, array[index]);
		else
			return minRecursive (array, index + 1, lowestNumber);
	}

	// declaración recursiva del método fibonacci
   public final static long fibonacci( long numero )
   {
      if ( ( numero == 0 ) || ( numero == 1 ) ) // casos base
         return numero;
      else // paso recursivo
         return fibonacci( numero - 1 ) + fibonacci( numero - 2 );
   } // fin del método fibonacci
   
   public final static boolean isNumber( String possibleNumber ) {
   	try {
   		Integer.parseInt( possibleNumber );
   		return true;
   	} catch( NumberFormatException numberFormatEx ) {
   		try {
				Double.parseDouble( possibleNumber );
				return true;
			} catch( NumberFormatException numberFormatEx2 ) {
				return false;
			}
   	}
   }
   
   //Exercise 18.7
   public static final < T > boolean isEqual( T object1, T object2 ) {
   	return object1.equals( object2 );
   }
}
