// Exercise 17.27
package deitel.other.simple.compiler;

public final class Symbol {
	private double symbol;
	private char type;
	private int ubication;

	public Symbol( double symbol, char type, int ubication ) {
		this.symbol = symbol;
		this.type = type;
		this.ubication = ubication;
	}
	
	public double getSymbol() {
		return symbol;
	}
	
	public char getType() {
		return type;
	}
	
	public int getUbication() {
		return ubication;
	}
}
