// Ejercicio 7.28. Simulacion: la tortuga y la liebre
package deitel.probability.turtleAndRabbit;

import java.util.Random;

public class Rabbit {
	public byte position = 1;
	private Random randomMove = new Random ();

	/**
	 *
	 * It simulates a rabbit's move: 20% sleep (it doesn't make a move), 20% bigJump, 10% bigSlip, 30% littleJump
	 * and 20% littleSlip.
	 */
	public void makeArandomMove () {
		byte possibility = (byte) randomMove.nextInt (10);
		
		if (possibility > 6)
			littleJump ();
		else if (possibility > 4)
			littleSlip ();
		else if (possibility > 2)
			bigJump ();
		else if (possibility == 0)
			bigSlip ();
	}
	
	/**
	 *
	 * It increases (plus 9) the position.
	 */
	private void bigJump () {
		position += 9;
		position = position > 70 ? 70: position;
	}
	
	/**
	 *
	 * It increases (plus 1) the position.
	 */
	private void littleJump () {
		++position;
		position = position > 70 ? 70: position;
	}
	
	/**
	 *
	 * It decreases (less 12) the position.
	 */
	private void bigSlip () {
		position -= 12;
		position = position < 1 ? 1: position;
	}
	
	/**
	 *
	 * It decreases (less 2) the position.
	 */
	private void littleSlip () {
		position -= 2;
		position = position < 1 ? 1: position;
	}
}
