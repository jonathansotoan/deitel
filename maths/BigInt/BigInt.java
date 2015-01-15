// exercise 8.18
/* this class doesn't support negative numbers, multiply and divide operation */
import java.util.Scanner;

public class BigInt {
	private char[] number = new char[40];

	public BigInt (long bigInt) {
		this (("" + bigInt).toCharArray ());
	}

	public BigInt (char[] bigInt) {
		setNumber (bigInt);
	}
	
	public void setNumber (char[] bigInt) {
		reinitNumber ();
	
		for (byte j = 0; j < bigInt.length; ++j)
			setNumber (bigInt[j], (byte) (number.length - bigInt.length + j));
	}
	
	private void setNumber (char numberToSet, byte position) {
		number[position] = Character.isDigit (numberToSet) ? numberToSet : '0';
	}
	
	private void setNumber (byte numberToSet, byte position) {
		setNumber (("" + numberToSet).charAt (0), position);
	}
	
	public char[] getNumber () {
		return number;
	}
	
	public void reinitNumber () {
		for (byte j = 0; j < number.length; ++j)
			number[j] = '0';
	}
	
	public String toString () {
		String str = "";
		boolean checkZero = false;
		
		for (char num: number)
			if (num != '0' || checkZero) {
				str += num;
				checkZero = true;
			}
		
		return str.equals ("") ? "0" : str;
	}
	
	public void add (char[] numberToAdd) {
		if (numberToAdd.length <= number.length)
			for (byte j = 0; j < numberToAdd.length; ++j)
				add ((byte) (Character.getNumericValue (numberToAdd[numberToAdd.length - 1 - j])), (byte) (number.length - 1 - j));
	}
	
	public void add (long numberToAdd) {
		add (("" + numberToAdd).toCharArray ());
	}
	
	private void add (byte numberToAdd, byte position) {
		byte addition = (byte) (Character.getNumericValue (number[position]) + numberToAdd);
		
		if (addition > 9) {
			setNumber ((byte) (addition - (addition / 10) * 10), position);
			add ((byte) (addition / 10), --position);
		} else
			setNumber (addition, position);
	}
	
	public void substract (char[] numberToSubstract) {
		if (numberToSubstract.length <= number.length)
			for (byte j = 0; j < numberToSubstract.length; ++j)
				substract ((byte) (Character.getNumericValue (numberToSubstract[numberToSubstract.length - 1 - j])), (byte) (number.length - 1 - j));
	}
	
	public void substract (long numberToSubstract) {
		substract (("" + numberToSubstract).toCharArray ());
	}
	
	private void substract (byte numberToSubstract, byte position) {
		byte substract = (byte) (Character.getNumericValue (number[position]) - numberToSubstract);
		
		if (substract < 0) {
			setNumber ((byte) (10 + substract), position);
			substract ((byte) 1, --position);
		} else
			setNumber (substract, position);
	}
	
	public static BigInt nextBigInt () {
		return new BigInt (new Scanner (System.in).next ().toCharArray ());
	}
	
	public boolean areEquals (BigInt bi) {
		boolean equals = true;
	
		for (byte j = 0; equals && j < number.length; ++j)
			if (number[j] != bi.getNumber()[j])
				equals = false;
		
		return equals;
	}
	
	public boolean isBiggerThan (BigInt bi) {
		boolean isBigger = false;
		
		for (byte j = 0; !isBigger && j < number.length; ++j)
			if (number[j] > bi.getNumber ()[j])
				isBigger = true;
		
		return isBigger;
	}
	
	public boolean isLowerThan (BigInt bi) {
		return !(isBiggerThan (bi) || areEquals (bi));
	}
	
	public boolean isBiggerOrEqual (BigInt bi) {
		return isBiggerThan (bi) || areEquals (bi);
	}
	
	public boolean isLowerOrEqual (BigInt bi) {
		return isLowerThan (bi) || areEquals (bi);
	}
}
