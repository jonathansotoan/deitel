// Exercise 17.12 - 17.14
package deitel.dataStructure;

public class InfixAndPostfixOperations {

	public static String convertToPostfix (String infixStr) {
		StringBuffer infix = new StringBuffer (infixStr);
		StringBuffer postfix = new StringBuffer ();
		Pila stack = new Pila ();
		
		stack.push ('(');
		infix.append (')');
		
		for (int j = 0; j < infix.length () && !stack.estaVacia (); ++j) {
			if( Character.isDigit( infix.charAt( j ) ) ) {
				int[] nextNumberAndIndex = getNextNumber( infix, j );
				postfix.append( nextNumberAndIndex[0] + " " );
				j = nextNumberAndIndex[1] - 1;
			} else if( infix.charAt( j ) == '(' )
				stack.push( '(' );
			else if( isOperator( infix.charAt( j ) ) ) {
				while( isOperator( (Character) stack.nextValue() ) &&
						( greaterPrecedence( (Character) stack.nextValue(), infix.charAt( j ) ) ||
						equalPrecedence( (Character) stack.nextValue(), infix.charAt( j ) ) ) )
					postfix.append( stack.pop() );
				
				stack.push( infix.charAt( j ) );
			} else if( infix.charAt( j ) == ')') {
				while( (Character) stack.nextValue() != '(' )
					postfix.append( stack.pop() );
				
				stack.pop();
			}
		}
		
		return postfix.toString ();
	}
	
	private static boolean isOperator( final char VALUE ) {
		switch( VALUE ) {
			case '+':
			case '-':
			case '*':
			case '/':
			case 94://exponentiation
			case '%':
				return true;
		}
			
		return false;
	}
	
	private static boolean greaterPrecedence( final char OPERATOR1, final char OPERATOR2 ) {
			switch( OPERATOR1 ) {
				case '*':
				case '/':
				case 94://exponentiation
				case '%':
					if( OPERATOR2 == '+' || OPERATOR2 == '-')
						return true;
			}
			
			return false;
				
	}
	
	private static boolean equalPrecedence( final char OPERATOR1, final char OPERATOR2 ) {
		switch( OPERATOR1 ) {
			case '*':
			case '/':
			case 94://exponentiation
			case '%':
				if( OPERATOR2 == '+' || OPERATOR2 == '-' )
					return false;
			
			case '+':
			case '-':
				if( OPERATOR2 == '*' || OPERATOR2 == '/' || OPERATOR2 == 94 || OPERATOR2 == '%' )
					return false;
		}
		
		return true;
	}
	
	public static int postfixEvaluator( String postfixStr ) {
		StringBuffer postfix = new StringBuffer (postfixStr);
		Pila stack = new Pila();
		int results = -1;
		
		postfix.append( ')' );

		for( int j = 0; j < postfix.length(); ++j ) {
			if( Character.isDigit( postfix.charAt( j ) ) ) {
				int[] numberAndIndex = getNextNumber( postfix, j );
				stack.push( numberAndIndex[0] );
				j = numberAndIndex[1] - 1;
			} else if( isOperator( postfix.charAt( j ) ) ) {
				int x = (Integer) stack.pop();
				int y = (Integer) stack.pop();
				
				stack.push( calculate( y, postfix.charAt( j ), x ) );
			} else if( postfix.charAt( j ) == ')' )
				results = (Integer) stack.pop();
		}

		return results;
	}
	
	private static int calculate(final int OP1, final char OPERATOR, final int OP2) {
		switch( OPERATOR ) {
			case '*':
				return OP1 * OP2;
			case '/':
				return OP1 / OP2;
			case 94://exponentiation
				return (int) Math.pow( OP1, OP2 );
			case '%':
				return OP1 % OP2;
			case '+':
				return OP1 + OP2;
			case '-':
				return OP1 - OP2;
			default:
				return -1;
		}
	}
	
	//Returns an int array with a size of 2: index 0 has the next number and index 1 has the index such must continue.
	private static int[] getNextNumber( final StringBuffer SB, final int INDEX ) {
		StringBuffer results = new StringBuffer();
		int index = 0;

		for( int j = INDEX;
				SB.charAt( j ) != ' ' && !isOperator( SB.charAt( j ) ) && SB.charAt( j ) != ')' && j < SB.length();
				++j ) {
			results.append( SB.charAt( j ) );
			index = j;
		}
		
		int[] values = {Integer.parseInt( results.toString() ), index + 1};
		return values;
	}
	
	public static void main (String args[]) {
		//System.out.println (convertToPostfix ("(6 + 2) * 5 - 8 / 4"));
		System.out.println( postfixEvaluator( convertToPostfix ("(6 + 2) * 5 - 8 / 4") ) );
		//System.out.println( postfixEvaluator( "65 16 + 10 * 80 40 / - " ) );
	}
}
