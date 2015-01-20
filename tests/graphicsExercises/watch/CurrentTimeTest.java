package deitel.tests.graphicExercises.watch;

import deitel.graphicExercises.watch.CurrentTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link deitel.graphicsExercises.watch.CurrentTime} class.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 19/01/15
 */
public class CurrentTimeTest {
	
	/**
	 * Is increment method able to increment the original value by 1 with easy
	 * cases?
	 */
	@Test
	public void testIncrementEasyNumbers() {
		for(byte originalValue = 0; originalValue < 60; ++originalValue)
			shortcutIncrement(originalValue + 1, originalValue);
	}

	/**
	 * Is increment method able to increment the original value by 1 with the
	 * hardest possible case?
	 */
	@Test
	public void testIncrementHardestNumber() {
		shortcutIncrement(0, 59);
	}

	/**
	 * Does increment method throw a
	 * {@link deitel.graphicExercises.watch.CurrentTime.WrongHourException}
	 * exception when the number is greater or equal than 60?
	 */
	@Test
	public void testIncrementInvalidNumbers() {
		try {
			Calculating.getEllipsePerimeter(-87, 76);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(-87, 76)]");
			Calculating.getEllipsePerimeter(867, -876);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(867, -876)]");
			Calculating.getEllipsePerimeter(-1, -76);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(-1, -76)]");
		} catch(CurrentTime.WrongHourException whe) {
			// cotrol is never going to get here, even if the test fails
		}
	}

	/**
	 * This is a helper for testing increment method faster.
	 * <p/>
	 * @param	expectedValue	the expected value after incrementing
	 * @param	originalValue	the original value that is going to be tested
	 */
	public void shortcutIncrement(byte expectedValue, byte originalValue) {
		try {
			assertEquals("[Increment of " + originalValue + "] =>", expectedValue, increment(originalValue));
		} catch(CurrentTime.WrongHourException whe) {
			fail(whe.getMessage());
		}

	}
}
