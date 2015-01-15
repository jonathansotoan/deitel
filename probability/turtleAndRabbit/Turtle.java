// Ejercicio 7.28. Simulacion: la tortuga y la liebre
package deitel.probability.turtleAndRabbit;

import java.util.Random;

public class Turtle {
	public byte position = 1;
	private Random randomMove = new Random ();
	
	/**
	 *
	 * It simulates a turtle's move: 50% heavyFastStep, 20% slip and 30% heavySlowStep.
	 */
	public void makeArandomMove () {
		byte possibility = (byte) randomMove.nextInt (10);
		
		if (possibility > 4)
			heavyFastStep ();
		else if (possibility > 1)
			heavySlowStep ();
		else
			slip ();
	}
	
	/**
	 *
	 * It increases (plus 3) the position.
	 */
	private void heavyFastStep () {
		position += 3;
		position = position > 70 ? 70: position;
	}
	
	/**
	 *
	 * It decreases (less 6) the position.
	 */
	private void slip () {
		position -= 6;
		position = position < 1 ? 1: position;
	}
	
	/**
	 *
	 * It increases (plus 1) the position.
	 */
	private void heavySlowStep () {
		++position;
		position = position > 70 ? 70: position;
	}
}
