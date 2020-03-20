// ValidationUtils.java - (insert one line description here)

package com.veg.stats.main.utils;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.veg.stats.main.constants.ErrorMessageConstants;
import com.veg.stats.main.constants.VegetationConstants;
import com.veg.stats.main.exceptions.VegetationsValidationException;
import com.veg.stats.main.model.FieldCondition;

/**
 * Validation Utils for Utility methods
 * 
 * @author Shikha
 */
public class ValidationUtils
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

    /**
     * @param vegetation
     */
    public static void validateOccuranceDateFormat(final FieldCondition vegetation)
            throws VegetationsValidationException
    {
        logger.debug("Validating Occurence date format.");
        try
        {
            VegetationsUtils.formatDateToISO_8601(vegetation.getOccurrenceAt());
        }
        catch (Exception e)
        {
            logger.warn("Incorrect vegetation date format. exception : ", e.getMessage());
            VegetationsValidationException ex = new VegetationsValidationException(
                    ErrorMessageConstants.INCORRECT_VEGETATION_DATE_FORMAT,
                    HttpStatus.BAD_REQUEST);
            throw ex;
        }
        logger.debug("Successfully validated Occurence date format.");

    }

    /**
     * @param vegetation
     * @throws VegetationsValidationException
     */
    public static void validateVegetationDate(final FieldCondition vegetation, final int numofStatsDays)
            throws VegetationsValidationException
    {
        logger.debug("validating occurence date.");
        //Validate Not null 
        if (!StringUtils.isEmpty(vegetation.getOccurrenceAt()))
        {

            // validate correct format
            validateOccuranceDateFormat(vegetation);

            //Validate date is not yet Valid
            validateDate(vegetation, numofStatsDays);
        }
        else
        {
            logger.warn(ErrorMessageConstants.VEGETATION_INVALID_OBJECT);
            throw new VegetationsValidationException(ErrorMessageConstants.VEGETATION_INVALID_OBJECT, HttpStatus.BAD_REQUEST);
        }
        logger.debug("Succesfully validated occurence date.");
    }

    /**
     * @param vegetation
     */
    private static void validateDate(final FieldCondition vegetation, final int numofStatsDays)
            throws VegetationsValidationException
    {
        logger.debug("validating occurence date to not be Future date.");
        LocalDateTime occurrenceDate = LocalDateTime.parse(vegetation.getOccurrenceAt(), VegetationConstants.DATE_INPUT_FORMATTER);
        LocalDateTime todaysDate = LocalDateTime.now();
        logger.debug("occurence Date : " + occurrenceDate + " , today's Date:  " + todaysDate);
        if (occurrenceDate.isAfter(todaysDate))
        {
            logger.warn(ErrorMessageConstants.VEGETATION_FUTURE_DATE);
            throw new VegetationsValidationException(ErrorMessageConstants.VEGETATION_FUTURE_DATE, HttpStatus.BAD_REQUEST);
        }
        
        //Check if the date is older than 30 days
        LocalDateTime fromDate = LocalDateTime.now().minusDays(numofStatsDays);
        if (occurrenceDate.isBefore(fromDate))
        {
            logger.warn(ErrorMessageConstants.VEGETATION_OLD_DATE);
            throw new VegetationsValidationException(ErrorMessageConstants.VEGETATION_OLD_DATE, HttpStatus.BAD_REQUEST);
        }
        
        logger.debug("successfully validated occurence date: Not a Future date.");
    }

    /**
     * @param vegetation
     * @throws VegetationsValidationException
     */
    public static void validateVegetation(final FieldCondition vegetation, final int numofStatsDays)
            throws VegetationsValidationException
    {
        logger.debug("Validating vegetation.");
        if (null != vegetation && !StringUtils.isEmpty(vegetation.getOccurrenceAt()))
        {
            validateVegetationValue(vegetation);
            validateVegetationDate(vegetation, numofStatsDays);
        }
        else
        {
            logger.warn(ErrorMessageConstants.VEGETATION_INVALID_OBJECT);
            throw new VegetationsValidationException(ErrorMessageConstants.VEGETATION_INVALID_OBJECT, HttpStatus.BAD_REQUEST);
        }
        logger.debug("Successfully Validated vegetation.");
    }

    /**
     * @param vegetation
     * @throws VegetationsValidationException
     */
    private static void validateVegetationValue(final FieldCondition vegetation)
            throws VegetationsValidationException
    {

        if (vegetation.getVegetation() < 0.0f)
        {
            logger.warn(ErrorMessageConstants.VEGETATION_VALUE_LESS_THAN_ZERO);
            throw new VegetationsValidationException(ErrorMessageConstants.VEGETATION_VALUE_LESS_THAN_ZERO, HttpStatus.BAD_REQUEST);
        }
    }
    
}
