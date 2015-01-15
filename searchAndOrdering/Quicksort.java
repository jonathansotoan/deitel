// Exercise 16.10
package deitel.searchAndOrdering;

public class Quicksort extends Ordering {

	public Quicksort (final int SIZE) {
		super (SIZE, false);
	}
	
	@Override
	public void order () {
		order (0, data.length - 1);
	}
	
	private void order (final int INIT, final int END) {
		int valueIndex = moveMax (INIT, INIT + 1, END);

		if (INIT <= END) {
			order (INIT, valueIndex - 1);
			order (valueIndex + 1, END);
		}
	}
	
	private int moveMax (final int VALUE_INDEX, final int INIT, final int END) {
		if (INIT <= END)
			for (int j = END; j >= INIT; --j)
				if (data[j] < data[VALUE_INDEX]) {
					interchange (j, VALUE_INDEX);
					return moveMin (j, VALUE_INDEX + 1, j - 1);
				}
		
		return VALUE_INDEX;
	}
	
	private int moveMin (final int VALUE_INDEX, final int INIT, final int END) {
		if (INIT <= END)
			for (int j = INIT; j <= END; ++j)
				if (data[j] > data[VALUE_INDEX]) {
					interchange (j, VALUE_INDEX);
					return moveMax (j, j + 1, VALUE_INDEX - 1);
				}

		return VALUE_INDEX;
	}
	
	private final void interchange (final int firstIndex, final int secondIndex) {
		final int temp = data[firstIndex];
		data[firstIndex] = data[secondIndex];
		data[secondIndex] = temp;
	}
	
	public static void main (String args[]) {
		Quicksort q = new Quicksort (20);
		System.out.println ("Without order: " + q);
		q.order ();
		System.out.println ("Ordered:       " + q);
	}
}
