package deitel.tests.util;

/**
 * Contains some utils for use along the project.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 20/01/15
 */
public class Utils {
	final static String HIGHLIGHTER = "-----------------";
	
	/**
	 * Returns a String representation of the StackTraceElements similar to
	 * the printed by the {@link Throwable.printStackTrace()} method and with
	 * a highlighter to make it easy to identify it when it is used with a test.
	 * <p/>
	 * @param	stackTraceElements	the elements that are going to be converted
	 * @return						a String representation of the specified elements
	 */
	public static String stackTraceElementsToString(StackTraceElement[] stackTraceElements) {
		StringBuilder representation =
			new StringBuilder("\n")
				.append(HIGHLIGHTER)
				.append(" Start StackTrace ")
				.append(HIGHLIGHTER)
				.append('\n');

		for(StackTraceElement element : stackTraceElements)
			representation.append(element).append('\n');

		return representation
			.append(HIGHLIGHTER)
			.append(" End StackTrace ")
			.append(HIGHLIGHTER)
			.append('\n').toString();
	}
}
