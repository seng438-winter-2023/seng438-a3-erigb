package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest {
	Values2D values;
	KeyedValues keyedValuesList;
	KeyedValues negativeValuesList;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
	}

	/***
	 * This test will test calculateColumnTotal by using a 1 column, 2 row Values2D object.
	 * Expected output is 10
	 */
	@Test
	public void calculateColumnTotalForTwoValues() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("The total for the column containing 2.5 and 7.5 is 10.0", 10.0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using a 1 column, 2 row Values2D object with all valid rows.
	 * Expected output is 10
	 */
	@Test
	public void calculateColumnTotalForTwoValuesWithAllValidRows() {
		int[] validRows = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals("The total for the column containing 2.5 and 7.5 is 10.0", 10.0, result, .000000001d);
	}

	/***
	 * This test will check calculateColumnTotal by using a 1 column, 2 row Values2D object with all valid rows.
	 * This object only contains 0 values.
	 * It is also run through using all valid rows to test the overriding method.
	 * Expected output is 0
	 */
	@Test
	public void calculateColumnTotalReturnsZeroForInvalidValue() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(1, 0);
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	/***
	 * This test will check calculateColumnTotal by using a 1 column, 2 row Values2D object using all valid rows.
	 * This object only contains 0 values.
	 * Expected output is 0
	 */
	@Test
	public void calculateColumnTotalReturnsZeroForInvalidValueWithAllValidRows() {
		int[] validRows = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(1, 0);
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals(0, result, .000000001d);
	}

	/***
	 * This test will test calculateColumnTotal by using an empty Values2D.
	 * Expected output is 0.
	 */
	@Test
	public void calculateColumnTotalReturnsZeroForEmptyValue2D() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using an empty Values2D using only valid rows.
	 * Expected output is 0.
	 */
	@Test
	public void calculateColumnTotalReturnsZeroForEmptyValue2DWithAllValidRows() {
		int[] validRows = {0};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals(0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using a properly made Values2D.
	 * Expected output is 10.
	 */
	@Test
	public void calculateRowTotalForTwoValues() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The total for the row containing 2.5 and 7.5 is 10.0", 10, result, .000000001d);
	}

	/***
	 * This test will test calculateRowTotal by using a properly made Values2D using only valid columns.
	 * Expected output is 10.
	 */
	@Test
	public void calculateRowTotalForTwoValuesWithAllValidColumns() {
		int[] validCols = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		assertEquals("The total for the row containing 2.5 and 7.5 is 10.0", 10, result, .000000001d);
	}

	/***
	 * This test will test calculateRowTotal by using a properly made Values2D with invalid inputs.
	 * Expected output is 0.
	 */
	@Test
	public void calculateRowTotalReturnsZeroForInvalidValue() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(10));
				one(values).getValue(0, 10);
				will(throwException(new IndexOutOfBoundsException("Index 10 is out of bound for Column Count of 1")));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using a properly made Values2D with invalid inputs using all valid columns
	 * Expected output is 0.
	 */
	@Test
	public void calculateRowTotalReturnsZeroForInvalidValueWithAllValidColumns() {
		int[] validCols = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(10));
				one(values).getValue(0, 10);
				will(null);
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	}

	/***
	 * This test will test calculateRowTotal by using an empty Values2D.
	 * Expected output is 0.
	 */
	@Test
	public void calculateRowTotalReturnsZeroForEmptyValue2D() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using an empty Values2D using only valid columns.
	 * Expected output is 0.
	 */
	@Test
	public void calculateRowTotalReturnsZeroForEmptyValue2DWithAllValidColumns() {
		int[] validCols = {0};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using a properly made Values2D with two values using only one valid row.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateColumnTotalForTwoValuesWithOneValidRow() {
		int[] validRows = {0, 5};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals("The total for the column using only valid rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using a properly made Values2D with two values using only one valid row.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateRowTotalForTwoValuesWithOneValidRow() {
		int[] validCols = {0, 5};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		assertEquals("The total for the column using only valid rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using a properly made Values2D with a null value.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateColumnTotalForTwoValuesWithNullValue() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("The total for the column using all rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using a properly made Values2D with a null value using only valid row.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateColumnTotalForTwoValuesWithNullValueWithAllValidRows() {
		int[] validRows = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals("The total for the column using all rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using a properly made Values2D with a null value.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateRowTotalForTwoValuesWithNullValue() {
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The total for the column using only valid rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateRowTotal by using a properly made Values2D with a null value using only valid row.
	 * Expected output is 7.5.
	 */
	@Test
	public void calculateRowTotalForTwoValuesWithNullValueWithAllValidRows() {
		int[] validCols = {0, 1};
		Mockery mockObject = new Mockery();
		values = mockObject.mock(Values2D.class);
		mockObject.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		assertEquals("The total for the column using only valid rows is 7.5", 7.5, result, .000000001d);
	}
	
	/***
	 * This test will test calculateColumnTotal by using a null Values2D.
	 * Expected output is IllegalArgumentException.class.
	 */
	@Test
	public void calculateColumnTotalForNullValues() {
		values = null;
		try {
			DataUtilities.calculateColumnTotal(values, 0);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					exception.getClass());
		}
	}
	
	/***
	 * This test will test calculateRowTotal by using a null Values2D.
	 * Expected output is IllegalArgumentException.class.
	 */
	@Test
	public void calculateRowTotalForNullValues() {
		values = null;
		try {
			DataUtilities.calculateRowTotal(values, 0);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					exception.getClass());
		}
	}

	// the following tests the method getCumulativePercentages in a case where data passed to it had only positive values
	// since the documentation gives the range of percentages from 0.0-1.0
	@Test
	public void getCumulativePercentagesForAllPositiveDataObject() {
		Mockery keyedMock = new Mockery();
		keyedValuesList = keyedMock.mock(KeyedValues.class);
		// the call to cumulative percentages uses the following getters multiple times depending on length on KeyedValues object
		keyedMock.checking(new Expectations() {
			{
				atLeast(1).of(keyedValuesList).getItemCount();
				will(returnValue(4));

				atLeast(1).of(keyedValuesList).getValue(0);
				will(returnValue(5));
				atLeast(1).of(keyedValuesList).getValue(1);
				will(returnValue(9));
				atLeast(1).of(keyedValuesList).getValue(2);
				will(returnValue(2));
				atLeast(1).of(keyedValuesList).getValue(3);
				will(returnValue(4));
				one(keyedValuesList).getKey(0);
				will(returnValue(1));
				one(keyedValuesList).getKey(1);
				will(returnValue(2));
				one(keyedValuesList).getKey(2);
				will(returnValue(15));
				one(keyedValuesList).getKey(3);
				will(returnValue(18));

			}
		});

		KeyedValues resultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		assertEquals("The key in index \"0\" is 1", 1, resultValues.getKey(0));
		assertEquals("The key in index \"1\" is 2", 2, resultValues.getKey(1));
		assertEquals("The key in index \"2\" is 15", 15, resultValues.getKey(2));
		assertEquals("The key in index \"3\" is 18", 18, resultValues.getKey(3));
		assertEquals("The percentage for index \"0\" is 5/20 = 0.25", 0.25, resultValues.getValue(0).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"1\" is (5+9)/20 = 0.7 ", 0.7, resultValues.getValue(1).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"2\" is (5+9+2)/20 = 0.8", 0.8, resultValues.getValue(2).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"3\" is (5+9+2+4)/20 = 1.0", 1.0,
				resultValues.getValue(2).doubleValue(), .000000001d);
	}
	// the following tests the method getCumulativePercentages in a case where data passed to it had only negative values
	// since the documentation gives the range of percentages from 0.0-1.0
	@Test
	public void getCumulativePercentagesForPositiveDataWithANullDataPoint() {
		Mockery keyedMock = new Mockery();
		keyedValuesList = keyedMock.mock(KeyedValues.class);
		// the call to cumulative percentages uses the following getters multiple times depending on length on KeyedValues object
		keyedMock.checking(new Expectations() {
			{
				atLeast(1).of(keyedValuesList).getItemCount();
				will(returnValue(4));

				atLeast(1).of(keyedValuesList).getValue(0);
				will(returnValue(5));
				atLeast(1).of(keyedValuesList).getValue(1);
				will(returnValue(9));
				atLeast(1).of(keyedValuesList).getValue(2);
				will(returnValue(2));
				atLeast(1).of(keyedValuesList).getValue(3);
				will(returnValue(null));
				one(keyedValuesList).getKey(0);
				will(returnValue(1));
				one(keyedValuesList).getKey(1);
				will(returnValue(2));
				one(keyedValuesList).getKey(2);
				will(returnValue(15));
				one(keyedValuesList).getKey(3);
				will(returnValue(18));

			}
		});

		KeyedValues resultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		assertEquals("The key in index \"0\" is 1", 1, resultValues.getKey(0));
		assertEquals("The key in index \"1\" is 2", 2, resultValues.getKey(1));
		assertEquals("The key in index \"2\" is 15", 15, resultValues.getKey(2));
		assertEquals("The key in index \"3\" is null", 18, resultValues.getKey(3));
		assertEquals("The percentage for index \"0\" is 5/16 = 0.3125", 0.3125, resultValues.getValue(0).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"1\" is (5+9)/16 = 0.875 ", 0.875, resultValues.getValue(1).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"2\" is (5+9+2)/16 = 1.0", 1.0, resultValues.getValue(2).doubleValue(),
				.000000001d);
		assertEquals("The percentage for index \"3\" is (5+9+2+0)/16 = 1.0", 1.0,
				resultValues.getValue(2).doubleValue(), .000000001d);
	}

	// the following tests the method getCumulativePercentages in a case where data passed to it had only negative values
	// since the documentation gives the range of percentages from 0.0-1.0
	@Test
	public void getCumulativePercentagesForNegativeDataObject() {

		Mockery negativeKeyedMock = new Mockery();
		keyedValuesList = negativeKeyedMock.mock(KeyedValues.class);
		// the call to cumulative percentages uses the following getters multiple times depending on length on KeyedValues object
		negativeKeyedMock.checking(new Expectations() {
			{
				atLeast(1).of(keyedValuesList).getItemCount();
				will(returnValue(3));

				atLeast(1).of(keyedValuesList).getValue(0);
				will(returnValue(-5));
				atLeast(1).of(keyedValuesList).getValue(1);
				will(returnValue(-9));
				atLeast(1).of(keyedValuesList).getValue(2);
				will(returnValue(-2));
				one(keyedValuesList).getKey(0);
				will(returnValue(1));
				one(keyedValuesList).getKey(1);
				will(returnValue(2));
				one(keyedValuesList).getKey(2);
				will(returnValue(15));

			}
		});

		KeyedValues negativeResultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		assertEquals("The key in index \"0\" is 1", 1, negativeResultValues.getKey(0));
		assertEquals("The key in index \"1\" is 2", 2, negativeResultValues.getKey(1));
		assertEquals("The key in index \"2\" is 15", 15, negativeResultValues.getKey(2));
		assertEquals("The percentage for index \"0\" is |-5/16| = 0.3125", 0.3125,
				negativeResultValues.getValue(0).doubleValue(), .000000001d);
		assertEquals("The percentage for index \"1\" is |-5+-9/16| = 0.875 ", 0.875,
				negativeResultValues.getValue(1).doubleValue(), .000000001d);
		assertEquals("The percentage for index \"2\" is |-5+-9+-2/16| = 1.0", 1.0,
				negativeResultValues.getValue(2).doubleValue(), .000000001d);
	}
	
	// the following tests the method getCumulativePercentages in a case where data passed to it had both positive and negative values
	// since the documentation gives the range of percentages from 0.0-1.0
	@Test
	public void getCumulativePercentagesForMixedDataObject() {

		Mockery negativeKeyedMock = new Mockery();
		keyedValuesList = negativeKeyedMock.mock(KeyedValues.class);
		// the call to cumulative percentages uses the following getters multiple times depending on length on KeyedValues object
		negativeKeyedMock.checking(new Expectations() {
			{
				atLeast(1).of(keyedValuesList).getItemCount();
				will(returnValue(3));

				atLeast(1).of(keyedValuesList).getValue(0);
				will(returnValue(-5));
				atLeast(1).of(keyedValuesList).getValue(1);
				will(returnValue(9));
				atLeast(1).of(keyedValuesList).getValue(2);
				will(returnValue(-2));
				one(keyedValuesList).getKey(0);
				will(returnValue(1));
				one(keyedValuesList).getKey(1);
				will(returnValue(2));
				one(keyedValuesList).getKey(2);
				will(returnValue(15));

			}
		});

		KeyedValues negativeResultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		assertEquals("The key in index \"0\" is 1", 1, negativeResultValues.getKey(0));
		assertEquals("The key in index \"1\" is 2", 2, negativeResultValues.getKey(1));
		assertEquals("The key in index \"2\" is 15", 15, negativeResultValues.getKey(2));
		assertEquals("The percentage for index \"0\" is |5|/16 = 0.3125", 0.3125,
				negativeResultValues.getValue(0).doubleValue(), .000000001d);
		assertEquals("The percentage for index \"1\" is |-5|+9/16 = 0.875 ", 0.875,
				negativeResultValues.getValue(1).doubleValue(), .000000001d);
		assertEquals("The percentage for index \"2\" is |-5|+9|-2|/16 = 1.0", 1.0,
				negativeResultValues.getValue(2).doubleValue(), .000000001d);
	}

	/* This test will test the method createNumberArray where we are passing in Null arguments which is not permitted
	 * An exception should be thrown
	 */
	@Test
	public void createNumberArrayWithNullInput() {
		try {
			DataUtilities.createNumberArray(null);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					exception.getClass());
		}
	}

	/* This test will test the method createNumberArray where we are passing in no arguments (empty)
	 * Expected output should also be empty similar to the input
	 */
	@Test
	public void createNumberArrayThatIsEmpty() {
		Number[] expectedResult = {};
		double[] inputArray = {};
		Number[] actualResult = DataUtilities.createNumberArray(inputArray);
		assertArrayEquals("The result should be", expectedResult, actualResult);
	}

	/* This test will test the method createNumberArray where we are passing in positive arguments
	 * Expected output should be the input array, {1.1, 5.5, 8.6}
	 */
	@Test
	public void createNumberArrayWithPositive() {
		Number[] expectedResult = { 1.1, 5.5, 8.6 };
		double[] inputArray = { 1.1, 5.5, 8.6 };
		Number[] actualResult = DataUtilities.createNumberArray(inputArray);
		assertArrayEquals("The result should be", expectedResult, actualResult);
	}

	/* This test will test the method createNumberArray where we are passing in negative arguments
	 * Expected output should be the input array, {-1.1, -5.5, -8.6}
	 */
	@Test
	public void createNumberArrayWithNegative() {
		Number[] expectedResult = { -1.1, -5.5, -8.6 };
		double[] inputArray = { -1.1, -5.5, -8.6 };
		Number[] actualResult = DataUtilities.createNumberArray(inputArray);
		assertArrayEquals("The array should be", expectedResult, actualResult);
	}

	/* This test will test the method createNumberArray2D where we are passing in Null arguments which is not permitted
	 * An exception should be thrown
	 */
	@Test
	public void createNumberArray2DWithNullInput() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail("An exception should be thrown!");
		} catch (Exception exception) {
			assertEquals("The exception thrown type is InvalidParameterException", InvalidParameterException.class,
					exception.getClass());
		}
	}

	/* This test will test the method createNumberArray2D where we are passing in no arguments (empty)
	 * Expected output should also be empty similar to the input
	 */
	@Test
	public void createNumberArray2DThatIsEmpty() {
		Number[][] expectedResult = { {}, {} };
		double[][] inputArray = { {}, {} };
		Number[][] actualResult = DataUtilities.createNumberArray2D(inputArray);
		assertArrayEquals("The array should be", expectedResult, actualResult);
	}

	/* This test will test the method createNumberArray2D where we are passing in positive values for the second argument and the first argument is empty
	 * Expected output should be the input array, {{}, {1.1, 5.5}}
	 */
	@Test
	public void createNumberArray2DThatFirstArrayIsEmpty() {
		Number[][] expectedResult = { {}, { 1.1, 5.5 } };
		double[][] inputArray = { {}, { 1.1, 5.5 } };
		Number[][] actualResult = DataUtilities.createNumberArray2D(inputArray);
		assertArrayEquals("The array should be", expectedResult, actualResult);
	}

	/* This test will test the method createNumberArray2D where we are passing in positive values for the first argument and the second argument is empty
	 * Expected output should be the input array, {{1.1, 5.5}, {}}
	 */
	@Test
	public void createNumberArray2DThatSecondArrayIsEmpty() {
		Number[][] expectedResult = { { 1.1, 5.5 }, {} };
		double[][] inputArray = { { 1.1, 5.5 }, {} };
		Number[][] actualResult = DataUtilities.createNumberArray2D(inputArray);
		assertArrayEquals("The array should be", expectedResult, actualResult);
	}

	@Test
	public void testForEmptyInput() {
		double[][] expectedResult =  {};
		double[][] input = {};
		double[][] actualResult = DataUtilities.clone(input);
		assertArrayEquals("The output should be", expectedResult, actualResult);
	}

	@Test
	public void testForASingleRowWithSingleValue() {
		double[][] expectedResult =  { {1.0} };
		double[][] input = { {1.0} };
		double[][] actualResult = DataUtilities.clone(input);
		assertArrayEquals("The output should be", expectedResult, actualResult);
	}

	@Test
	public void testForASingleRowWithMultipleValue() {
		double[][] expectedResult =  { {1.0, 2.1, 3.2} };
		double[][] input = { {1.0, 2.1, 3.2} };
		double[][] actualResult = DataUtilities.clone(input);
		assertArrayEquals("The output should be", expectedResult, actualResult);
	}

	@Test
	public void testForMultipleRowWithMultipleValue() {
		double[][] expectedResult =  { {1.0, 2.1, 3.2}, {12.0, 22.1, 33.2}, {1.5, 2.5, 3.5} };
		double[][] input = { {1.0, 2.1, 3.2}, {12.0, 22.1, 33.2}, {1.5, 2.5, 3.5} };
		double[][] actualResult = DataUtilities.clone(input);
		assertArrayEquals("The output should be", expectedResult, actualResult);
	}

	@Test
	public void testForFirstArrayIsNull() {
		double[][] a =  null;
		double[][] b = {{6, 2, 5}, {2.1, 1.2, 3.3}};
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}

	@Test
	public void testForSecondArrayIsNull() {
		double[][] a =  {{6, 2, 5}, {2.1, 1.2, 3.3}};
		double[][] b = null;
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}

	@Test
	public void testForBothArraysNull() {
		double[][] a =  null;
		double[][] b = null;
		boolean result = DataUtilities.equal(a, b);
		assertTrue(result);
	}

	@Test
	public void testForTwoEqualArray() {
		double[][] a =  {{1, 2, 3}, {1.1, 2.2, 3.3}};
		double[][] b = {{1, 2, 3}, {1.1, 2.2, 3.3}};
		boolean result = DataUtilities.equal(a, b);
		assertTrue(result);
	}

	@Test
	public void testForTwoDifferentArray() {
		double[][] a =  {{1, 2, 3}, {1.1, 2.2, 3.3}};
		double[][] b = {{6, 2, 5}, {2.1, 1.2, 3.3}};
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}

	@Test
	public void testForArrayFirstRowIsSame() {
		double[][] a =  {{1, 2, 3}, {1.1, 2.2, 3.3}};
		double[][] b = {{1, 2, 3}, {2.1, 1.2, 3.3}};
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}

	@Test
	public void testForArraySecondRowIsSame() {
		double[][] a =  {{1, 2, 3}, {1.1, 2.2, 3.3}};
		double[][] b = {{6, 2, 5}, {2.1, 1.2, 3.3}};
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}

	@Test
	public void testForDifferntArrayLength() {
		double[][] a =  {{1, 2, 3}, {1.1, 2.2, 3.3}};
		double[][] b = {{6, 2, 5}};
		boolean result = DataUtilities.equal(a, b);
		assertFalse(result);
	}
}
