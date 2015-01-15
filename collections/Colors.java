//Exercise 19.15
package deitel.collections;

import java.util.HashMap;
import java.awt.Color;

public class Colors {
	private HashMap< String, Color > map;
	
	public Colors() {
		map = new HashMap< String, Color >( 13 );
		map.put( "green", Color.GREEN );
		map.put( "black", Color.BLACK );
		map.put( "blue", Color.BLUE );
		map.put( "cyan", Color.CYAN );
		map.put( "dark gray", Color.DARK_GRAY );
		map.put( "gray", Color.GRAY );
		map.put( "light gray", Color.LIGHT_GRAY );
		map.put( "magenta", Color.MAGENTA );
		map.put( "orange", Color.ORANGE );
		map.put( "pink", Color.PINK );
		map.put( "white", Color.WHITE );
		map.put( "red", Color.RED );
		map.put( "yellow", Color.YELLOW );
	}
	
	public final Color getColor( final String color ) {
		return map.get( color );
	}
	
	public static void main( String args[] ) {
		Colors c = new Colors();
		System.out.println ( c.getColor( "red" ) );
		System.out.println ( c.getColor( "magenta" ) );
		System.out.println ( c.getColor( "brown" ) );
	}
}
