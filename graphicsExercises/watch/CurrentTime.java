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
	private Time currentTime;
	private Timer timer;

	/**
	 * Creates a CurrentTime object with the specified listeners for hour,
	 * minute and second respectively (these listeners can be set to null). If
	 * you want that the calls to the specified listeners stop, call
	 * stopListenersCalls method.
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
				boolean[] changeLog = currentTime.incrementSecond();

				if(secondChanged != null)
					secondChanged.actionPerformed(ae);

				if(minuteChanged != null && changeLog[1])
					minuteChanged.actionPerformed(ae);

				if(hourChanged != null && changeLog[0])
					hourChanged.actionPerformed(ae);
			}
		});

		currentTime = new Time(
			(byte) Calendar.getInstance().get(Calendar.HOUR),
			(byte) Calendar.getInstance().get(Calendar.MINUTE),
			(byte) Calendar.getInstance().get(Calendar.SECOND)
		);
		timer.start();
	}

	/**
	 * Returns the current time represented with a Time object.
	 * <p/>
	 * @return				a Time object representing the current time
	 */
	public Time getTime() {
		return currentTime;
	}

	/**
	 * Make this object stop calling the listeners from the current object.
	 * Note: You should call this method after you remove every reference to
	 * your object.
	 */
	public void stopListenersCalls() {
		timer.stop();
	}

	@Override
	protected void finalize() {
		timer.stop();
	}
}
