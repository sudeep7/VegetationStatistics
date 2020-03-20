// VegetationsUtils.java - (insert one line description here)

package com.veg.stats.main.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.veg.stats.main.constants.ErrorMessageConstants;
import com.veg.stats.main.constants.VegetationConstants;
import com.veg.stats.main.exceptions.VegetationsValidationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;

/**
 * Util functions
 * 
 * @author Shikha
 */
public class VegetationsUtils {

	private static final Logger logger = LoggerFactory.getLogger(VegetationsUtils.class);

	/**
	 * @param date
	 * @return
	 */
	public static Date getDate(final LocalDate date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
	}

	/**
	 * @param occurrenceAt
	 * @return
	 */
	public static String formatDateToISO_8601(final String occurrenceAt) throws Exception {

		LocalDateTime date = LocalDateTime.parse(occurrenceAt, VegetationConstants.DATE_INPUT_FORMATTER);
		String formatDate = VegetationConstants.DATE_INPUT_FORMATTER.format(date);
		logger.debug("formatted date : ", formatDate);
		return formatDate;
	}

	/**
	 * Create Vegetation response From the List of Dao objects
	 * 
	 * @param vegetationFieldStats
	 * @return
	 */
	public static FieldStatistics createVegetationResponse(final List<VegetationDto> vegetationFieldStats) {
		FieldStatistics fieldStatistics = null;
		if (!vegetationFieldStats.isEmpty()) {
			Double totalVegetation = vegetationFieldStats.stream()
					.collect(Collectors.summingDouble(VegetationDto::getTotalVegetation));

			Long totalVegetationCount = vegetationFieldStats.stream()
					.collect(Collectors.summingLong(VegetationDto::getTotalVegetationCount));

			float minimumVegetation = vegetationFieldStats.stream()
					.collect(Collectors.minBy(Comparator.comparingDouble(VegetationDto::getVegetationMinValue))).get()
					.getVegetationMinValue();

			float maximumVegetation = vegetationFieldStats.stream()
					.collect(Collectors.maxBy(Comparator.comparingDouble(VegetationDto::getVegetationMaxValue))).get()
					.getVegetationMaxValue();

			Float vegetationAvg = (float) (totalVegetation / totalVegetationCount);

			fieldStatistics = new FieldStatistics(roundOff(vegetationAvg, 2), roundOff(minimumVegetation, 2),
					roundOff(maximumVegetation, 2));
		} else
		{
			return new FieldStatistics();
		}

		return fieldStatistics;
	}

	/**
	 * Round to certain number of decimals
	 * 
	 * @param d
	 * @param decimalPlace
	 * @return
	 */
	public static float roundOff(final float value, final int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(value));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public static VegetationDto getNewVegetationStats(final FieldCondition fieldStatistics)
			throws VegetationsValidationException {

		return new VegetationDto(VegetationsUtils.getDate(fieldStatistics.getOccurrenceAt()),
				fieldStatistics.getVegetation(), fieldStatistics.getVegetation(), fieldStatistics.getVegetation(), 1);

	}

	/**
	 * @param vegetation
	 * @throws VegetationsValidationException
	 */
	public static VegetationDto calculateVegetationStats(final VegetationDto vegetationStats, final FieldCondition vegetation)
			throws VegetationsValidationException {

		if (vegetationStats.getVegetationMinValue() > vegetation.getVegetation())
			vegetationStats.setVegetationMinValue(vegetation.getVegetation());

		if (vegetationStats.getVegetationMaxValue() < vegetation.getVegetation())
			vegetationStats.setVegetationMaxValue(vegetation.getVegetation());

		vegetationStats.setTotalVegetation((long) (vegetationStats.getTotalVegetation() + vegetation.getVegetation()));

		vegetationStats.setTotalVegetationCount(vegetationStats.getTotalVegetationCount() + 1);

		return vegetationStats;
	}

	public static Date getDate(final String strDate) throws VegetationsValidationException {
		try {
			return new SimpleDateFormat(VegetationConstants.VALID_DATE_FORMAT).parse(strDate);
		} catch (ParseException e) {
			VegetationsValidationException ex = new VegetationsValidationException(
					ErrorMessageConstants.DATE_CONVERSION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
			throw ex;
		}
	}

}
