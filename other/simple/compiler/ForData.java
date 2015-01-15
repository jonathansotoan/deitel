//Exercise 17.29.i
package deitel.other.simple.compiler;

public class ForData {
	public final String VAR;
	public final double IF_LINE;
	public final int LINE_AFTER_NEXT;
	public final String STEP;
	public final int FLAG_POSITION;

	ForData( final String NEW_VAR, final double NEW_IF_LINE, final int NEW_LINE_AFTER_NEXT, final String NEW_STEP, final int NEW_FLAG_POSITION ) {
		VAR = NEW_VAR;
		IF_LINE = NEW_IF_LINE;
		LINE_AFTER_NEXT = NEW_LINE_AFTER_NEXT;
		STEP = NEW_STEP;
		FLAG_POSITION = NEW_FLAG_POSITION;
	}
}
