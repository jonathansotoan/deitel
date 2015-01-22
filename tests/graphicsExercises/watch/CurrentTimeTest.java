package deitel.tests.graphicsExercises.watch;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import deitel.graphicsExercises.watch.CurrentTime;
import deitel.graphicsExercises.watch.Time;
import deitel.tests.util.Utils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for {@link deitel.graphicsExercises.watch.CurrentTime} class.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 19/01/15
 */
public class CurrentTimeTest {
	private CurrentTime currentTime;

	// milliseconds for events
	private long initMillis;
	private long secondChangedMillis;
	private long minuteChangedMillis;
	private long hourChangedMillis;

	// flags for checking the milliseconds taken
	private boolean secondTaken;
	private boolean minuteTaken;

	// listeners
	private ActionListener hourListener;
	private ActionListener minuteListener;
	private ActionListener secondListener;

	// error margins (milliseconds)
	private final short SECOND_DELTA = 65;
	private final short MINUTE_DELTA = 190;
	private final short HOUR_DELTA = 270; // full hours are not tested here (it would be too boring)

	// Time objects for testing
	private Time longTestTime;
	private Time shortTestTime;

	// for testStopListenersCalls method
	private boolean callsStopped;

	// Time object to test when hour is changed
	private Time timeOnHourChange;

	// for feedback during tests
	private int secondsPassed;

	/**
	 * Creates CurrentTime object ready for being tested.
	 */
	@Before
	public void setUp() {
		System.out.println("This test will run for about 132 seconds");

		hourListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				timeOnHourChange = new Time(currentTime.getTime());
				hourChangedMillis = System.currentTimeMillis();
			}
		};

		minuteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(!minuteTaken) {
					minuteChangedMillis = System.currentTimeMillis();
					minuteTaken = true;
				}
			}
		};

		secondListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.print(++secondsPassed + " ");

				if(!secondTaken) {
					secondChangedMillis = System.currentTimeMillis();
					secondTaken = true;
				}
			}
		};

		
		longTestTime = new Time((byte) 5, (byte) 58, (byte) 0);
		shortTestTime = new Time((byte) 11, (byte) 59, (byte) 59);
	}

	/**
	 * Restart some variables for every test. This make every test independent
	 * from the others.
	 */
	public void restart() {
		secondTaken = minuteTaken = false;
		hourChangedMillis = minuteChangedMillis = secondChangedMillis = 0;
		currentTime = new CurrentTime(hourListener, minuteListener, secondListener);
		initMillis = System.currentTimeMillis();
	}

	/**
	 * Is CurrentTime class able to call the provided listeners at the
	 * right time (this test is about 122 seconds long)?
	 */
	@Test
	public void testCurrentTimeLongTest() {
		try {
			restart();
			setTime(currentTime, longTestTime);

			Thread.sleep(124000);
			currentTime.stopListenersCalls();

			System.out.println("time: "+timeOnHourChange);
			System.out.println("time2: "+currentTime.getTime());

			assertAreEqual("second", secondChangedMillis,
				initMillis + 1000, SECOND_DELTA);
			assertAreEqual("minute", minuteChangedMillis,
				initMillis + 60000, MINUTE_DELTA);
			assertAreEqual("hour", hourChangedMillis,
				initMillis + 120000, HOUR_DELTA);
			assertEquals(
				"[Times are not equal] =>",
				new Time((byte) 6, (byte) 0, (byte) 0),
				timeOnHourChange
			);
		} catch(InterruptedException ie) {
			fail("\nSomething went wrong trying to sleep the current Thread" + Utils.throwableToString(ie));
		}
	}

	/**
	 * Is CurrentTime class able to call the provided listeners at the
	 * right time (this test is just about 1.5 seconds long)?
	 */
	@Test
	public void testCurrentTimeShortTest() {
		try {
			restart();
			setTime(currentTime, shortTestTime);

			Thread.sleep(1500);
			currentTime.stopListenersCalls();

			assertAreEqual("second", secondChangedMillis,
				initMillis + 1000, SECOND_DELTA);
			assertAreEqual("minute", minuteChangedMillis,
				initMillis + 1000, SECOND_DELTA);
			assertAreEqual("hour", hourChangedMillis,
				initMillis + 1000, SECOND_DELTA);
			assertEquals(
				"[Times are not equal] =>",
				new Time((byte) 0, (byte) 0, (byte) 0),
				currentTime.getTime()
			);
		} catch(InterruptedException ie) {
			fail("\nSomething went wrong trying to sleep the current Thread" + Utils.throwableToString(ie));
		}
	}

	/**
	 * Is stopListenersCalls method able to actually stop the calls (this test
	 * is about 6 seconds long)?
	 */
	@Test
	public void testStopListenersCalls() {
		try {
			restart();

			currentTime = new CurrentTime(null, null, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					callsStopped = false;
				}
			});

			Thread.sleep(2500);

			currentTime.stopListenersCalls();
			callsStopped = true;

			Thread.sleep(3500);

			assertTrue(callsStopped);
		} catch(InterruptedException ie) {
			fail("\nSomething went wrong trying to sleep the current Thread" + Utils.throwableToString(ie));
		}
	}

	/**
	 * This is a shortcut for asserting that the specified field has the
	 * expected value with a delta error oscilation.
	 * <p/>
	 * @param	field		the name of the field that is checked. This is just for a good feedback
	 * @param	expected	the expected value
	 * @param	actual		the given actual value
	 * @param	delta		says how much the actual value can be away from the expected one without failing
	 */
	private void assertAreEqual(String field, long expected, long actual, short delta) {
		assertTrue(
			"[The " + field + " from CurrentTime class is too inexact (error margin of "
				+ delta + ")] => expected<" + expected + "> actual<" + actual
				+ ">, difference: " + (actual - expected),
			actual > expected - delta && actual < expected + delta
		);
	}

	/**
	 * Sets the specified time to the CurrentTime object via Reflection as such
	 * field is private and it is not possible to do it directly.
	 * <p/>
	 * @param	currentTimeObj	the object where the change must be done
	 * @param	time			the value that is going to be asigned
	 */
	private void setTime(CurrentTime currentTimeObj, Time time) {
		try {
			Field privateTime = currentTimeObj.getClass().getDeclaredField("currentTime");
			privateTime.setAccessible(true);
			privateTime.set(currentTimeObj, time);
			privateTime.setAccessible(false);
		} catch(Exception e) {
			fail("\nSomething went wrong trying to set the CurrentTime's Time" + Utils.throwableToString(e));
		}
	}
}
