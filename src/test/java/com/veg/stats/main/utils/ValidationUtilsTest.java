// ValidationUtilsTest.java - (insert one line description here)

package com.veg.stats.main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.veg.stats.main.constants.VegetationConstants;
import com.veg.stats.main.exceptions.VegetationsValidationException;
import com.veg.stats.main.model.FieldCondition;

/**
 * 
 */
public class ValidationUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidateOccuranceDateFormat_ValidDate() {
		FieldCondition fieldCondition = new FieldCondition((float) 1.12, "2019-11-11T11:11Z");
		try {
			ValidationUtils.validateOccuranceDateFormat(fieldCondition);
		} catch (VegetationsValidationException e) {
			Assert.fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateOccuranceDateFormat(com.veg.stats.main.model.FieldCondition)}.
	 * 
	 * @throws VegetationsValidationException
	 */
	@Test(expected = VegetationsValidationException.class)
	public void testValidateOccuranceDateFormat_InvalidValue() throws VegetationsValidationException {
		FieldCondition fieldCondition = new FieldCondition((float) 1.12, "sdfsdf");
		ValidationUtils.validateOccuranceDateFormat(fieldCondition);
		Assert.fail();
	}

	@Test(expected = VegetationsValidationException.class)
	public void testValidateOccuranceDateFormat_InvalidDateFormat() throws VegetationsValidationException {
		FieldCondition fieldCondition = new FieldCondition((float) 1.12, "11-11-2019T11:11Z");
		ValidationUtils.validateOccuranceDateFormat(fieldCondition);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateOccuranceDateFormat(com.veg.stats.main.model.FieldCondition)}.
	 * 
	 * @throws VegetationsValidationException
	 */
	@Test
	public void testvalidateVegetationDate_ValidValue() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(VegetationConstants.VALID_DATE_FORMAT);
		String formatDate = dtf.format(LocalDateTime.now().minusDays(2));

		FieldCondition fieldCondition = new FieldCondition((float) 1.12, formatDate);
		try {
			ValidationUtils.validateVegetationDate(fieldCondition, 30);
		} catch (VegetationsValidationException e) {
			Assert.fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateVegetationOccuranceDate(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test(expected = VegetationsValidationException.class)
	public void testvalidateVegetationDate_OldValue() throws VegetationsValidationException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(VegetationConstants.VALID_DATE_FORMAT);
		String formatDate = dtf.format(LocalDateTime.now().minusDays(30));

		FieldCondition fieldCondition = new FieldCondition((float) 1.12, formatDate);
		ValidationUtils.validateVegetationDate(fieldCondition, 2);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateVegetationOccuranceDate(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test(expected = VegetationsValidationException.class)
	public void testvalidateVegetationDate_FutureValue() throws VegetationsValidationException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(VegetationConstants.VALID_DATE_FORMAT);
		String formatDate = dtf.format(LocalDateTime.now().plusDays(30));

		FieldCondition fieldCondition = new FieldCondition((float) 1.12, formatDate);
		ValidationUtils.validateVegetationDate(fieldCondition, 2);
		Assert.fail();
	}
	
	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateVegetationOccuranceDate(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test
	public void testvalidateVegetation_ValidValue() throws VegetationsValidationException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(VegetationConstants.VALID_DATE_FORMAT);
		String formatDate = dtf.format(LocalDateTime.now().minusDays(2));

		FieldCondition fieldCondition = new FieldCondition((float) 1.12, formatDate);
		try {
			ValidationUtils.validateVegetation(fieldCondition, 30);
		} catch (VegetationsValidationException e) {
			Assert.fail();
		}
	}
	

	
	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateVegetationOccuranceDate(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test(expected = VegetationsValidationException.class)
	public void testvalidateVegetation_NegativeValue() throws VegetationsValidationException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(VegetationConstants.VALID_DATE_FORMAT);
		String formatDate = dtf.format(LocalDateTime.now().minusDays(2));

		FieldCondition fieldCondition = new FieldCondition((float) -1.12, formatDate);
		ValidationUtils.validateVegetation(fieldCondition, 30);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.utils.ValidationUtils#validateVegetation(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test
	public void testValidateVegetation() {
//        fail("Not yet implemented");
	}

}
