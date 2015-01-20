package deitel.graphicsExercises.watch;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.Timer;

/**
 * This class represents the current time and you can provide ActionListener
 * objects for execute when second, minute or hour is changed. Note that the
 * time is not 100% precise; it has an error margin of 0.7 seconds.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 19/01/15
 */
public class CurrentTime {
	private byte hour;
	private byte minute;
	private byte second;
	private Timer timer;

	/**
	 * Creates a CurrentTime object with the specified listeners for hour,
	 * minute and second respectively (these listeners can be set to null).
	 * </p>
	 * @param	hourChanged		the listener that is going to be called when the current hour changes
	 * @param	minuteChanged	the listener that is going to be called when the current minute changes
	 * @param	secondChanged	the listener that is going to be called when the current second changes
	 */
	public CurrentTime(
		final ActionListener hourChanged,
		final ActionListener minuteChanged,
		final ActionListener secondChanged
	) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				second = increment(second);
				if(secondChanged != null) secondChanged.actionPerformed(ae);

				if(second == 0) {
					minute = increment(minute);
					if(minuteChanged != null) minuteChanged.actionPerformed(ae);

					if(minute == 0) {
						hour = increment(hour);
						if(hourChanged != null) hourChanged.actionPerformed(ae);
					}
				}
			}
		});

		hour = (byte) Calendar.getInstance().get(Calendar.HOUR);
		minute = (byte) Calendar.getInstance().get(Calendar.MINUTE);
		second = (byte) Calendar.getInstance().get(Calendar.SECOND);
		timer.start();
	}

	/**
	 * Returns the current hour.
	 * <p/>
	 * @return	the current hour
	 */
	public byte getHour() {
		return hour;
	}

	/**
	 * Returns the current minute.
	 * <p/>
	 * @return	the current minute
	 */
	public byte getMinute() {
		return minute;
	}

	/**
	 * Returns the current second.
	 * <p/>
	 * @return	the current second
	 */
	public byte getSecond() {
		return second;
	}

	/**
	 * Takes the original value and returns it incremented by one with a maximum
	 * of 59. This means that if the original value is 59, 0 is going to be
	 * returned.
	 * <p/>
	 * @param	originalValue		the original value that is going to be incremented by 1
	 * @return						the original value plus 1 (with a maximum of 59)
	 * @throws	WrongHourException	when original value is greater or equal than 60
	 */
	private byte increment(byte originalValue) throws WrongHourException {
		if(originalValue > 59)
			throw new WrongHourException("The maximum of a second/minute/hour must be 59, and it is currently " + originalValue);

		return (byte) ((originalValue + 1) % 60);
	}

	/**
	 * Returns a String representation of this CurrentTime with the following
	 * format: HH:MM:SS.
	 * <p/>
	 * @return				a String representation of the time with the following format: HH:MM:SS
	 */
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}

	/**
	 * It is an exception that is thrown when 60 or a greater number is used to
	 * increment the hour, minute or second (it is possible via setter methods).
	 * <p/>
	 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
	 * @version	1, 19/01/15
	 */
	public class WrongHourException extends Exception {

		public WrongHourException(String message) {
			super(message);
		}
	}
}
