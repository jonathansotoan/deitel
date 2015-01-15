//Exercise 17.30
package deitel.other.simple.interpreter;

public class Instruction {
	public final static byte INPUT = 0;
	public final static byte INPUT_STRING = 1;
	public final static byte IF = 2;
	public final static byte LET = 3;
	public final static byte GOTO = 4;
	public final static byte GOSUB = 5;
	public final static byte RETURN = 6;
	public final static byte FOR = 7;
	public final static byte NEXT = 8;
	public final static byte PRINT = 9;
	public final static byte PRINT_STRING = 10;
	public final static byte PRINTLN = 11;
	public final static byte END = 12;
	public final static byte REM = 13;

	public final double LINE;
	public final byte INSTRUCTION;
	public final String COMPLEMENT;
	
	public Instruction( final double LINE, final byte INSTRUCTION, final String COMPLEMENT ) throws IllegalArgumentException {
		if( INSTRUCTION == -1 )
			throw new IllegalArgumentException( String.format( "The instruction code \"%d\" is not a valid instruction code", INSTRUCTION ) );

		this.LINE = LINE;
		this.INSTRUCTION = INSTRUCTION;
		this.COMPLEMENT = COMPLEMENT;
	}
	
	public Instruction( final double LINE, final String INSTRUCTION, final String COMPLEMENT ) throws IllegalArgumentException {
		this( LINE, getInstructionCode( INSTRUCTION ), COMPLEMENT );
	}
	
	@Override
	public String toString() {
		return String.format( "Line: %f, code instruction: %d, complement: %s\n", LINE, INSTRUCTION, COMPLEMENT );
	}
	
	public static byte getInstructionCode( final String INSTRUCTION ) {
		if( INSTRUCTION.equals( "input" ) )
			return INPUT;
		else if( INSTRUCTION.equals( "input_string" ) )
			return INPUT_STRING;
		else if( INSTRUCTION.equals( "if" ) )
			return IF;
		else if( INSTRUCTION.equals( "let" ) )
			return LET;
		else if( INSTRUCTION.equals( "goto" ) )
			return GOTO;
		else if( INSTRUCTION.equals( "gosub" ) )
			return GOSUB;
		else if( INSTRUCTION.equals( "return" ) )
			return RETURN;
		else if( INSTRUCTION.equals( "for" ) )
			return FOR;
		else if( INSTRUCTION.equals( "next" ) )
			return NEXT;
		else if( INSTRUCTION.equals( "print" ) )
			return PRINT;
		else if( INSTRUCTION.equals( "print_string" ) )
			return PRINT_STRING;
		else if( INSTRUCTION.equals( "println" ) )
			return PRINTLN;
		else if( INSTRUCTION.equals( "end" ) )
			return END;
		else if( INSTRUCTION.equals( "rem" ) )
			return REM;
		else
			return -1;
	}
}
