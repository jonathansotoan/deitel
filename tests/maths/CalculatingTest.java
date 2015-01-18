package deitel.tests.maths;

import deitel.maths.Calculating;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for {@link deitel.maths.Calculating}.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 17/01/15
 */
public class CalculatingTest {
	private final float DELTA = 0.3f;// the acceptable difference a float number can be considered equal to other
	
	// Hyptenuse
	@Test
	public void testHypotenuseSmallSides() {
		shortHandGetHypotenuse(3, 4, 5);
		shortHandGetHypotenuse(94, 73, 119);
		shortHandGetHypotenuse(186, 47, 192);
		shortHandGetHypotenuse(714, 27, 715);
		shortHandGetHypotenuse(1, 1, 1);
	}
	
	@Test
	public void testHypotenuseLargerSides() {
		shortHandGetHypotenuse(1514, 7571, 7721);
		shortHandGetHypotenuse(5725, 5455, 7908);
		shortHandGetHypotenuse(9714, 9407, 13522);
		shortHandGetHypotenuse(4487, 17, 4487);
		shortHandGetHypotenuse(11111, 11111, 15713);
	}
	
	@Test
	public void testHypotenuseNegativeSides() {
		try {
			Calculating.getHypotenuse(-15, 26);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getHypotenuse(-15, 26)]");
			Calculating.getHypotenuse(347, -98);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getHypotenuse(347, -98)]");
			Calculating.getHypotenuse(-13, -78);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getHypotenuse(-13, -78)]");
		} catch(Calculating.NegativeArgumentException tddee) {
			// cotrol is never going to get here
		}
	}
	
	@Test
	public void testHypotenuseNonSides() {
		shortHandGetHypotenuse(0, 541, 541);
		shortHandGetHypotenuse(8754, 0, 8754);
		shortHandGetHypotenuse(0, 0, 0);
	}

	/**
	 * This is just a helper for calling Calculating.getHypotenuse method.
	 * <p/>
	 * @param	side1				the first side of the right triangle
	 * @param	side2				the second side of the right triangle
	 * @param	expectedHypotenuse	the expected result for the hypotenuse calculated from side1 and side2
	 */
	private void shortHandGetHypotenuse(int side1, int side2, int expectedHypotenuse) {
		try {
			assertEquals("[Hypotenuse with sides " + side1 + " and " + side2 + "] =>", expectedHypotenuse, Calculating.getHypotenuse(side1, side2));
		} catch(Calculating.NegativeArgumentException tdnee) {
			fail(tdnee.getMessage());
		}
	}

	// ellipse
	@Test
	public void testEllipsePerimeterSmallRadiouses() {
		shortHandGetEllipsePerimeter(5, 7, 37.96);
		shortHandGetEllipsePerimeter(54, 94, 473.49);
		shortHandGetEllipsePerimeter(18, 20, 119.46);
		shortHandGetEllipsePerimeter(108, 152, 822.67);
		shortHandGetEllipsePerimeter(91, 90, 568.63);
	}

	@Test
	public void testEllipsePerimeterLargerRadiouses() {
		shortHandGetEllipsePerimeter(507, 705, 3833.06);
		shortHandGetEllipsePerimeter(5017, 3405, 26701.38);
		shortHandGetEllipsePerimeter(43127, 34015, 243194.81);
		shortHandGetEllipsePerimeter(873216, 823127, 5330380.3816);
		shortHandGetEllipsePerimeter(152348, 215410, 1163854.54);
	}

	@Test
	public void testEllipsePerimeterNegativeRadiouses() {
		try {
			Calculating.getEllipsePerimeter(-87, 76);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(-87, 76)]");
			Calculating.getEllipsePerimeter(867, -876);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(867, -876)]");
			Calculating.getEllipsePerimeter(-1, -76);
			fail("[Calculating.NegativeArgumentException expected, for calling Calculating.getEllipsePerimeter(-1, -76)]");
		} catch(Calculating.NegativeArgumentException tddee) {
			// cotrol is never going to get here
		}
	}

	@Test
	public void testEllipsePerimeterNonRadiouses() {
		shortHandGetEllipsePerimeter(4456, 0, 17749.9407);
		shortHandGetEllipsePerimeter(0, 4459, 17761.89);
		shortHandGetEllipsePerimeter(0, 0, 0);
	}

	@Test
	public void testEllipsePerimeterBigDifferenceBetweenRadiuses() {
		shortHandGetEllipsePerimeter(507, 1500, 6697.14);
		shortHandGetEllipsePerimeter(18, 3405, 13570.27);
		shortHandGetEllipsePerimeter(3127, 9000, 40365.7);
		shortHandGetEllipsePerimeter(73216, 214014, 957405.86);
		shortHandGetEllipsePerimeter(1348, 215410, 858579.83);
	}

	/**
	 * This is just a helper for calling Calculating.getEllipsePerimeter method.
	 * <p/>
	 * @param	radius1				the first radius of the ellipse
	 * @param	radius2				the second radius of the ellipse
	 * @param	expectedPerimeter	the expected result for the perimeter of the ellipse calculated from radius1 and radius2
	 */
	private void shortHandGetEllipsePerimeter(int radius1, int radius2, double expectedPerimeter) {
		try {
			assertEquals("[Ellipse with radiuses " + radius1 + " and " + radius2 + "] =>", expectedPerimeter, Calculating.getEllipsePerimeter(radius1, radius2), DELTA);
		} catch(Calculating.NegativeArgumentException tdnee) {
			fail(tdnee.getMessage());
		}
	}
}