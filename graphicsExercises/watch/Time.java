package deitel.graphicsExercises.watch;

/**
 * Represents a certain point of time with hour, minute, and second.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 21/01/15
 */
public class Time {
	private byte hour;
	private byte minute;
	private byte second;

	/**
	 * Creates a new Time with the specified hour, minute and second.
	 * <p/>
	 * @param	hour	the hour of this Time object
	 * @param	minute	the minute of this Time object
	 * @param	second	the second of this Time object
	 */
	public Time(byte hour, byte minute, byte second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/**
	 * Returns the hour of this Time object.
	 * <p/>
	 * @return	the hour of this Time object
	 */
	public byte getHour() {
		return hour;
	}

	/**
	 * Returns the minute of this Time object.
	 * <p/>
	 * @return	the minute of this Time object
	 */
	public byte getMinute() {
		return minute;
	}

	/**
	 * Returns the second of this Time object.
	 * <p/>
	 * @return	the second of this Time object
	 */
	public byte getSecond() {
		return second;
	}

	/**
	 * Increments the second of this hour by one. If it is necessary, minute and
	 * hour are also changed (evaluated separatelly). Returns a boolean array
	 * with a length of 3 indicating whether or not the hour, minute, and second
	 * were incremented (the second's index:2 is always true).
	 * <p/>
	 * @return		a boolean that indicates if hour, minute and/or second fields were changed in the that order
	 */
	public boolean[] incrementSecond() {
		boolean[] changeLog = new boolean[3];
		second = (byte) ((second + 1) % 60);
		changeLog[2] = true;

		if(second == 0) {
			minute = (byte) ((minute + 1) % 60);
			changeLog[1] = true;

			if(minute == 0) {
				hour = (byte) ((hour + 1) % 12);
				changeLog[0] = true;
			}
		}

		return changeLog;
	}

	/**
	 * Returns a String representation of this Time with the following format:
	 * HH:MM:SS.
	 * <p/>
	 * @return				a String representation of the time with the following format: HH:MM:SS
	 */
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}

	/**
	 * Returns true if the specified object is equal to this one.
	 * <p/>
	 * @param	other	the other object that is going to be compared with this one
	 * @return			true if the specified object is equal to this one, false if they are not
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Time))
			return false;

		Time other = (Time) obj;

		return getSecond() == other.getSecond()
			&& getMinute() == other.getMinute()
			&& getHour() == other.getHour();
	}

	/**
	 * Returns the hash code of this object. Note that 2 objects of this class
	 * does not return the same has code, even when they have the same hour (it
	 * means when equals return true).
	 * <p/>
	 * @return		the hash code of this object.
	 */
	@Override
	public int hashCode() {
		return getHour() * 3600 + getMinute() * 60 + getSecond()
			+ super.hashCode();
	}
}