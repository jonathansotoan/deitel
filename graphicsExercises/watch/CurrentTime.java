package deitel.graphicsExercises.watch;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.Timer;

public class CurrentTime {
	private byte hour;
	private byte minute;
	private byte second;
	private Timer timer;

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

	public byte getHour() {
		return hour;
	}

	public byte getMinute() {
		return minute;
	}

	public byte getSecond() {
		return second;
	}

	private byte increment(byte originalValue) {
		return (byte) ((originalValue + 1) % 60);
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}
}
