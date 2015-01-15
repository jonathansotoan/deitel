// Exercise 16.5, 16.6 and 16.7
package deitel.searchAndOrdering;

import java.util.Random;

public abstract class Ordering {
	protected int[] data;
	private static final Random RAN = new Random ();

	public Ordering (final int SIZE, final boolean BIG) {
		data = new int[SIZE];
		
		for (int j = 0; j < SIZE; ++j)
			if (BIG)
				data[j] = RAN.nextInt (1000);
			else
				data[j] = RAN.nextInt (90) + 10;
	}
	
	@Override
	public String toString () {
		StringBuilder results = new StringBuilder ();
		
		for (int j = 0; j < data.length; ++j)
			results.append (data[j] + " ");
		
		return results.toString ();
	}
	
	public abstract void order ();
}
