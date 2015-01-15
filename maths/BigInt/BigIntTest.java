public class BigIntTest {

	public static void main (String args[]) {
		char[] num = {'1', '2', '3', 'a'};
	
		BigInt bInt = new BigInt (num);
		System.out.println (bInt);
		
		System.out.print (bInt);
		bInt.add (9385);
		System.out.println (" + 9385 = " + bInt);
		
		System.out.print (bInt);
		bInt.substract (1230);
		System.out.println (" - 1230 = " + bInt);
		
		System.out.println ("Insert a big int");
		BigInt bInt2 = BigInt.nextBigInt ();
		
		BigInt b2 = new BigInt (bInt2.getNumber ());
		BigInt b3 = new BigInt (bInt2.getNumber ()); b3.add (1);
		BigInt b4 = new BigInt (bInt2.getNumber ()); b4.substract (1);
		System.out.printf ("Boolean methods (predicates)\nall should be true\n%s.areEquals (%s) = %b\n%s.isBiggerThan (%s) = %b\n%s.isLowerThan (%s) = %b\n\nall should be false\n%s.areEquals (%s) = %b\n%s.isBiggerThan (%s) = %b\n%s.isLowerThan (%s) = %b\n\n",
								bInt2, b2, bInt2.areEquals (b2),
								b3, bInt2, b3.isBiggerThan (bInt2),
								b4, bInt2, b4.isLowerThan (bInt2),
								bInt2, b4, bInt2.areEquals (b4),
								b4, bInt2, b4.isBiggerThan (bInt2),
								bInt2, b4, bInt2.isLowerThan (b2));
	}
}
