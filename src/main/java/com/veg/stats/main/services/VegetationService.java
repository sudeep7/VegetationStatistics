// VegetationService.java - (insert one line description here)

package com.veg.stats.main.services;

import java.util.List;

import com.veg.stats.main.exceptions.VegetationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;

/**
 * 
 */
public interface VegetationService
{

    /**
     * @return
     * @throws VegetationException
     */
    FieldStatistics getVegetationStatsForThirtyDays()
            throws VegetationException;

    /**
     * @param vegetation
     * @throws VegetationException
     */
    void saveVegetationFieldConditions(final FieldCondition vegetation)
            throws VegetationException;

	List<VegetationDto> getALLVegetationStats() throws VegetationException;

}
