package deitel.tests.graphicsExercises.watch;

import deitel.graphicsExercises.watch.Time;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link deitel.graphicsExercises.watch.Time} class.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 21/01/15
 */
@RunWith(Parameterized.class)
public class TimeTest {
	@Parameters(name="{index}: Time({0}:{1}:{2})")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ 7, 56, 48},
			{ 3, 21, 34},
			{10, 42, 12},
			{ 0,  0,  0},
			{ 0, 45,  1},
			{11, 59, 59},
			{11, 10, 59}
		});
	}

	private Time time;
	private byte hour;
	private byte minute;
	private byte second;

	/**
	 * Creates a TimeTest object used to run the tests. The values are provided
	 * by {@link data} method.
	 * <p/>
	 * @param	hour	the hour for these tests.
	 * @param	minute	the minute for these tests.
	 * @param	second	the second for these tests.
	 */
	public TimeTest(int hour, int minute, int second) {
		this.hour = (byte) hour;
		this.minute = (byte) minute;
		this.second = (byte) second;
		time = new Time(this.hour, this.minute, this.second);
	}

	/**
	 * Are the constructors able to create right Time objects?
	 */
	@Test
	public void testConstructors() {
		final int COPIER_CONSTRUCTOR_TESTS = 10;
		assertTimeEquals(hour, minute, second, time);

		for(byte i = 0; i < COPIER_CONSTRUCTOR_TESTS; ++i) {
			time = new Time(time);
			assertTimeEquals(hour, minute, second, time);
			assertTimeEquals(hour, minute, second, time);
		}
	}

	/**
	 * Is incrementSecond method able to right increment seconds?
	 * Is also able to increment the minute and hour values when it is
	 * necessary?
	 */
	@Test
	public void testIncrementSecondMinuteAndHour() {
		String message =  " change indicator is not working (last time: " + time + ", current time: ";
		boolean[] changed = time.incrementSecond();
		message += time + ") ";

		boolean expected = second == 59 && minute == 59 ? changed[0] : !changed[0];
		assertTrue("[Hour" + message + "expected<" + expected + "> but got<"
			+ !expected + ">]", expected);

		expected = second == 59 ? changed[1] : !changed[1];
		assertTrue("[Minute" + message + "expected<" + expected + "> but got<"
			+ !expected + ">]", expected);

		assertTrue("[Second" + message + "expected<true> but got<false>]", changed[2]);

		assertTimeEquals(
			(byte) ((hour + (minute + ++second / 60) / 60) % 12),
			(byte) ((minute + second / 60) % 60),
			(byte) (second % 60),
			time
		);
	}

	/**
	 * Is toString method able to print a well standard formated Time?
	 */
	@Test
	public void testToStringFormat() {
		//Pattern pattern = Pattern.compile("(0[0-9]|1[12]):([0-5][0-9]):([0-5][0-9])");
		assertTrue("[The format of Time.toString method is not right. Expected pattern<(0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])> actual String<" + time + ">]", time.toString().matches("(0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])"));
	}

	/**
	 * Is equals method able to actually determine whether two objects are
	 * equal?
	 * Is hashCode method able to always return a different value for different
	 * objects? even when the objects are equal?
	 */
	@Test
	public void testEqualsAndHashCode() {
		final String EQU_MSG = " should be equal to ";
		final String NEQ_MSG = " should not be equal to ";

		Time tempTime = new Time(hour, minute, second);
		assertTrue(time + EQU_MSG + tempTime, time.equals(tempTime));
		assertNotEquals("Hash codes: " + tempTime.hashCode() + NEQ_MSG + time.hashCode(),
			tempTime.hashCode(), time.hashCode());

		Time tempTime2 = new Time(
			(byte) (hour + (minute + ++second / 60) / 60),
			(byte) (minute + second / 60),
			(byte) (second % 60)
		);
		time.incrementSecond();
		assertTrue(time + EQU_MSG + tempTime2, time.equals(tempTime2));
		assertNotEquals("Hash codes: " + tempTime2.hashCode() + NEQ_MSG + time.hashCode(),
			tempTime2.hashCode(), time.hashCode());

		assertFalse(time + NEQ_MSG + tempTime, time.equals(tempTime));
		assertNotEquals("Hash codes: " + tempTime.hashCode() + NEQ_MSG + time.hashCode(),
			tempTime.hashCode(), time.hashCode());

		assertFalse(tempTime + NEQ_MSG + tempTime2, tempTime.equals(tempTime2));
		assertNotEquals("Hash codes: " + tempTime2.hashCode() + NEQ_MSG + tempTime.hashCode(),
			tempTime2.hashCode(), tempTime.hashCode());
	}

	/**
	 * Asserts that two Time objects are equal without using {@link equals}
	 * method.
	 * <p/>
	 * @param	expectedHour	the expected hour for time object
	 * @param	expectedMinute	the expected minute for time object
	 * @param	expectedSecond	the expected second for time object
	 * @param	time			the actual time
	 */
	private void assertTimeEquals(byte expectedHour, byte expectedMinute, byte expectedSecond, Time time) {
		String message = " are not equal (expected time<" + new Time(expectedHour, expectedMinute, expectedSecond) + "> actual time<" + time + ">)] =>";
		assertEquals("[The hours" + message, expectedHour, time.getHour());
		assertEquals("[The minutes" + message, expectedMinute, time.getMinute());
		assertEquals("[The seconds" + message, expectedSecond, time.getSecond());
	}
}
