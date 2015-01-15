// Exercises 7.34 - 7.36
/**
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 1.1 24/06/2013
 * @see SimpletronTest class
 *
 * This class simulates a simple computer, and its language programming:
 * LMS (lenguaje máquina de Simpletron in spanish).
 */
import java.util.Scanner;
import java.util.Vector;
import java.util.StringTokenizer;
 
public class Simpletron {
	private double[] memory;// it's used to storage the instrucctions
	private Scanner input = new Scanner (System.in);// this is used to get the user's input
	private short instructionsCount = -1;// This has the current instruction number executing
	private double acumulator;// This has a number that the program can use to process
	private byte operationCode;// This var is used to storage the current operation code (first and second digit)
	private short operand;// This has the memory's ubication of a number (third, fourth and last digit) that will aplicate the current operation
	private int registryOperation;// This is used to storage the operationCode and the operand while they're processing
	private boolean error = false;
	private boolean isHexInput = false;

	// It initializes the attributes
	public Simpletron () {
		memory = new double[1000];
		
		for (short j = 0; j < memory.length; ++j)
			memory[j] = 0;
	}
	
	// This method prints a welcome message
	public void printWelcomeMessage () {
		System.out.printf ("*** %s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n*** %-47s ***\n\n",
								"Welcome to Simpletron!",
								"Please, insert in your program one instruction",
								"(or one data word), then press <intro> key.",
								"I'll show you the memory's ubication number and",
								"a question mark (?). Then you'll write the word",
								"to that memory's ubication. When you want to",
								"finish the input, please insert -999999 number.",
								"If you input in the first line \"HEX\", then",
								"you must to insert the program in hexadecimal",
								"numbers and the -F423F number is the indicator",
								"to finish the program's input.");
	}
	
	public void getProgram () {
		String instruction;
		
		do {
			System.out.printf ("%03d ? ", 0);
			instruction = input.next ().replace ('+', '0');
		
			if (instruction.equals ("HEX"))
				isHexInput = true;
		} while (instruction.equals ("HEX"));

		for (byte j = 0;
				!instruction.equals ("-999999") && !instruction.equals ("-F423F") && j < memory.length;
				++j) {
			memory[j] = isHexInput ? convertToDec (instruction): Double.parseDouble (instruction);
			System.out.printf ("%03d ? ", j + 1);
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
		final byte READ = 10;
		final byte WRITE = 11;
		final byte WRITELN = 12;
		final byte READSTRING = 13;
		final byte WRITESTRING = 14;
		final byte LOAD = 20;
		final byte SAVE = 21;
		final byte ADD = 30;
		final byte SUBSTRACT = 31;
		final byte DIVIDE = 32;
		final byte MULTIPLY = 33;
		final byte RESIDUE = 34;
		final byte POW = 35;
		final byte GOTO = 40;
		final byte GOTONEG = 41;
		final byte GOTOZERO = 42;
		final byte STOP = 43;
		
		System.out.println ("*** Starting program ***");
		
		while (registryOperation != 43000 && !error) {
			registryOperation = (int) memory[++instructionsCount];
			operationCode = (byte) (registryOperation / 1000);
			operand = (short) (registryOperation % 1000);
			
			switch (operationCode) {
				case READ:
					double instruction;
				
					do {
						System.out.println ("Insert a number between -99999 and 99999 numbers");
						instruction = Double.parseDouble (input.next ().replace ('+', '0'));
					} while (instruction > 99999 || instruction < -99999);
					
					memory[operand] = instruction;
					break;
					
				case WRITE:
					System.out.printf ("The program says: %+09.2f\n", memory[operand]);
					break;
					
				case WRITELN:
					System.out.println ();
					break;
					
				case READSTRING:
					String enteredStr;
				
					do {
						System.out.printf ("Insert text with a maximun of %d characters\n", memory.length-operand-1);
						input.nextLine ();
						enteredStr = input.nextLine ();
					} while (enteredStr.length () > memory.length - operand - 1);
					
					saveAsASCII (enteredStr, operand);
					break;
					
				case WRITESTRING:
					System.out.println ("The program says: ");
					printASCII (operand);
					break;
					
				case LOAD:
					acumulator = memory[operand];
					break;
					
				case SAVE:
					memory[operand] = acumulator;
					break;
					
				case ADD:
					if (acumulator + memory[operand] > 99999 || acumulator + memory[operand] < -99999)
						throwError (String.format ("The sum %+06d + %+06d is bigger than 99999 or it's smaller than -99999",
										acumulator,
										memory[operand]));
					else
						acumulator += memory[operand];
					break;
					
				case SUBSTRACT:
					if (acumulator - memory[operand] > 99999 || acumulator - memory[operand] < -99999)
						throwError (String.format ("The substraction %+06d - %+06d is bigger than 99999 or it's smaller than -99999",
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
					if (acumulator * memory[operand] > 99999 || acumulator * memory[operand] < -99999)
						throwError (String.format ("The multiplication %+06d * %+06d is bigger than 99999 or it's smaller than -99999",
										acumulator,
										memory[operand]));
					else
						acumulator *= memory[operand];
					break;
					
				case RESIDUE:
					if (memory[operand] == 0)
						throwError ("you're trying divide by 0");
					else
						acumulator %= memory[operand];
					break;
					
				case POW:
					if (Math.pow (acumulator, memory[operand]) > 99999)
						throwError (String.format ("You can't pow %+06d by %+06d, because it's bigger than 99999",
										acumulator,
										memory[operand]));
					else
						acumulator = (int) Math.pow (acumulator, memory[operand]);
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
	private void throwError (String error) {
		this.error = true;
		System.out.printf ("*** You have an error: %s ***\n\n", error);
		
		memoryDump ();
	}
	
	/**
	 *
	 * This method makes a memory dump to show the memory's value and the others Simpletron' attributes.
	 */
	private void memoryDump () {
	 	System.out.printf ("Registries\n%-20s %+010.3f\n%-20s %03d\n%-20s %+06d\n%-20s %02d\n%-20s %03d\n\n",
	 							"acumulator", acumulator,
	 							"instructionsCount", instructionsCount,
	 							"registryOperation", registryOperation,
	 							"operationCode", operationCode,
	 							"operand", operand);
	 							
	 	System.out.print ("MEMORY:\n\n    ");
	 	
	 	for (byte j = 0; j < 10; ++j)
	 		System.out.printf ("%12d", j);
	 	
	 	for (byte j = 0; j < 100; ++j) {
	 		System.out.printf ("\n%4d", j * 10);
	 		
	 		for (byte k = 0; k < 10; ++k)
	 			if (isHexInput)
	 				System.out.printf ("  %s%s",
	 										memory[j * 10 + k] < 0 ? "": "+",
	 										addZeros (convertToHex (memory[j * 10 + k]), (byte) 5, (byte) 3));
	 			else
		 			System.out.printf ("  %+010.3f", memory[j * 10 + k]);
		}
	 	
		System.out.print ("\n    ");
	 	
		for (byte j = 0; j < 10; ++j)
	 		System.out.printf ("%12d", j);
	 	
		System.out.println ("\n*** The execution is over ***");
	}
	
	/**
	 *
	 * This method converts the specified number to an hexadecimal number.
	 *
	 * @returns an <code><b>String</b></code> that contains the hexadecimal number.
	 * @param decimalNumber as <code><b>int</b></code> that defines the decimal number to convert.
	 */
	private String convertToHex (double decimalNumber) {
		String factor = "";// this var indicates if the number is negative
	
		if (decimalNumber < 0) {
			decimalNumber *= -1;
			factor = "-";
		}
		
		double decimalPartAfterPoint = decimalNumber - (int) decimalNumber;
		String hexNumberAfterPoint = "";
		
		for (byte j = 0; j < 2; ++j) {
			hexNumberAfterPoint += getHexaDigit ((int) (decimalPartAfterPoint * 16));
			decimalPartAfterPoint = decimalPartAfterPoint * 16 - (int) (decimalPartAfterPoint * 16);
		}
		
		return factor + Integer.toHexString ((int) decimalNumber).toUpperCase () + "." + hexNumberAfterPoint;
	}

	/**
	 *
	 * This Method returns the correspondent digit in hexadecimal.
	 *
	 * @returns a <code><b>char</b></code> with the correspondent digit in hexadecimal.
	 * @param an <code><b>int</code></b> which cointains the number to return in hexadecimal.
	 */
	private String getHexaDigit (int decimalDigit) {
		String returnValue;
	
		if (decimalDigit < 10)
			returnValue = "" + decimalDigit;
		else
			switch (decimalDigit) {
				case 10:
					returnValue = "A";
					break;
				case 11:
					returnValue = "B";
					break;
				case 12:
					returnValue = "C";
					break;
				case 13:
					returnValue = "D";
					break;
				case 14:
					returnValue = "E";
					break;
				case 15:
					returnValue = "F";
					break;
				default:
					returnValue = "X";// It indicates that there's an error
			}
		
		return returnValue;
	}
	
	/**
	 *
	 * This method converts the specified hexadecimalnumber to an decimal number.
	 *
	 * @returns an <code><b>double</b></code> which contains the decimal number.
	 * @param hexaNumber as <code><b>String</b></code> which defines the hexadecimal number to convert.
	 */
	private double convertToDec (String hexaNumber) {
		double decNumber = 0;
		byte factor = (byte) (hexaNumber.charAt (0) == '-' ? -1: 1);// this var indicates if the number is negative
		if (hexaNumber.indexOf (',') != -1) {
			StringTokenizer st = new StringTokenizer (hexaNumber, ",");
			String beforePoint = st.nextToken ();
			String afterPoint = st.nextToken ();
			double afterPointDec = 0.0;

			for (byte j = 0; j > afterPoint.length () * -1 && j > -2; --j)
				if (hexaNumber.charAt (j * -1) != '-')
					afterPointDec += Character.getNumericValue (afterPoint.charAt (j * -1)) * Math.pow (16, j - 1);

			return factor * (Integer.parseInt (beforePoint, 16) + afterPointDec);
		} else
			return factor * Integer.parseInt (hexaNumber, 16);
	}

	/**
	 *
	 * This Method returns the correspondent digit in decimal.
	 *
	 * @returns a <code><b>byte</b></code> with the correspondent digit in decimal.
	 * @param a <code><b>char</code></b> such cointains the number to return in decimal.
	 */
	private byte getDecDigit (char hexaDigit) {
		byte returnValue;

		switch (hexaDigit) {
			case 'A':
				returnValue = 10;
				break;
			case 'B':
				returnValue = 11;
				break;
			case 'C':
				returnValue = 12;
				break;
			case 'D':
				returnValue = 13;
				break;
			case 'E':
				returnValue = 14;
				break;
			case 'F':
				returnValue = 15;
				break;
			default:
				if (Character.getNumericValue (hexaDigit) >= 0)
					returnValue = (byte) (Character.getNumericValue (hexaDigit));
				else
					returnValue = -1;// It indicates that there's an error
		}

		return returnValue;
	}
	
	/**
	 *
	 * This method adds as 0 as the string needs to be a string with the specified characters.
	 *
	 * @returns a <code><b>String</b></code> which contains the same param's string, but with the numbers
	 * of 0 required to be an string of the specified characters.
	 * @param a <code><b>String</b></code> which specifies the String to fill with 0.
	 * @param a <code><b>byte</b></code> which specifies what's the new size to the specified String (before of the point).
	 * @param a <code><b>byte</b></code> which specifies what's the new size to the specified String (after of the point).
	 */
	private String addZeros (String str, byte sizeBP, byte sizeAP) {
		StringTokenizer st = new StringTokenizer (str, ".");
		String strBeforePoint = st.nextToken ();
		String strAfterPoint = st.nextToken ();
		byte strBPinitialLength = (byte) strBeforePoint.length ();
		byte strAPinitialLength = (byte) strAfterPoint.length ();

		for (byte j = 0; j < sizeBP - strBPinitialLength; ++j)
			strBeforePoint = "0".concat (strBeforePoint);
		
		for (byte j = 0; j < sizeAP - strAPinitialLength; ++j)
			strAfterPoint += "0";
			
		return strBeforePoint + "." + strAfterPoint;
	}
	
	/**
	 *
	 * This method saves the specified String in memory array as an ASCII sequence.
	 *
	 * @param str as a <code><b>String</b></code> such specifies the string to save as an ASCII sequence.
	 * @param position as an <code><b>int</b></code>. In the specified position the str
	 * var will be saved (and the next spaces if it is necessary).
	 */
	private void saveAsASCII (String str, int position) {
		/*System.out.println ((char) 65);
		System.out.println ((int) 'A');*/
		memory[position++] = str.length ();
		
		for (short j = 0; j < str.length (); j += 2, ++position) {
			double data = (int) str.charAt (j);
			data = j + 1 < str.length () ? data + ((int) str.charAt (j + 1) / 1000.0) : data;
			memory[position] = data;
		}
	}
	
	/**
	 *
	 * This method prints an ASCII string saved in memory array starting in the specified position.
	 *
	 * @param position as <code><b>short</code></b>. It indicates the position such is the string's length.
	 */
	private void printASCII (short position) {
		for (short j = 1; j <= (int) memory[position]; ++j) {
			System.out.print ((char) ((int) memory[j + position]) + "" +
									(char) Math.round ((memory[j + position] - (int) memory[j + position]) * 1000));
		}
		
		System.out.println ();
	}
}
