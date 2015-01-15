// Exercise 16.5 and 16.6
package deitel.searchAndOrdering;

public class BubbleOrdering extends Ordering {
	
	public BubbleOrdering (final int SIZE) {
		super (SIZE, false);
	}
	
	@Override
	public void order () {
		boolean interchangeMade = true;
	
		for (int j = 1; j < data.length && interchangeMade; ++j) {
			interchangeMade = false;
		
			for (int k = 0; k < data.length - j; ++k)
				if (data[k] > data[k + 1]) {
					interchange (k, k + 1);
					interchangeMade = true;
				}
		}
	}
	
	private final void interchange (final int firstIndex, final int secondIndex) {
		final int temp = data[firstIndex];
		data[firstIndex] = data[secondIndex];
		data[secondIndex] = temp;
	}
	
	public static void main (String args[]) {
		BubbleOrdering bo = new BubbleOrdering (20);
		System.out.println ("Without order: " + bo);
		bo.order ();
		System.out.println ("Ordered:       " + bo);
	}
}
