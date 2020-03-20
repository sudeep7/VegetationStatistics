// VegetationsUtilsTest.java - (insert one line description here)


package com.veg.stats.main.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.veg.stats.main.exceptions.VegetationsValidationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;

/**
 * 
 */
public class VegetationsUtilsTest
{

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp()
            throws Exception
    {
    }

    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#getDate(java.time.LocalDate)}.
     */
    @Test
    public void testGetDate()
    {
//        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#formatDateToISO_8601(java.lang.String)}.
     * @throws Exception 
     */
    @Test(expected = Exception.class)
    public void testFormatDateToISO_8601_InvalidValue() throws Exception
    {
			VegetationsUtils.formatDateToISO_8601("2019-11-1111:11Z");
			Assert.fail();
    }
    
    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#formatDateToISO_8601(java.lang.String)}.
     * @throws Exception 
     */
    @Test(expected = Exception.class)
    public void testFormatDateToISO_8601_ValidValue() throws Exception
    {
			VegetationsUtils.formatDateToISO_8601("asdasd");
			Assert.fail();
    }

    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     */
    @Test
    public void testCreateVegetationResponse_Valid()
    {
    	ArrayList<VegetationDto> obj = new ArrayList<VegetationDto>(
    		      Arrays.asList(
    		    		  new VegetationDto(new Date(), (float)1, (float)10, (float)25, (long)3), 
    		    		  new VegetationDto(new Date(), (float)11, (float)100, (float)150, (long)3), 
    		    		  new VegetationDto(new Date(), (float)12, (float)50, (float)75, (long)5)));
    	
    	FieldStatistics fieldStats = VegetationsUtils.createVegetationResponse(obj);
    	Assert.assertEquals(1.0f, fieldStats.getVegetationMinValue(), 0.00f);
    	Assert.assertEquals(100.0f, fieldStats.getVegetationMaxValue(), 0.00f);
    	Assert.assertEquals(22.73f, fieldStats.getVegetationAvg(), 0.00f);
    }
    
    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     */
    @Test
    public void testCreateVegetationResponse_Empty()
    {
    	ArrayList<VegetationDto> obj = new ArrayList<VegetationDto>();
    	
    	FieldStatistics fieldStats = VegetationsUtils.createVegetationResponse(obj);
    	Assert.assertEquals(0.0f, fieldStats.getVegetationMinValue(), 0.00f);
    	Assert.assertEquals(0.0f, fieldStats.getVegetationMaxValue(), 0.00f);
    	Assert.assertEquals(0.0f, fieldStats.getVegetationAvg(), 0.00f);
    }
    
    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     */
    @Test
    public void testRoundOff_2()
    {
    	float newValue = VegetationsUtils.roundOff(1.23456f, 2);
    	Assert.assertEquals(1.23f, newValue, 0.00f);
    }

    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     */
    @Test
    public void testRoundOff_4()
    {
    	float newValue = VegetationsUtils.roundOff(1.23456f, 4);
    	Assert.assertEquals(1.2346f, newValue, 0.00f);
    }
    


    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     * @throws VegetationsValidationException 
     */
    @Test
    public void testCalculateVegDtoStats() throws VegetationsValidationException
    {
    	VegetationDto vegetationDto	= new VegetationDto(new Date(), (float)1, (float)10, (float)25, (long)3);
    	FieldCondition fieldCondition = new FieldCondition(10f, "2019-11-11T11:11Z");
    	VegetationDto finalValue = VegetationsUtils.calculateVegetationStats(vegetationDto, fieldCondition);
    	
    	Assert.assertEquals(35.0f, finalValue.getTotalVegetation(), 0.00f);
    	Assert.assertEquals(4, finalValue.getTotalVegetationCount());
    	Assert.assertEquals(10.0f, finalValue.getVegetationMaxValue(), 0.00f);
    	Assert.assertEquals(1.0f, finalValue.getVegetationMinValue(), 0.00f);
    }
    

    


    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     * @throws VegetationsValidationException 
     */
    @Test(expected = VegetationsValidationException.class)
    public void getDate_InValidValue() throws VegetationsValidationException
    {
    	VegetationsUtils.getDate("2019-11-1111:11Z");
    	Assert.fail();
    }

    /**
     * Test method for {@link com.veg.stats.main.utils.VegetationsUtils#createVegetationResponse(java.util.List)}.
     * @throws VegetationsValidationException 
     */
    @Test
    public void getDate_ValidValue()
    {
    	try {
			VegetationsUtils.getDate("2019-11-11T11:11Z");
		} catch (VegetationsValidationException e) {
			Assert.fail();
		}
    	
    }

}
