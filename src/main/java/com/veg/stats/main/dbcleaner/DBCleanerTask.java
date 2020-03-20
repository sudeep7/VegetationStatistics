package com.veg.stats.main.dbcleaner;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.veg.stats.main.dao.VegetationDao;
import com.veg.stats.main.utils.VegetationsUtils;

/**
 * Timer task that deleted all the older entries
 * 
 * @author Shikha
 */
@Component
public class DBCleanerTask {

	private static final Logger logger = LoggerFactory.getLogger(DBCleanerTask.class);

	@Autowired
	VegetationDao validationDao;

	@Value("${field.condition.numofdays}")
	private int numOfStatsDays;

	@Scheduled(cron = "0 0 0 * * *")
	public void run() {
		logger.info("Executing the task to delete all older entrues");
		Date fromDate = VegetationsUtils.getDate(LocalDate.now().minusDays(numOfStatsDays));
		validationDao.deleteOlderEntries(fromDate);
	}

}
