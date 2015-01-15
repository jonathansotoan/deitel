// Exercise 16.7
package deitel.searchAndOrdering;

public class TrayOrdering extends Ordering {

	public TrayOrdering (final int SIZE) {
		super (SIZE, true);
	}
	
	@Override
	public void order () {
		int[][] temp = new int[10][data.length];
		
		for (int j = 0; j < temp.length; ++j)
			for (int k = 0; k < temp[j].length; ++k)
				temp[j][k] = -1;

		for (byte h = 1; h < 4; ++h) {
			for (int j = 0; j < data.length; ++j) {
				String s = data[j] + "";
		
				setValue (temp,
								s.length () >= h ? Integer.parseInt (s.charAt (s.length () - h) + "") : 0,
								0,
								data[j]);
			}
		
			int index = -1;
			for (int j = 0; j < temp.length; ++j)
				for (int k = 0; k < temp[j].length; ++k)
					if (temp[j][k] != -1) {
						data[++index] = temp[j][k];
						temp[j][k] = -1;
					}
		}
	}
	
	private void setValue (int[][] target, final int ROW, final int COLUMN, final int VALUE) {
		if (target[ROW][COLUMN] == -1)
			target[ROW][COLUMN] = VALUE;
		else
			setValue (target, ROW, COLUMN + 1, VALUE);
	}
	
	public static void main (String args[]) {
		TrayOrdering to = new TrayOrdering (20);
		System.out.println ("Without order: " + to);
		to.order ();
		System.out.println ("Ordered:       " + to);
	}
}
