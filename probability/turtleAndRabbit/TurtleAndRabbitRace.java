// Ejercicio 7.28. Simulacion: la tortuga y la liebre
package deitel.probability.turtleAndRabbit;

public class TurtleAndRabbitRace {

	public static void main (String args[]) {
		Turtle trtl = new Turtle ();
		Rabbit rbt = new Rabbit ();
		
		while (rbt.position < 70 && trtl.position < 70) {
			trtl.makeArandomMove ();
			rbt.makeArandomMove ();
			
			System.out.print ("|");
			for (byte j = 1; j <= 70; ++j)
				if (rbt.position == j && trtl.position == j)
					System.out.print ("OUCH!!");
				else if (rbt.position == j)
					System.out.print ("R");
				else if (trtl.position == j)
					System.out.print ("T");
				else
					System.out.print (" ");
			
			System.out.println ("|");
		}
		
		System.out.printf ("The winner is the %s\n", trtl.position > 69 ? "turtle": "rabbit");
	}
}
