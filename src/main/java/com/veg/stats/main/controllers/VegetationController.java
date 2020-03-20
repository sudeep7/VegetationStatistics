// VegetationController.java - (insert one line description here)

package com.veg.stats.main.controllers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veg.stats.main.exceptions.VegetationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;
import com.veg.stats.main.services.VegetationService;


/**
 * Controller for all the api's
 * 
 * @author Shikha
 */
@RestController
@RequestMapping("/")
public class VegetationController {

	private static final Logger logger = LoggerFactory.getLogger(VegetationController.class);

	@Resource(name = "vegetationService")
	private VegetationService vegetationService;

	@GetMapping("/all")
	@ResponseBody
	public List<VegetationDto> getAllVegetationStats() throws VegetationException {
		logger.info("Retrieving field Statistics for past 30 Days from : " + new Date(System.currentTimeMillis()));
		return vegetationService.getALLVegetationStats();

	}

	@GetMapping("/field-statistics")
	@ResponseBody
	public FieldStatistics getVegetationStatsForPastThirtyDays() throws VegetationException {
		logger.info("Retrieving field Statistics for past 30 Days from : " + new Date(System.currentTimeMillis()));
		return vegetationService.getVegetationStatsForThirtyDays();

	}

	@PostMapping("/field-conditions")
	@ResponseBody
	public void postVegetation(@RequestBody(required = true) FieldCondition vegetation) throws VegetationException {
		logger.info("posting Field conditions - vegetation = " + vegetation.getVegetation() + " ,occurrenceAt = "
				+ vegetation.getOccurrenceAt());
		vegetationService.saveVegetationFieldConditions(vegetation);
		logger.info("successfully posted Field conditions - vegetation = " + vegetation.getVegetation()
				+ " , occurrenceAt = " + vegetation.getOccurrenceAt());
	}

}
