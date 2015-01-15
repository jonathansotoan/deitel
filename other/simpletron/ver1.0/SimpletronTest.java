// Exercises 7.34 - 7.36
/*
 *
 * @author Jonathan Soto, contact me: jonathansoto.an@gmail.com
 * @version 1.0 18/06/2013
 * @see Simpletron class
 *
 * This class makes a Simpletron class test.
 */
public class SimpletronTest {

	public static void main (String args[]) {
		Simpletron mySimpletron = new Simpletron ();
		
		mySimpletron.printWelcomeMessage ();
		mySimpletron.getProgram ();
		mySimpletron.runProgram ();
	}
}

/***************************************************************************
* Examples of LMS programs.																*
****************************************************************************
* 1. Summation of 2 numbers																*
* Ubication	Number	Instruction															*
* 00			+1007		Read A																*
* 01			+1008		Read B																*
* 02			+2007		Load A in the acumulator										*
* 03			+3008		Add B																	*
* 04			+2109		Save C (A + B)														*
* 05			+1109		Write (or print) C												*
* 06			+4300		Stop program														*
* 07			-99999	It isn't in the program, it's to finish the input		*
****************************************************************************
* 2. Major of 2 numbers																		*
* Ubication	Number	Instruction															*
* 00			+1009		Read A																*
* 01			+1010		Read B																*
* 02			+2009		Load A in the acumulator										*
* 03			+3110		Substract B															*
* 04			+4107		If the acumulator is minor than 0, it goes to 07		*
* 05			+1109		Write (or print) A												*
* 06			+4300		Stop the program													*
* 07			+1110		Write B																*
* 08			+4300		Stop the program													*
* 09			-99999	It isn't in the program, it's to finish the input		*
****************************************************************************
* 3. This program sums positive numbers (until you input a negative one)	*
* Ubication	Number	Instruction															*
* 00			+1008		Read a var															*
* 01			+2008		Load the var														*
* 02			+4106		If the var is negative, ti goes to 06						*
* 03			+3009		Add the var to the total (in 09)								*
* 04			+2109		Save the new total												*
* 05			+4000		It goes to 00 (reinit)											*
* 06			+1109		Write total number												*
* 07			+4300		Stop program														*
* 08			-99999	It isn't in the program, it's to finish the input		*
****************************************************************************
* 4. This program calculates the average of 7 numbers (negatives too)		*
* Ubication	Number	Instruction															*
* 00			+1014		Read a var															*
* 01			+2014		Load the var														*
* 02			+3015		Add the current total											*
* 03			+2115		To save the new total											*
* 04			+2016		To load the count var											*
* 05			+3117		Less 1 to the count var											*
* 06			+2116		To save the new count var										*
* 07			+4209		To go to 09 to finish the program							*
* 08			+4000		To come back to 00												*
* 09			+2015		To load the total var											*
* 10			+3218		To divide by 0007 to get the average						*
* 11			+2115		To saves the average in 15										*
* 12			+1115		To write the average												*
* 13			+4300		End of the program												*
* 14			+0000		Variable to save the numbers									*
* 15			+0000		Var to save the total (it ends with the average)		*
* 16			+0007		Var used as count													*
* 17			+0001		Constant to substract from the count						*
* 18			+0007		Value to divide and get the average							*
* 19			-99999	It isn't in the program, it's to finish the input		*
****************************************************************************
* 5. Print the major of a X numbers (X is the first number entered).			*
* Ubication	Number	Instruction															*
* 00			+1018		To read quantity of numbers that will be analized		*
* 01			+2018		To load the quantity												*
* 02			+3119		To less 1 to the quantity										*
* 03			+2118		To save the new quantity										*
* 04			+1020		To read the first number and it's saved as the major	*
* 05			+1021		To read the next number											*
* 06			+2021		To load the last number entered								*
* 07			+3120		To less the current major number								*
* 08			+4111		If acumulator's number is negative, go to 11 ubication*
* 09			+2021		To load the last number entered								*
* 10			+2120		To save the acumulator's number as the major number	*
* 11			+2018		To load the quantity in the acumulator						*
* 12			+3119		To less 1 to the quantity										*
* 13			+2118		To save the new quantity										*
* 14			+4216		If we shouldn't query for more numbers, it goes to 16	*
* 15			+4005		It goes to 05														*
* 16			+1120		Write the major number											*
* 17			+4300		The program ends													*
* 18			+0000		Variable to save the quantity									*
* 19			+0001		Constant to substract by quantity							*
* 20			-99999	It isn't in the program, it's to finish the input		*
***************************************************************************/
