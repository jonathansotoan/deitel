//Exercise 18.8
package deitel.generics;

public class Pair< F, S > {
	private F firstElement;
	private S secondElement;

	public Pair( final F FIRST, final S SECOND ) {
		setFirst( FIRST );
		setSecond( SECOND );
	}
	
	public final void setFirst( final F NEW_FIRST ) {
		firstElement = NEW_FIRST;
	}
	
	public final F getFirst() {
		return firstElement;
	}
	
	public final void setSecond( final S NEW_SECOND ) {
		secondElement = NEW_SECOND;
	}
	
	public final S getSecond() {
		return secondElement;
	}
	
	public String toString() {
		return String.format( "[ %s, %s ]", firstElement, secondElement );
	}
	
	public static void main( String args[] ) {
		Pair< Integer, Byte > pair1 = new Pair< Integer, Byte >( 259, (byte) 13 );
		Pair pair2 = new Pair< Double, Boolean >( 34.21, true );
		
		System.out.printf( "pair1: %s\npair2: %s\n", pair1, pair2 );
	}
}
