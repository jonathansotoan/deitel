// Exercises 7.34 - 7.36
/*
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 1.1 24/06/2013
 * @see Simpletron class
 *
 * This class makes a Simpletron class test.
 */
package deitel.other.simpletron.ver1_2;

import java.util.Scanner;
import java.io.File;

public class SimpletronTest {

	public static void main (String args[]) {
		Simpletron mySimpletron = new Simpletron ();
		
		System.out.println( "Type the complete path of the LMS programm" );
		Scanner s = new Scanner( System.in );
		mySimpletron.getProgram ( new File( s.nextLine() ) );
		mySimpletron.runProgram ();
	}
}
