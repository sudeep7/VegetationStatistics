// VegetationDao.java - (insert one line description here)

package com.veg.stats.main.dao;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.veg.stats.main.model.VegetationDto;

/**
 * Dao layer
 * 
 * @author Shikha
 */
@Transactional
public interface VegetationDao extends CrudRepository<VegetationDto, Date> {

	@Query("select a from VegetationDto a where a.occurenceDate >= :startDateTime")
	List<VegetationDto> getLast30DaysEntries(@Param("startDateTime") Date startDateTime);
	
	@Modifying
	@Transactional
	@Query("delete from VegetationDto a where a.occurenceDate <= :fromDate")
	void deleteOlderEntries(@Param("fromDate") Date fromDate);
}
