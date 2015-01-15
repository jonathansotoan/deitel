// Exercises 7.34 - 7.36
/**
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 1.0 17/06/2013
 * @see SimpletronTest class
 *
 * This class simulates a simple computer, and its language programming:
 * LMS (lenguaje máquina de Simpletron in spanish).
 */
import java.util.Scanner;
 
public class Simpletron {
	private short[] memory;// it's used to storage the instrucctions
	private Scanner input = new Scanner (System.in);// this is used to get the user's input
	private byte instructionsCount = -1;// This has the current instruction number executing
	private short acumulator;// This has a number that the program can use to process
	private byte operationCode;// This var is used to storage the current operation code (first and second digit)
	private byte operand;// This has the memory's ubication of a number that will aplicate the current operation
	private short registryOperation;// This is used to storage the operationCode and the operand while they're processing
	private boolean error = false;

	// It initializes the attributes
	public Simpletron () {
		memory = new short[100];
		
		for (byte j = 0; j < memory.length; ++j)
			memory[j] = 0;
	}
	
	// This method prints a welcome message
	public void printWelcomeMessage () {
		System.out.printf ("*** %s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n\n",
								"Welcome to Simpletron!",
								"Please, insert in your program one instruction",
								"(or one data word), then press <intro> key.",
								"I'll show you the memory's ubication number and",
								"a question mark (?). Then you'll write the word",
								"to that memory's ubication. When you want to",
								"finish the input, please insert -99999 number.");
	}
	
	public void getProgram () {
		System.out.printf ("%02d ? ", 0);
		String instruction = input.next ().replace ('+', '0');
		
		for (byte j = 0; !instruction.equals ("-99999") && j < memory.length; ++j) {
			memory[j] =  Short.parseShort (instruction);
			System.out.printf ("%02d ? ", j + 1);
			instruction = input.next ().replace ('+', '0');
		}
		
		System.out.println ("*** The input is over ***");
	}
	
	/**
	 *
	 * This method runs the program that is in memory array.
	 */
	public void runProgram () {
		// operations's constants
		final short READ = 10;
		final short WRITE = 11;
		final short LOAD = 20;
		final short SAVE = 21;
		final short ADD = 30;
		final short SUBSTRACT = 31;
		final short DIVIDE = 32;
		final short MULTIPLY = 33;
		final short GOTO = 40;
		final short GOTONEG = 41;
		final short GOTOZERO = 42;
		final short STOP = 43;
		
		System.out.println ("*** Starting program ***");
		
		while (registryOperation != 4300 && !error) {
			registryOperation = memory[++instructionsCount];
			operationCode = (byte) (registryOperation / 100);
			operand = (byte) (registryOperation % 100);
			
			switch (operationCode) {
				case READ:
					short instruction;
				
					do {
						System.out.println ("Insert a number between -9999 and 9999 numbers");
						instruction = Short.parseShort (input.next ().replace ('+', '0'));
					} while (instruction > 9999 || instruction < -9999);
					
					memory[operand] = instruction;
					break;
					
				case WRITE:
					System.out.println ("The program says: ".concat ("" + memory[operand]));
					break;
					
				case LOAD:
					acumulator = memory[operand];
					break;
					
				case SAVE:
					memory[operand] = acumulator;
					break;
					
				case ADD:
					if (acumulator + memory[operand] > 9999 || acumulator + memory[operand] < -9999)
						throwError (String.format ("The sum %+04d + %+04d is bigger than 9999 or it's smaller than -9999",
										acumulator,
										memory[operand]));
					else
						acumulator += memory[operand];
					break;
					
				case SUBSTRACT:
					if (acumulator - memory[operand] > 9999 || acumulator - memory[operand] < -9999)
						throwError (String.format ("The substraction %+04d - %+04d is bigger than 9999 or it's smaller than -9999",
										acumulator,
										memory[operand]));
					else
						acumulator -= memory[operand];
					break;
					
				case DIVIDE:
					if (memory[operand] == 0)
						throwError ("you're trying divide by 0");
					else
						acumulator /= memory[operand];
					break;
					
				case MULTIPLY:
					if (acumulator * memory[operand] > 9999 || acumulator * memory[operand] < -9999)
						throwError (String.format ("The multiplication %+04d * %+04d is bigger than 9999 or it's smaller than -9999",
										acumulator,
										memory[operand]));
					else
						acumulator *= memory[operand];
					break;
					
				case GOTO:
					instructionsCount = --operand;
					break;
					
				case GOTONEG:
					if (acumulator < 0)
						instructionsCount = --operand;
					break;
					
				case GOTOZERO:
					if (acumulator == 0)
						instructionsCount = --operand;
					break;
					
				case STOP:
					break;
				
				default:
					throwError ("The operationCode is wrong (the first and second digit of your instruction)");
			}
		}
		
		if (!error)
			memoryDump ();
	}
	
	/*
	 *
	 * This method throws an specified error and it calls memoryDump method.
	 */
	public void throwError (String error) {
		this.error = true;
		System.out.printf ("*** You have an error: %s ***\n\n", error);
		
		memoryDump ();
	}
	
	/**
	 *
	 * This method makes a memory dump to show the memory's value and the others Simpletron' attributes.
	 */
	 private void memoryDump () {
	 	System.out.printf ("Registries\n%-20s %+05d\n%-20s %02d\n%-20s %+05d\n%-20s %02d\n%-20s %02d\n\n",
	 							"acumulator", acumulator,
	 							"instructionsCount", instructionsCount,
	 							"registryOperation", registryOperation,
	 							"operationCode", operationCode,
	 							"operand", operand);
	 							
	 	System.out.print ("MEMORY:\n\n  ");
	 	
	 	for (byte j = 0; j < 10; ++j)
	 		System.out.printf ("%7d", j);
	 	
	 	for (byte j = 0; j < 10; ++j) {
	 		System.out.printf ("\n%2d", j * 10);
	 		
	 		for (byte k = 0; k < 10; ++k)
	 			System.out.printf ("  %+05d", memory[j * 10 + k]);
	 	}
	 	
	 	System.out.println ("\n*** The execution is over ***");
	 }
}
