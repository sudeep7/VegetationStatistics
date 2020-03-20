// VegetationService.java - (insert one line description here)

package com.veg.stats.main.services.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.veg.stats.main.dao.VegetationDao;
import com.veg.stats.main.exceptions.VegetationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;
import com.veg.stats.main.services.VegetationService;
import com.veg.stats.main.utils.ValidationUtils;
import com.veg.stats.main.utils.VegetationsUtils;

/**
 * Service later.
 * 
 * @author Shikha
 */
@Scope(value = "singleton")
@Component(value = "vegetationService")
public class VegetationServiceImpl implements VegetationService {

	@Resource(name = "vegetationDao")
	private VegetationDao vegetationDao;

	@Value("${field.condition.numofdays}")
	private int numOfStatsDays;

	private static final Logger logger = LoggerFactory.getLogger(VegetationServiceImpl.class);


	/*
	 * Get all the records in the DB
	 */
	@Override
	public List<VegetationDto> getALLVegetationStats() throws VegetationException {
		return (List<VegetationDto>) vegetationDao.findAll();
	}

	/*
	 * @see
	 * com.veg.stats.services.VegetationService#getVegetationStatsForThirtyDays( )
	 */
	@Override
	public FieldStatistics getVegetationStatsForThirtyDays() throws VegetationException {
		return getVegetationStats(numOfStatsDays);
	}

	public FieldStatistics getVegetationStats(final int numOfDays) throws VegetationException {

		logger.debug("VegetationService.getVegetationStatsForThirtyDays()");

		//Get the 30 days back date
		Date fromDate = VegetationsUtils.getDate(LocalDate.now().minusDays(numOfDays));

		//Get all the entries for last 30 days.
		List<VegetationDto> vegetationFieldStats = vegetationDao.getLast30DaysEntries(fromDate);

		//Convert to required response format
		FieldStatistics fieldStats = VegetationsUtils.createVegetationResponse(vegetationFieldStats);

		logger.debug("successfully retrieved vegetation stats for past 30 days from : ", LocalDate.now());
		return fieldStats;

	}

	/*
	 * @see com.veg.stats.main.services.VegetationService#
	 * saveVegetationFieldConditions(com.veg.stats.main.model.Vegetation)
	 */
	@Override
	public void saveVegetationFieldConditions(final FieldCondition fieldCondition) throws VegetationException {
		logger.debug("VegetationService.saveVegetationFieldConditions()");

		ValidationUtils.validateVegetation(fieldCondition, numOfStatsDays);

		//Convert to date
		Date occuredDate = VegetationsUtils.getDate(fieldCondition.getOccurrenceAt());

		//Synchronized by the date. Only entries belonging to same date will be blocked.
		synchronized (fieldCondition.getOccurrenceAt().substring(0, 10).intern()) {

			logger.info("Entered into sync block");
			// Check if the entry is already present. If present recalculate the entity
			// object and save.
			Optional<VegetationDto> dbOptionaValue = vegetationDao.findById(occuredDate);

			VegetationDto vegStats = null;

			if (dbOptionaValue.isPresent()) {
				//If present recalculate and update
				vegStats = VegetationsUtils.calculateVegetationStats(dbOptionaValue.get(), fieldCondition);
			} else {
				// Construct the new Object
				vegStats = VegetationsUtils.getNewVegetationStats(fieldCondition);
			}

			vegetationDao.save(vegStats);
		}

	}

}
