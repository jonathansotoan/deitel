// Exercises 17.27 - 17.29
package deitel.other.simple.compiler;

import deitel.dataStructure.InfixAndPostfixOperations;
import deitel.dataStructure.Pila;
import deitel.other.simpletron.ver1_2.Simpletron;
import deitel.maths.Calculating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Formatter;

public class SimpleCompiler {
	private final Formatter OUT;
	private Vector symbols;
	private Vector LMSprogram;
	private Vector constants;
	private Vector strings;
	private Pila linesToReturn;
	private Pila linesToGoFor;
	private int varsAndConstantsCount;
	private int forCount;
	private int nextStringStart;
	private int[] flags;
	private Scanner reader;
	private String filePath;
	
	public SimpleCompiler( String fileName ) throws FileNotFoundException {
		reader = new Scanner( new File( fileName ) );
		//gets the file name excluding the extension and it adds new extension .lms
		StringTokenizer st = new StringTokenizer( fileName, "." );
		String absoluteFilePath = st.nextToken() + ".lms";
		OUT = new Formatter( absoluteFilePath );
		filePath = new File( absoluteFilePath ).getAbsolutePath();

		symbols = new Vector( 5, 5 );
		LMSprogram = new Vector( 5, 5 );
		constants = new Vector( 5, 5 );
		strings = new Vector( 5, 5 );
		
		linesToReturn = new Pila();
		linesToGoFor = new Pila();
		varsAndConstantsCount = 999;
		forCount = 1001;
		nextStringStart = 501;
		
		flags = new int[1000];
		for( short j = 0; j < 1000; ++j )
			flags[ j ] = -1;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void compile() {
		try {
			firstRun();
			secondRun();
		} catch( SyntaxError syntaxErr ) {
			System.err.printf( "%s\nIn line %f\n", syntaxErr.getMessage(), getLastLine() );
			System.exit( 1 );
		}
	}

	private void firstRun() throws SyntaxError {
		StringTokenizer tokenizer;
		boolean saveLineToReturn = false;
		boolean saveLineToNext = false;
		
		while( reader.hasNext() ) {
			tokenizer = new StringTokenizer( reader.nextLine() );
			
			if( tokenizer.hasMoreTokens() ) {
				String line = tokenizer.nextToken();
			
				if( Calculating.isNumber( line ) )
					addToList( new Symbol( Double.parseDouble( line ), 'L', LMSprogram.size() ) );
				else
					throw new SyntaxError( String.format( "The line \"%s\" is not a number", line ) );
			
				if( saveLineToReturn ) {
					linesToReturn.push( Integer.parseInt( line ) );
					saveLineToReturn = false;
				}
				
				if( saveLineToNext ) {
					flags[ ((ForData) linesToGoFor.pop()).FLAG_POSITION ] = Integer.parseInt( line );
					saveLineToNext = false;
				}
			
				String instruction = tokenizer.nextToken();

				if( instruction.equals( "input" ) )
					while( tokenizer.hasMoreTokens() )
						input( (int) getVar( tokenizer.nextToken() ) );
				else if( instruction.equals( "input_string" ) )
					if( tokenizer.hasMoreTokens() )
						input_string( tokenizer.nextToken() );
					else
						throw new SyntaxError( "It seems that does not exist a variable to save the string" );
				else if( instruction.equals( "if" ) ) {
					final String value1 = tokenizer.nextToken();
					final String comparator = tokenizer.nextToken();
					final String value2 = tokenizer.nextToken();
					
					if( !tokenizer.nextToken().equals( "goto" ) )
						throw new SyntaxError( "The \"goto\" word is in a wrong position or does not exist" );
					
					final String lineGoto = tokenizer.nextToken();
					
					if( !Calculating.isNumber( lineGoto ) )
						throw new SyntaxError( String.format( "The line \"%s\" is not a number", lineGoto ) );

					runIf( value1, comparator, value2, Integer.parseInt( lineGoto ) );
				} else if( instruction.equals( "let" ) ) {
					String var = tokenizer.nextToken();
					String infix = "";
					
					//check the = symbol
					if( !tokenizer.nextToken().equals( "=" ) )
						throw new SyntaxError( "It seems that the equal symbol (=) is not in the correct position or it does not exist." );

					while( tokenizer.hasMoreTokens() ) {
						String token = tokenizer.nextToken();

						if( Character.isLetter( token.charAt( 0 ) ) )
							infix += getVar( token ) + " ";
						else
							infix += token + " ";
					}
					
					let( var, infix );
				} else if( instruction.equals( "goto" ) )
					runGoto( tokenizer.nextToken() );
				else if( instruction.equals( "gosub" ) ) {
					runGoto( tokenizer.nextToken() );
					saveLineToReturn = true;
				} else if( instruction.equals( "return" ) )
					runGoto( linesToReturn.pop().toString() );
				else if( instruction.equals( "for" ) ) {
					String value1 = tokenizer.nextToken();

					if( !tokenizer.nextToken().equals( "=" ) )
						throw new SyntaxError( "It seems that the equal symbol (=) is not in the correct position or it does not exist." );

					String token = tokenizer.nextToken();
					String expression = "";

					while( !token.equals( "to" ) ) {
						expression += token + " ";

						if( tokenizer.hasMoreTokens() )
							token = tokenizer.nextToken();
						else
							throw new SyntaxError( "It seems that \"to\" word is not in the correct position or it does not exist." );
					}

					String value3 = tokenizer.nextToken();

					if( tokenizer.hasMoreTokens() ) {
						if( !tokenizer.nextToken().equals( "step" ) )
							throw new SyntaxError( "It seems that \"step\" keyword symbol is not in the correct position or it does not exist." );

						String value4 = tokenizer.nextToken();
						
						runFor( value1, expression, value3, value4);
					} else
						runFor( value1, expression, value3, "1" );
				} else if( instruction.equals( "next" ) ) {
					ForData fd = (ForData) linesToGoFor.obtenerValorDelFrente();
					
					let( fd.VAR, fd.VAR + " + " + fd.STEP );
					specialGoto( fd.IF_LINE, fd.VAR.charAt( 0 ) );
					saveLineToNext = true;
				} else if( instruction.equals( "print" ) )
					while( tokenizer.hasMoreTokens() )
						print( tokenizer.nextToken() );
				else if( instruction.equals( "print_string" ) ) {
					if( tokenizer.hasMoreTokens() ) {
						String value = tokenizer.nextToken();
						
						if( value.charAt( 0 ) == '"' ) {
							while( tokenizer.hasMoreTokens() )
								value += " " + tokenizer.nextToken();

							LMSprogram.add( 14000 + nextStringStart );
							saveAsASCII( value.replaceAll( "\"", "" ) );
						} else
							print_stringFromMemory( value );
					} else
						throw new SyntaxError( "It seems that does not exist a variable to save the string" );
				} else if( instruction.equals( "println" ) )
					LMSprogram.add( 12000 );
				else if( instruction.equals( "end" ) )
					LMSprogram.add( 43000 );
				else if( !instruction.equals( "rem" ) )
					throw new SyntaxError( String.format( "The instruction \"%s\" is incorrect", instruction ) );
			}
		}
		
		reader.close();
	}
	
	private void saveAsASCII ( final String str ) {
		strings.add( str.length () / 2.0 );
		nextStringStart += str.length() / 2 + 1;
		
		for (short j = 0; j < str.length (); j += 2) {
			double data = (int) str.charAt (j);
			data = j + 1 < str.length () ? data + ((int) str.charAt (j + 1) / 1000.0) : data;
			strings.add( (double) data );
		}
	}
	
	private void print_stringFromMemory( final String POSSIBLE_VAR ) {
		double var = (double) getVar( POSSIBLE_VAR );
		addVariableOrConstant( var, 'V' );
		LMSprogram.add( 13975 + 25 * (1000 - getUbication( var, 'V' )) );
		flags[ LMSprogram.size() - 1 ] = -2;
	}
	
	private void input_string( final String POSSIBLE_VAR ) {
		double var = (double) getVar( POSSIBLE_VAR );
		addVariableOrConstant( var, 'V' );
		//Use the var ubication to start to save the string to know where is it in another instructions
		LMSprogram.add( 12975 + 25 * (1000 - getUbication( var, 'V' )) );
		flags[ LMSprogram.size() - 1 ] = -2;
	}
	
	private void runFor( final String VALUE1, final String EXPRESSION, final String VALUE3, final String VALUE4 ){
		let( VALUE1, EXPRESSION );
		runIf( VALUE1, "==", VALUE3, 1001 );
		linesToGoFor.push( new ForData( VALUE1, getLastLine(), 1001, VALUE4, LMSprogram.size() - 1 ) );
	}
	
	private void runIf( final String VALUE1, final String COMPARATOR, final String VALUE2, final int LINE_GOTO ) {
		final double VAR1, VAR2;
		final char TYPE1, TYPE2;

		if( Calculating.isNumber( VALUE1 ) ) {
			VAR1 = Double.parseDouble( VALUE1 );
			TYPE1 = 'C';
		} else if( Character.isLetter( VALUE1.charAt( 0 ) ) ) {
			VAR1 = (double) getVar( VALUE1 );
			TYPE1 = 'V';
		} else
			throw new SyntaxError( String.format( "The variable or number that you want to use (\"%s\") is malformed", VALUE1 ) );

		if( Calculating.isNumber( VALUE2 ) ) {
			VAR2 = Double.parseDouble( VALUE2 );
			TYPE2 = 'C';
		} else if( Character.isLetter( VALUE2.charAt( 0 ) ) ) {
			VAR2 = (double) getVar( VALUE2 );
			TYPE2 = 'V';
		} else
			throw new SyntaxError( String.format( "The variable or number that you want to use (\"%s\") is malformed", VALUE2 ) );

		addVariableOrConstant( VAR1, TYPE1 );
		addVariableOrConstant( VAR2, TYPE2 );

		if( COMPARATOR.equals( "==" ) ) {
			LMSprogram.add( 20000 + getUbication( VAR1, TYPE1 ) );
			LMSprogram.add( 31000 + getUbication( VAR2, TYPE2 ) );

			if( getUbication( LINE_GOTO, 'L' ) == -1 ) {
				LMSprogram.add( 42000 );
				flags[ LMSprogram.size() - 1 ] = LINE_GOTO;
			} else
				LMSprogram.add( 42000 + getUbication( LINE_GOTO, 'L' ) );
		} else if( COMPARATOR.equals( "<" ) ) {
			LMSprogram.add( 20000 + getUbication( VAR1, TYPE1 ) );
			LMSprogram.add( 31000 + getUbication( VAR2, TYPE2 ) );

			if( getUbication( LINE_GOTO, 'L' ) == -1 ) {
				LMSprogram.add( 41000 );
				flags[ LMSprogram.size() - 1 ] = LINE_GOTO;
			} else
				LMSprogram.add( 41000 + getUbication( LINE_GOTO, 'L' ) );
		} else if( COMPARATOR.equals( ">" ) ) {
			LMSprogram.add( 20000 + getUbication( VAR2, TYPE2 ) );
			LMSprogram.add( 31000 + getUbication( VAR1, TYPE1 ) );

			if( getUbication( LINE_GOTO, 'L' ) == -1 ) {
				LMSprogram.add( 41000 );
				flags[ LMSprogram.size() - 1 ] = LINE_GOTO;
			} else
				LMSprogram.add( 41000 + getUbication( LINE_GOTO, 'L' ) );
		} else
			throw new SyntaxError( String.format( "The comparator %s is not supported", COMPARATOR ) );
	}
	
	private void let( final String POSSIBLE_VAR, final String EXPRESION ) {
		final double VAR = (double) getVar( POSSIBLE_VAR );
		String infix = "";

		addVariableOrConstant( VAR, 'V' );
		postfixEvaluator( convertToPostfix( EXPRESION ) );
		LMSprogram.add( 21000 + getUbication( VAR, 'V' ) );
	}
	
	private void runGoto( final String POSSIBLE_LINE ) {
		int lineGoto;

		if( Calculating.isNumber( POSSIBLE_LINE ) )
			lineGoto = Integer.parseInt( POSSIBLE_LINE );
		else
			throw new SyntaxError( String.format( "%s is not a valid line identifier", POSSIBLE_LINE ) );

		if( getUbication( lineGoto, 'L' ) == -1 ) {
			LMSprogram.add( 40000 );
			flags[ LMSprogram.size() - 1 ] = lineGoto;
		} else
			LMSprogram.add( 40000 + getUbication( lineGoto, 'L' ) );
	}
	
	//goto to use in for instructions (because these instructions are 4 instructions and the line to goto is impossible to find with the normal goto method
	private void specialGoto( final double LINE, final char VAR ) {
		for( int j = getUbication( LINE, 'L' ) + 1; j < LMSprogram.size(); ++j )
			if( (Integer) LMSprogram.elementAt( j ) == 20000 + getUbication( VAR, 'V' ) )
				LMSprogram.add( 40000 + j );
	}
	
	private char getVar( final String POSSIBLE_VAR ) throws SyntaxError {
		POSSIBLE_VAR.replace( ",", "" );
		char var = POSSIBLE_VAR.charAt( 0 );

		if( !Character.isLetter( var ) )
			throw new SyntaxError( String.format( "The variable or array identifier (\"%c\") must be a letter.", var ) );
	
		if( POSSIBLE_VAR.length() > 3 )
			var = getArray( POSSIBLE_VAR );
		else if( POSSIBLE_VAR.length() != 1 )
			throw new SyntaxError( String.format( "The variable (\"%s\") must be one letter", POSSIBLE_VAR ) );
		
		return var;
	}
	
	private char getArray( final String POSSIBLE_ARRAY ) throws SyntaxError {
		if( POSSIBLE_ARRAY.charAt( 1 ) != '[' ||
				POSSIBLE_ARRAY.charAt( POSSIBLE_ARRAY.length() - 1 ) != ']' ||
				!Calculating.isNumber( POSSIBLE_ARRAY.substring( 2, POSSIBLE_ARRAY.length() - 1 ) ) )
			throw new SyntaxError( String.format( "The array (\"%s\") is malformed, the correct format is: VAR_IDENTIFIER[INDEX]", POSSIBLE_ARRAY ) );
		else
			return (char) (130 + (int) POSSIBLE_ARRAY.charAt( 0 ) + Integer.parseInt( POSSIBLE_ARRAY.substring( 2, POSSIBLE_ARRAY.length() - 1 ) ));
	}
	
	private void input( final double VAR ) {
		addVariableOrConstant( VAR, 'V' );
		LMSprogram.add( 10000 + getUbication( VAR, 'V' ) );
	}
	
	private void print( final String VALUE_TO_PRINT ) {
		VALUE_TO_PRINT.replaceAll( ",", "" );
		final double var;
		final char type;

		if( Calculating.isNumber( VALUE_TO_PRINT ) ) {
			var = Double.parseDouble( VALUE_TO_PRINT );
			type = 'C';
		} else {
			var = (double) getVar( VALUE_TO_PRINT );
			type = 'V';
		}

		addVariableOrConstant( var, type );
		LMSprogram.add( 11000 + getUbication( var, type ) );
	}
	
	private void addVariableOrConstant( final double VALUE, final char TYPE ) {
		if( !addToList( new Symbol( VALUE, TYPE, varsAndConstantsCount ) ) )
			--varsAndConstantsCount;
	}
	
	private void secondRun() {
		for( short j = 0; j < flags.length; ++j )
			if( flags[ j ] == -2 )
				LMSprogram.set( j, (Integer) LMSprogram.elementAt( j ) + nextStringStart );
			else if( flags[ j ] != -1 )
				LMSprogram.set( j, (Integer) LMSprogram.elementAt( j ) + getUbication( flags[ j ], 'L' ) );
		
		for( int j = 0; j < LMSprogram.size(); ++j )
			OUT.format( "%03d %+06d\n", j, (Integer) LMSprogram.elementAt( j ) );
		
		for( int j = 0; j < strings.size(); ++j )
			OUT.format( java.util.Locale.ENGLISH, "%03d %+010.3f\n", 501 + j, (Double) strings.elementAt( j ) );

		for( int j = 0; j < constants.size(); j += 2 )
			OUT.format( java.util.Locale.ENGLISH, "%03d %+010.3f\n", (Integer) constants.elementAt( j ), (Double) constants.elementAt( j + 1 ) );
		
		OUT.close();
	}
	
	private int getUbication( double symbol, char type ) {
		for( Object object: symbols ) {
			Symbol currentSymbol = (Symbol) object;

			if( type == 'V' &&
				currentSymbol.getType() == 'V' &&
				( currentSymbol.getSymbol() == (double) Character.toLowerCase( (char) symbol ) ||
					currentSymbol.getSymbol() == (double) Character.toUpperCase( (char) symbol) ) )
				return currentSymbol.getUbication();
			else if( currentSymbol.getSymbol() == symbol && currentSymbol.getType() == type )
				return currentSymbol.getUbication();
		}
		
		return -1;
	}
	
	private boolean addToList( final Symbol symbolToAdd ) {
		boolean alreadyExists = false;
		
		for( int j = 0; j < symbols.size() && !alreadyExists; ++j )
			if( ( (Symbol) symbols.elementAt( j ) ).getSymbol() == symbolToAdd.getSymbol() &&
				( (Symbol) symbols.elementAt( j ) ).getType() == symbolToAdd.getType() )
				alreadyExists = true;
		
		if( !alreadyExists ) {
			symbols.add( symbolToAdd );
			
			if( symbolToAdd.getType() == 'C' ) {
				constants.add( getUbication( symbolToAdd.getSymbol(), 'C' ) );
				constants.add( symbolToAdd.getSymbol() );
			}
		}
		
		return alreadyExists;
	}

	private double getLastLine() {
		for( int j = symbols.size() - 1; j >= 0; --j ) {
			Symbol currentSymbol = (Symbol) symbols.elementAt( j );
			
			if( currentSymbol.getType() == 'L' )
				return currentSymbol.getSymbol();
		}
		
		return -1;
	}
	
	/*Infix and postfix operations starts here*/
	public String convertToPostfix (String infixStr) {
		StringBuffer infix = new StringBuffer (infixStr);
		StringBuffer postfix = new StringBuffer ();
		Pila stack = new Pila ();
		
		stack.push ('(');
		infix.append (')');
		
		for (int j = 0; j < infix.length () && !stack.estaVacia (); ++j) {
			if( Character.isDigit( infix.charAt( j ) ) ) {
				double[] nextNumberAndIndex = getNextNumber( infix, j );
				postfix.append( nextNumberAndIndex[0] + " " );
				j = (int) nextNumberAndIndex[1] - 1;
			} else if( Character.isLetter( infix.charAt( j ) ) ) {
				postfix.append( infix.charAt( j ) + " " );
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
	
	private boolean isOperator( final char VALUE ) {
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
	
	private boolean greaterPrecedence( final char OPERATOR1, final char OPERATOR2 ) {
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
	
	private boolean equalPrecedence( final char OPERATOR1, final char OPERATOR2 ) {
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
	
	public void postfixEvaluator( String postfixStr ) {
		StringBuffer postfix = new StringBuffer (postfixStr);
		Pila stack = new Pila();
		
		postfix.append( ')' );

		for( int j = 0; j < postfix.length(); ++j )
			if( Character.isDigit( postfix.charAt( j ) ) ) {
				double[] numberAndIndex = getNextNumber( postfix, j );

				addVariableOrConstant( numberAndIndex[ 0 ], 'C' );
				stack.push( getUbication( numberAndIndex[ 0 ], 'C' ) );
				j = (int) numberAndIndex[1] - 1;
			} else if( Character.isLetter( postfix.charAt( j ) ) ) {
				addVariableOrConstant( (int) postfix.charAt( j ), 'V' );
				stack.push( getUbication( (int) postfix.charAt( j ), 'V' ) );
			} else if( isOperator( postfix.charAt( j ) ) ) {
				int x = (Integer) stack.pop();
				int y = (Integer) stack.pop();
				
				calculate( y, postfix.charAt( j ), x );
				LMSprogram.add( 21000 + varsAndConstantsCount );
				stack.push( varsAndConstantsCount-- );
			} else if( postfix.charAt( j ) == ')' )
				if( !LMSprogram.isEmpty() &&
						(Integer) LMSprogram.lastElement() == 21000 + (Integer) stack.obtenerValorDelFrente() )
					LMSprogram.remove( LMSprogram.size() - 1 );
				else
					LMSprogram.add( 20000 + (Integer) stack.pop() );
	}
	
	private void calculate(final int OP1, final char OPERATOR, final int OP2) {
		LMSprogram.add( 20000 + OP1 );
		
		switch( OPERATOR ) {
			case '*':
				LMSprogram.add( 33000 + OP2 );
				break;

			case '/':
				LMSprogram.add( 32000 + OP2 );
				break;

			case 94://exponentiation
				LMSprogram.add( 35000 + OP2 );
				break;

			case '%':
				LMSprogram.add( 34000 + OP2 );
				break;

			case '+':
				LMSprogram.add( 30000 + OP2 );
				break;

			case '-':
				LMSprogram.add( 31000 + OP2 );
		}
	}
	
	//Returns an int array with a size of 2: index 0 has the next number and index 1 has the index such must continue.
	private double[] getNextNumber( final StringBuffer SB, final int INDEX ) {
		StringBuffer results = new StringBuffer();
		int index = 0;

		for( int j = INDEX;
				SB.charAt( j ) != ' ' && !isOperator( SB.charAt( j ) ) && SB.charAt( j ) != ')' && j < SB.length();
				++j ) {
			results.append( SB.charAt( j ) );
			index = j;
		}
		
		double[] values = {Double.parseDouble( results.toString() ), index + 1};
		return values;
	}
	/*Infix and postfix operations ends here*/
	
	public static void main( String args[] ) {
		try {
			SimpleCompiler compiler = new SimpleCompiler( args[ 0 ] );
			compiler.compile();
			System.out.println( "Would you like to run the program? (y/n)" );
			Scanner in = new Scanner( System.in );
			
			if( in.next().charAt( 0 ) == 'y' ) {
				Simpletron simpletron = new Simpletron();
				simpletron.getProgram( new File( compiler.getFilePath() ) );
				simpletron.runProgram();
			}
		} catch( FileNotFoundException fileNotFoundEx ) {
			System.err.println( "File not found " + fileNotFoundEx.getMessage() );
		}
	}
}
