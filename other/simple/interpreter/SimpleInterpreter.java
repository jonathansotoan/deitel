// Exercise 17.30
package deitel.other.simple.interpreter;

import deitel.other.simple.compiler.SyntaxError;
import deitel.dataStructure.Pila;
import deitel.maths.Calculating;

import static deitel.other.simple.interpreter.Instruction.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.NoSuchElementException;

public class SimpleInterpreter {
	private Vector program;
	private Vector vars;
	private Pila forsData;
	private double currentLine;
	private double nextLine;
	
	public SimpleInterpreter() {
		program = new Vector( 5, 5 );
		vars = new Vector( 5, 5 );
		forsData = new Pila();
		currentLine = -1;
	}

	private void saveProgram( final File SIMPLE_PROGRAM ) throws SyntaxError, FileNotFoundException {
		Scanner reader = new Scanner( SIMPLE_PROGRAM );
		
		while( reader.hasNext() ) {
			StringTokenizer tokenizer = new StringTokenizer( reader.nextLine() );
			if( tokenizer.hasMoreTokens() ) {
				String lineNumber = tokenizer.nextToken();

				if( !Calculating.isNumber( lineNumber ) )
					throw new SyntaxError( String.format( "The line \"%s\" is not a number", lineNumber ) );

				currentLine = Double.parseDouble( lineNumber );
				program.add( new Instruction( currentLine, tokenizer.nextToken(), getRestOfTokens( tokenizer ) ) );
			}
		}
		//program vector must be ordered
	}
	
	private String getRestOfTokens( final StringTokenizer ST ) {
		String results = "";
		
		while( ST.hasMoreTokens() )
			results += ST.nextToken() + " ";
		
		return results;
	}
	
	private void executeProgram() throws SyntaxError {
		boolean mustContinue = true;
		boolean forFromBegining = true;
		Scanner keysReader = new Scanner( System.in );
		StringTokenizer tokenizer;
		Pila gosubIndexes = new Pila();
		nextLine = ((Instruction) program.elementAt( 0 )).LINE;

		while( mustContinue ) {
			Instruction currentInstruction = getInstructionAt( nextLine );
			currentLine = nextLine;
			nextLine = getNextLine( currentInstruction );

			tokenizer = new StringTokenizer( currentInstruction.COMPLEMENT );

			switch( currentInstruction.INSTRUCTION ) {
				case INPUT:
					if( !tokenizer.hasMoreTokens() )
						throw new SyntaxError( "There is not a variable to save the numeric user's input" );

					System.out.println( "Insert a number" );
					setValueToVar( '!', tokenizer.nextToken(), keysReader.nextLine() );
					break;

				case INPUT_STRING:
					if( !tokenizer.hasMoreTokens() )
						throw new SyntaxError( "There is not a variable to save the text user's input" );

					System.out.println( "Insert text" );
					setValueToVar( 's', tokenizer.nextToken(), keysReader.nextLine() );
					break;

				case IF:
					String var1 = tokenizer.nextToken();
					String comparator = tokenizer.nextToken();
					String var2 = tokenizer.nextToken();

					if( !tokenizer.nextToken().equals( "goto" ) )
						throw new SyntaxError( "goto word no exist" );

					double gotoLine = Double.parseDouble( getValueOf( tokenizer.nextToken() ) );
					
					addVar( new Var( var1, "0" ) );
					addVar( new Var( var2, "0" ) );
					runIf( Double.parseDouble( getValueOf( var1 ) ),
								comparator,
								Double.parseDouble( getValueOf( var2 ) ),
								gotoLine );
					break;

				case LET:
					String var = tokenizer.nextToken();
					
					if( !tokenizer.nextToken().equals( "=" ) )
						throw new SyntaxError( "The equal symbol (=) does not exist or it is in wrong position" );

					addVar( new Var( var, "0" ) );
					setValueToVar( '!', var, "" + postfixEvaluator( convertToPostfix( getRestOfTokens( tokenizer ) ) ) );
					break;

				case GOTO:
					if( tokenizer.hasMoreTokens() )
						nextLine = Double.parseDouble( getValueOf( tokenizer.nextToken() ) );
					else
						throw new SyntaxError( "The line to go is no specified" );
					break;

				case GOSUB:
					if( tokenizer.hasMoreTokens() )
						nextLine = Double.parseDouble( getValueOf( tokenizer.nextToken() ) );
					else
						throw new SyntaxError( "The line to go is no specified" );
					
					gosubIndexes.push( getNextLine( currentInstruction ) );
					break;

				case RETURN:
					nextLine = (Double) gosubIndexes.pop();
					break;

				case FOR:
					//Getting data
					String value1For = tokenizer.nextToken();
					
					if( !tokenizer.nextToken().equals( "=" ) )
						throw new SyntaxError( "The equal symbol (=) does not exist or it is in wrong position" );
					
					String value2For = tokenizer.nextToken();
					String token = tokenizer.nextToken();

					while( !token.equals( "to" ) ) {
						value2For += token;
						token = tokenizer.nextToken();
					}
					
					String maxValue = tokenizer.nextToken();
					
					String stepValue = "1";
					if( tokenizer.hasMoreTokens() ) {
						if( !tokenizer.nextToken().equals( "step" ) )
							throw new SyntaxError( "The \"step\" word does not exist or it is in wrong position" );
						
						stepValue = tokenizer.nextToken();
					}
					
					//Processing data
					if( forFromBegining ) {
						forsData.push( new ForData( value1For, stepValue, currentLine ) );
						addVar( new Var( value1For, "0" ) );
						setValueToVar( '!', value1For, "" + postfixEvaluator( convertToPostfix( value2For ) ) );
					}

					double line = nextLine;
					runIf( Double.parseDouble( getValueOf( value1For ) ),
								"==",
								Double.parseDouble( getValueOf( maxValue ) ),
								getNextLine( getNextNEXTinstruction( currentInstruction ) ) );
					forFromBegining = true;
					
					if( line != nextLine )// If the for instruction finished
						forsData.pop();
					break;

				case NEXT:
					if( !forsData.estaVacia() ) {
						ForData data = (ForData) forsData.obtenerValorDelFrente();
						setValueToVar( '!', data.var, "" + postfixEvaluator( convertToPostfix( String.format( "%s + %s", data.var, data.step ) ) ) );
						nextLine = data.forLine;
						forFromBegining = false;
					}
					break;

				case PRINT:
					System.out.printf( "\nThe program says:\n%s", getValueOf( tokenizer.nextToken() ) );
					break;

				case PRINT_STRING:
					String nextToken = tokenizer.nextToken();
					
					if( Character.isLetter( nextToken.charAt( 0 ) ) )
						System.out.printf( "\nThe program says:\n%s", getValueOf( nextToken ) );
					else
						System.out.printf( "\nThe program says:\n%s",
													(nextToken + " " + getRestOfTokens( tokenizer )).replaceAll( "\"", "" ) );
					break;

				case PRINTLN:
					System.out.println();
					break;

				case END:
					mustContinue = false;
					break;

				case REM:
					break;
				
				default:
					throw new SyntaxError( "The instruction is wrong" );
			}
		}
	}
	
	private Instruction getInstructionAt( final double LINE ) {
		for( int j = 0; j < program.size(); ++j )
			if( ((Instruction) program.elementAt( j )).LINE == LINE )
				return ((Instruction) program.elementAt( j ));
		
		return null;
	}
	
	private String getValueOf( String varName ) {
		if( Calculating.isNumber( varName ) )
			return varName;
		else
			for( int j = 0; j < vars.size(); ++j )
				if( ((Var) vars.elementAt( j )).NAME.equals( varName ) )
					return ((Var) vars.elementAt( j )).value;
		
		return null;
	}
	
	private void setValueToVar( char type, String name, String value ) {
		boolean exists = false;
		
		for( int j = 0; !exists && j < vars.size(); ++j )
			if( ((Var) vars.elementAt( j )).NAME.equals( name ) ) {
				((Var) vars.elementAt( j )).value = value;
				exists = true;
			}
		
		if( !exists )
			if( type == 's' )
				addVar( new Var( 's', name, value ) );
			else
				addVar( new Var( name, value ) );
	}
	
	/*private void setValueToVar( char type, String name, String value ) {
		if( type == 's' )
			addVar( new Var( 's', name, "" ) );
		else
			addVar( new Var( name, "0" ) );
		
		for( int j = 0; j < vars.size(); ++j )
			if( ((Var) vars.elementAt( j )).NAME.equals( name ) )
				((Var) vars.elementAt( j )).value = value;
	}*/
	
	private Instruction getNextNEXTinstruction( final Instruction CURRENT_INSTRUCTION ) {
		boolean continueSearching = true;
		
		for( int j = 0; j < program.size(); ++j )
			if( continueSearching && ((Instruction) program.elementAt( j )) == CURRENT_INSTRUCTION )
				continueSearching = false;
			else if( ((Instruction) program.elementAt( j )).INSTRUCTION == NEXT )
				return (Instruction) program.elementAt( j );
		
		return null;
	}
	
	private void runIf( double value1, String comparator, double value2, double lineToGo ) {
		if( comparator.equals( "==" ) && value1 == value2 )
			nextLine = lineToGo;
		else if( comparator.equals( ">" ) && value1 > value2 )
			nextLine = lineToGo;
		else if( comparator.equals( "<" ) && value1 < value2 )
			nextLine = lineToGo;
	}
	
	private double getNextLine( final Instruction CURRENT_INSTRUCTION ) {
		for( int j = 0; j < program.size(); ++j )
			if( CURRENT_INSTRUCTION == (Instruction) program.elementAt( j ) && j + 1 < program.size() )
				return ((Instruction) program.elementAt( j + 1 )).LINE;
		
		return -1;
	}
	
	private void addVar( final Var VAR ) {
		if( !vars.contains( VAR ) )
			vars.add( VAR );
	}
	
	private Var getVar( final String NAME ) {
		for( int j = 0; j < vars.size(); ++j )
			if( ((Var) vars.elementAt( j )).NAME.equals( NAME ) )
				return (Var) vars.elementAt( j );
		
		return null;
	}
	
	public void run( final File SIMPLE_PROGRAM ) {
		try {
			saveProgram( SIMPLE_PROGRAM );
			executeProgram();
			System.out.println();
		} catch( SyntaxError syntaxErr ) {
			System.err.printf( "%s\n%s\n",
										syntaxErr.getMessage(),
										currentLine == -1 ? "Unknown line" : "In line number " + currentLine );
			System.exit( 1 );
		} catch( IllegalArgumentException illegalArgumentEx ) {
			System.err.printf( "%s\n%s\n",
										illegalArgumentEx.getMessage(),
										currentLine == -1 ? "Unknown line" : "In line number " + currentLine );
			System.exit( 1 );
		} catch( NoSuchElementException noSuchElementEx ) {
			System.err.printf( "It seems that the line is not complete\n%s\n",
										currentLine == -1 ? "Unknown line" : "In line number " + currentLine );
			System.exit( 1 );
		} catch( FileNotFoundException fileNotFoundEx ) {
			System.err.println( "The specified file does not exist\n" );
			System.exit( 1 );
		} catch( Exception ex ) {
			System.err.printf( "It seems that you have an error\n%s\n",
										currentLine == -1 ? "Unknown line" : "In line number " + currentLine );
			System.exit( 1 );
		}
	}
	
	/*start postfix and infix operations*/
	private String convertToPostfix (String infixStr) {
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
			} else if( infix.charAt( j ) == '(' )
				stack.push( '(' );
			else if( isOperator( infix.charAt( j ) ) ) {
				while( isOperator( (Character) stack.nextValue() ) &&
						( greaterPrecedence( (Character) stack.nextValue(), infix.charAt( j ) ) ||
						equalPrecedence( (Character) stack.nextValue(), infix.charAt( j ) ) ) )
					postfix.append( stack.pop() );
				
				stack.push( infix.charAt( j ) );
			} else if( Character.isLetter( infix.charAt( j ) ) )
				postfix.append( infix.charAt( j ) + " " );
			else if( infix.charAt( j ) == ')') {
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
	
	public double postfixEvaluator( String postfixStr ) {
		StringBuffer postfix = new StringBuffer (postfixStr);
		Pila stack = new Pila();
		double results = -1;
		
		postfix.append( ')' );

		for( int j = 0; j < postfix.length(); ++j ) {
			if( Character.isDigit( postfix.charAt( j ) ) ) {
				double[] numberAndIndex = getNextNumber( postfix, j );
				stack.push( numberAndIndex[0] );
				j = (int) numberAndIndex[1] - 1;
			} else if( Character.isLetter( postfix.charAt( j ) ) )
				stack.push( Double.parseDouble( getVar( postfix.charAt( j ) + "" ).value ) );
			else if( isOperator( postfix.charAt( j ) ) ) {
				double x = (Double) stack.pop();
				double y = (Double) stack.pop();
				
				stack.push( calculate( y, postfix.charAt( j ), x ) );
			} else if( postfix.charAt( j ) == ')' )
				results = (Double) stack.pop();
		}

		return results;
	}
	
	private double calculate(final double OP1, final char OPERATOR, final double OP2) {
		switch( OPERATOR ) {
			case '*':
				return OP1 * OP2;
			case '/':
				return OP1 / OP2;
			case 94://exponentiation
				return (int) Math.pow( OP1, OP2 );
			case '%':
				return (int) OP1 % (int) OP2;
			case '+':
				return OP1 + OP2;
			case '-':
				return OP1 - OP2;
			default:
				return -1;
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
	/*end postfix and infix operations*/
	
	public static void main( String args[] ) {
		SimpleInterpreter si = new SimpleInterpreter();
		si.run( new File( args[ 0 ] ) );
	}
}

class Var {
	char TYPE;//i: int, d: double or s: String
	final String NAME;
	String value;

	//this constructor is to create a new numeric variable
	Var( final String NAME, final String VALUE ) {
		if( Calculating.isNumber( VALUE ) )
			try {
				Integer.parseInt( VALUE );
				TYPE = 'i';
			} catch( NumberFormatException numberFormatEx ) {
				TYPE = 'd';
			} finally {
				this.NAME = NAME;
				value = VALUE;
			}
		else
			throw new SyntaxError( String.format( "The input \"%s\" entered is not a number", VALUE ) );
	}

	Var( final char TYPE, final String NAME, final String VALUE ) throws SyntaxError {
		if( !Character.isLetter( NAME.charAt( 0 ) ) )
			throw new SyntaxError( "The variable name must be a letter" );

		this.TYPE = TYPE;
		this.NAME = NAME;
		value = VALUE;
	}
}

class ForData {
	final String var;
	final String step;
	final double forLine;
	
	ForData( String varName, String stepValue, double forLine ) {
		var = varName;
		step = stepValue;
		this.forLine = forLine;
	}
}
