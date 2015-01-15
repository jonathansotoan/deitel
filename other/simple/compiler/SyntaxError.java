// Exercise 17.29.f
// This is an Exception
package deitel.other.simple.compiler;

public class SyntaxError extends RuntimeException {

	public SyntaxError() {
		this( "No details" );
	}

	public SyntaxError( final String ERROR_DESCRIPTION ) {
		super( "You have an error in the syntax: ".concat( ERROR_DESCRIPTION ) );
	}
}
