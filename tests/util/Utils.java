package deitel.tests.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Contains some utils for use along the project.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 20/01/15
 */
public class Utils {
	final static String HIGHLIGHTER = "-----------------";
	
	/**
	 * Returns a String representation of the Throwable object. It is the same
	 * as the printed by the {@link Throwable.printStackTrace()} method, except
	 * for the highlighters added to make it easy to identify it when it is used
	 * with a test.
	 * <p/>
	 * @param	throwable	the object used to make its representation
	 * @return				a String representation of the specified Throwable
	 */
	public static String throwableToString(Throwable throwable) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		throwable.printStackTrace(new PrintStream(outStream));
		
		return
			new StringBuilder("\n")
				.append(HIGHLIGHTER)
				.append(" Start StackTrace ")
				.append(HIGHLIGHTER)
				.append('\n')
				.append(outStream.toString())
				.append(HIGHLIGHTER)
				.append(" End StackTrace ")
				.append(HIGHLIGHTER)
				.append('\n').toString();
	}
}
