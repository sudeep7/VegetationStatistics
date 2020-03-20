// ErrorMessageConstants.java - (insert one line description here)
// 

package com.veg.stats.main.constants;

/**
 * Error Messages
 */
public class ErrorMessageConstants {
	public static final String VEGETATION_FUTURE_DATE = "Vegetation Occurance date is in future. Provide the date before than current date.";

	public static final String VEGETATION_OLD_DATE = "Vegetation Occurance older than 30 days. Provide the date within 30 days.";

	public static final String INCORRECT_VEGETATION_DATE_FORMAT = "Incorrect vegetation date format. Please provide date formate as yyyy-MM-dd'T'HH:mm'Z'";

	public static final String DATE_CONVERSION_EXCEPTION = "Failed to convert String to date";

	public static final String VEGETATION_INVALID_OBJECT = "Invalid Request. Provide valid values for Vegetation and OccurenceAt";

	public static final String VEGETATION_VALUE_LESS_THAN_ZERO = "Vegetation Value should be greater than Zero.";

	public static final String NO_RECORDS_FOUND_FOR_30_DAYS = "No records found for the pas 30 days.";

	public static final String ERROR_RETRIEVING_VEGETATION_RECORDS = "Error in inserting record for Vegetation.";
}
