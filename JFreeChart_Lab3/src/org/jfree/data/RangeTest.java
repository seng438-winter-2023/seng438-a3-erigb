package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range NaNRange;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	NaNRange = new Range (Double.NaN, Double.NaN);
    }

    /**
     * NEW FOR ASSIGNMENT 3
     * Test that the constructor will throw an IllegalArgumentException when the upper bound is less than the lower bound
     */
    @Test
    public void constructorThrowsExceptionWhenValuesAreReversed() {
		try {
			Range invalidRange = new Range(5, 2);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					exception.getClass());
		}
    }

    /***
     * This test will check that the getLength function returns the correct value.
     * Expected output is 2
     */
    @Test
    public void lengthShouldBeTwo() {
        assertEquals("The length of Range from -1 to 1 should be 2",
        		2, exampleRange.getLength(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This will check that the getCentralValue method returns the correct value
     */
    @Test
    public void getCentralValueShouldBeZero() {
    	assertEquals("The central value of the Range (-1, 1) is 0", 0, exampleRange.getCentralValue(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This will check that the toString method returns the correct conversion
     */
    @Test
    public void toStringValidRange() {
    	String s = "Range[-1.0,1.0]";
    	assertEquals("toString() should convert Range to a String", s, exampleRange.toString());
    }
    
    /***
     * This test will check that the contains function returns the correct value when the 
     * given value is contained within the range.
     * Expected output is True
     */
    @Test
    public void containsShouldBeTrue() {
    	assertTrue("The Range does contain value 0.5. Contains should return true",
    	exampleRange.contains(0.5));
    }
    
    /**
     * This test will check that the contains function returns the correct value when the
     * given value is lower than the lower bound of the range.
     * Expected output is False
     */
    @Test
    public void containsShouldBeFalseWithLowerValue() {
    	assertFalse("The Range does not contain value -7. Contains should return false",
    	exampleRange.contains(-7));
    }
    
    /**
     * This test will check that the contains function returns the correct value when the
     * given value is higher than the upper bound of the range.
     * Expected output is False
     */
    @Test
    public void containsShouldBeFalseWithHigherValue() {
    	assertFalse("The Range does not contain value 10. Contains should return false",
    	exampleRange.contains(10));
    }
    
    /***
	 * This test will test intersects by using two ranges that are the same (fully overlapping).
	 * Expected output is True.
	 */
    @Test
    public void intersectsShouldBeTrueWithEqualRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (-1, 1). Contains should return true",
    	exampleRange.intersects(-1, 1));
    }
    
    /***
	 * This test will test intersects by using a specified range that slightly overlaps with the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsShouldBeTrueWithPartiallyOverlappingUpperRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (0, 2). Contains should return true",
    	exampleRange.intersects(0, 2));
    }
    
    /***
	 * This test will test intersects by using a specified range that fully encompasses the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsShouldBeTrueWithFullyOverlappingRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (-5, 5). Contains should return true",
    	exampleRange.intersects(-5, 5));
    }
    
    /***
	 * This test will test intersects by using a specified range is fully covered by the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsShouldBeTrueWithPartiallyOverlappingSmallerRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (0, 1). Contains should return true",
    	exampleRange.intersects(0, 1));
    }
    
    /***
	 * This test will test intersects by using a specified range that does not overlap with the example range.
	 * Expected output is False.
	 */
    @Test
    public void intersectsShouldBeFalseWithHigherRange() {
    	assertFalse("The Range (-1, 1) does not intersects the current range (-1, 1). Contains should return false",
    	exampleRange.intersects(2, 3));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (increase branch coverage)
     * This test will test intersects with a range that is completely below the specified range.
     * Expected output is false
     */
    @Test
    public void intersectsShouldBeFalseWithLowerRange() {
    	assertFalse("The Range (-5, -2) does not intersect the current Range (-1, 1).", exampleRange.intersects(-5, -2));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (increase branch coverage)
     * This test will test intersects returns false when range is within specified range with b0 > b1
     */
    @Test
    public void intersectsShouldBeFalseWithb0BelowUpperAndb0GreaterThanb1() {
    	assertFalse("The Range (0, -0.5) does not intersect the current Range (-1, 1)", exampleRange.intersects(0, -0.5));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (increase statement coverage)
     * This test will check that intersects is correct when the ranges overlap
     */
    @Test
    public void intersectsWithAnotherRange() {
    	Range r2 = new Range (0, 7);
    	assertTrue("The ranges (-1, 1) and (0, 7) intersect", exampleRange.intersects(r2));
    }
    
    /***
	 * This test will test shifting a range with a positive value for delta that is small enough so as not to move -1 beyond 0
	 * Expected output is a new range from (-0.5, 1.5).
	 */
    @Test
    public void shiftRangeWithoutZeroCrossingPositveDelta() {
    	Range afterShift = Range.shift(exampleRange, 0.5);
    	
    	assertEquals("The lower bound should be 0.5 + -1 = -0.5",
    	        -0.5, afterShift.getLowerBound(), .000000001d);
    	assertEquals("The upper bound should be 0.5 + 1 = 1.5",
    	        1.5, afterShift.getUpperBound(), .000000001d);
    }
    
     /***
	 * This test will test shifting a range with a negative value for delta that is small enough so as not to move 1 beyond 0
	 * Expected output is a new range from (-1.5, 0.5).
	 */
    @Test
    public void shiftRangeWithoutZeroCrossingNegativeDelta() {
    	Range afterShift = Range.shift(exampleRange, -0.5);
    	
    	assertEquals("The lower bound should be -0.5 + -1 = -1.5",
    	        -1.5, afterShift.getLowerBound(), .000000001d);
    	assertEquals("The upper bound should be -0.5 + 1 = 0.5",
    	        0.5, afterShift.getUpperBound(), .000000001d);	
    }

    /***
	 * This test will test shifting a range with a value for delta that is big enough so that it moves -1 beyond 0
         * based on the documentation no zero crossing is allowed so -1 would be shifted to 0
	 * Expected output is a new range from (0, 6).
	 */
    @Test
    public void shiftShouldNotAllowZeroCrossing() {
        Range afterShift = Range.shift(exampleRange, 5.0);
        assertEquals("The upper bound should be 5.0 + 1 = 6.0",
                6.0, afterShift.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 0 since no zero crossing is allowed and -1 + 5 = 4 > 0",
                0.0, afterShift.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will test shifting when the lower bound of the range is 0 and zero-crossing is not allowed. 
     * Expected output is a new range from (1, 4)
     */
    @Test
    public void shiftWithoutZeroCrossingLowerBoundZero() {
    	Range actual = Range.shift(new Range(0, 3), 1.0);
    	assertEquals("The upper bound should be 3+1=4", 4, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be 0+1=1", 1, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will test shifting when the the lower bound of the range is below 0 and the
     * delta is large enough that the lower bound will cross 0
     */
    @Test
    public void shiftWithZeroCrossingCrossZero() {
    	Range actual = Range.shift(exampleRange, 1.5, true);
    	assertEquals("The upper bound should be 1+1.5=2.5", 2.5, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1+1.5=0.5", 0.5, actual.getLowerBound(), .000000001d);
    }
    
        /* This test will test the method expandtoInclude where we are passing in a range and the value that must be included.
         * Expected output should be a range that spans over the input range and has been expanded to included the input value.
	 */
    @Test
    public void expandToIncludeWithinRange() {
    	Range actual = Range.expandToInclude(exampleRange, 0.8);
    	assertEquals("The upper bound should be [-1, 1].",
    			1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be [-1, 1].",
    			-1, actual.getLowerBound(), .000000001d);
    }
    
        /* This test will test the method expandtoInclude where we are passing in a range and the values that must be included.
         * Expected output should be a range that spans over the input range and expands the upper bound to included the input value.
	 */
    @Test
    public void expandToIncludeOutsideUpperRange() {
    	Range actual = Range.expandToInclude(exampleRange, 1.5);
    	assertEquals("The upper bound should be 1.5.",
    			1.5, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1.",
    			-1, actual.getLowerBound(), .000000001d);
    }
    
        /* This test will test the method expandtoInclude where we are passing in a range and the values that must be included.
         * Expected output should be a range that spans over the input range and expands the lower bound to included the input value.
	 */
    @Test
    public void expandToIncludeOutsideLowerRange() {
    	Range actual = Range.expandToInclude(exampleRange, -1.5);
    	assertEquals("The upper bound should be 1.",
    			1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1.5.",
    			-1.5, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (increase branch coverage)
     * This test will check that expandToInclude will create a new Range object when the input is null
     */
    @Test
    public void expandToIncludeStartingRangeNull() {
    	Range actual = Range.expandToInclude(null, 4.3);
    	assertEquals("The upper bound should be 4.3.",
    			4.3, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be 4.3.",
    			4.3, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve method coverage)
     * This test will check that the combine method returns a null range when both input ranges are null
     */
    @Test
    public void combineTwoNullRanges() {
    	assertNull("Combining two null ranges should return a null range.", Range.combine(null, null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combine method returns range1 when the second input range is null
     */
    @Test
    public void combineSecondRangeNull() {
    	assertEquals("Combining with one null range should return the original range", exampleRange, Range.combine(exampleRange, null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combine method returns the correct range when the input ranges overlap
     */
    @Test
    public void combineTwoOverlappingRanges() {
    	Range r2 = new Range(0, 4);
    	Range actual = Range.combine(exampleRange, r2);

    	assertEquals("The upper bound should be 4", 4, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1", -1, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combine method returns the correct range when the input ranges are disjoint
     */
    @Test
    public void combineTwoDisjointRanges() {
    	Range r2 = new Range(-3, -2);
    	Range actual = Range.combine(exampleRange, r2);

    	assertEquals("The upper bound should be 1", 1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -3", -3, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve method coverage)
     * This test will check that the combineIgnoringNaN method returns a null range when both input ranges are null
     */
    @Test
    public void combineIgnoringNaNTwoNullRanges() {
    	assertNull("Combining two null ranges should return a null range.", Range.combineIgnoringNaN(null, null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns a null range when the first input range is null
     * and the second is a NaNRange
     */
    @Test
    public void combineIgnoringNaNRange2isNaNRange() {
    	assertNull("Combining a null range and a NaN range should return a null range.", Range.combineIgnoringNaN(null, NaNRange));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns range2 when the first input range is null
     */
    @Test
    public void combineIgnoringNaNRange1IsNull() {
    	assertEquals("Combining with one null range should return the original range", exampleRange, Range.combine(null, exampleRange));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns range1 when the second input range is null
     */
    @Test
    public void combineIgnoringNaNSecondRangeNull() {
    	assertEquals("Combining with one null range should return the original range", exampleRange, Range.combineIgnoringNaN(exampleRange, null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns null when one range is NaN and the other is null
     */
    @Test
    public void combineIgnoringNaNFirstRangeNaNSecondRangeNull() {
    	assertNull("Combining a null range and a NaN range should return a null range.", Range.combineIgnoringNaN(NaNRange, null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns the correct range when the input ranges overlap
     */
    @Test
    public void combineIgnoringNaNTwoOverlappingRanges() {
    	Range r2 = new Range(0, 4);
    	Range actual = Range.combineIgnoringNaN(exampleRange, r2);

    	assertEquals("The upper bound should be 4", 4, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1", -1, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns the correct range when the input ranges are disjoint
     */
    @Test
    public void combineIgnoringNaNTwoDisjointRanges() {
    	Range r2 = new Range(-3, -2);
    	Range actual = Range.combineIgnoringNaN(exampleRange, r2);

    	assertEquals("The upper bound should be 1", 1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -3", -3, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that the combineIgnoringNaN method returns a null range when the input ranges are NaN
     */
    @Test
    public void combineIgnoringNaNBothNaNRanges() {
    	assertNull("Combining a null range and a NaN range should return a null range.", Range.combineIgnoringNaN(NaNRange, NaNRange));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that equals returns false when comparing to a different object type
     */
    @Test
    public void equalsIsFalseWithDifferentObject() {
    	assertFalse("Range is not equal to null", exampleRange.equals(null));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that equals returns false when comparing with a different lower bound
     */
    @Test
    public void equalsIsFalseWithDifferentLowerRange() {
    	Range r2 = new Range(0, 1);
    	assertFalse("Ranges are not equal", exampleRange.equals(r2));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that equals returns false when comparing with a different upper bound
     */
    @Test
    public void equalsIsFalseWithDifferentUpperRange() {
    	Range r2 = new Range(-1, 10);
    	assertFalse("Ranges are not equal", exampleRange.equals(r2));
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that expand works as intended when both upper and lower margin are positive
     */
    @Test
    public void expandWithValidRange() {
    	Range actual = Range.expand(exampleRange, 1, 1);

    	assertEquals("The upper bound should be 3", 3, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -3", -3, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that expand works as intended when the input factors make lower > upper
     */
    @Test
    public void expandWithNewLowerGreaterThanNewUpper() {
    	Range actual = Range.expand(exampleRange, -2, 0);

    	assertEquals("The upper bound should be 2", 2, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be 2", 2, actual.getLowerBound(), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve branch coverage)
     * This test will check that scale returns the correct value when the factor is greater than 0
     */
    @Test
    public void scaleWithValidArgs() {
    	Range actual = Range.scale(exampleRange, 1.5);

    	assertEquals("The upper bound should be 1.5", 1.5, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be -1.5", -1.5, actual.getLowerBound(), .000000001d);
    }
    
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that scale throws the correct exception when the factor is negative
     */
    @Test
    public void scaleWithNegativeFactor() {
    	try {
			Range.scale(exampleRange, -1);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					exception.getClass());
		}
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that constrains returns the exact value when it is contained in the range
     */
    @Test
    public void constrainAlreadyContainsValue() {
    	assertEquals("The range contains value 0", 0, exampleRange.constrain(0), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that the upper bound is returned when the input is above the upper bound of the range
     */
    @Test
    public void constrainValueAboveRange() {
    	assertEquals("The closest value in the range to 6 is 1", 1, exampleRange.constrain(6), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will check that the lower bound is returned when the input is below the lower bound of the range
     */
    @Test
    public void constrainValueBelowRange() {
    	assertEquals("The closest value in the range to -2.3 is -1", -1, exampleRange.constrain(-2.3), .000000001d);
    }
    
    /**
     * NEW FOR ASSIGNMENT 3 (improve statement coverage)
     * This test will ensure hashCode returns the expected value
     */
    @Test
    public void hashCodeReturnsCorrectValue() {
    	assertEquals("The hash code is incorrect", -31457280, exampleRange.hashCode());
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
