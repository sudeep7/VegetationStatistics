// VegetationServiceImplTest.java - (insert one line description here)

package com.veg.stats.main.services.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.veg.stats.main.dao.VegetationDao;
import com.veg.stats.main.exceptions.VegetationException;
import com.veg.stats.main.model.FieldCondition;
import com.veg.stats.main.model.FieldStatistics;
import com.veg.stats.main.model.VegetationDto;
import com.veg.stats.main.services.VegetationService;
import com.veg.stats.main.utils.VegetationsUtils;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VegetationServiceImplTest {

	@Autowired
	VegetationService vegetationService;

	@MockBean
	private VegetationDao vegetationDao;

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

	/**
	 * Test method for
	 * {@link com.veg.stats.main.services.impl.VegetationServiceImpl#getVegetationStatsForThirtyDays()}.
	 * 
	 * @throws VegetationException
	 */
	@Test
	public void testGetAll() throws VegetationException {
		when(vegetationDao.findAll()).thenReturn(Stream
				.of(new VegetationDto(new Date(), (float) 1, (float) 10, (float) 25, (long) 3),
						new VegetationDto(new Date(), (float) 11, (float) 100, (float) 150, (long) 3))
				.collect(Collectors.toList()));
		Assert.assertEquals(2, vegetationService.getALLVegetationStats().size());
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.services.impl.VegetationServiceImpl#saveVegetationFieldConditions(com.veg.stats.main.model.FieldCondition)}.
	 */
	@Test
	public void testSave() {
		FieldCondition fieldCondition = new FieldCondition(1.12f, "2019-11-11T11:11Z");
		Optional<VegetationDto> empty = Optional.empty();
		when(vegetationDao.findById(Matchers.any(Date.class))).thenReturn(empty);
	}

	/**
	 * Test method for
	 * {@link com.veg.stats.main.services.impl.VegetationServiceImpl#saveVegetationFieldConditions(com.veg.stats.main.model.FieldCondition)}.
	 * 
	 * @throws VegetationException
	 */
	@Test
	public void testGetStats() throws VegetationException {

		ArrayList<VegetationDto> obj = new ArrayList<VegetationDto>(Arrays.asList(
				new VegetationDto(VegetationsUtils.getDate("2019-11-11T11:11Z"), (float) 1, (float) 10, (float) 25,
						(long) 3),
				new VegetationDto(VegetationsUtils.getDate("2019-11-12T11:11Z"), (float) 11, (float) 100, (float) 150,
						(long) 3),
				new VegetationDto(VegetationsUtils.getDate("2019-11-13T11:11Z"), (float) 12, (float) 50, (float) 75,
						(long) 5)));

		when(vegetationDao.getLast30DaysEntries(Matchers.any(Date.class))).thenReturn(obj);

		FieldStatistics fieldStatistics = vegetationService.getVegetationStatsForThirtyDays();

		Assert.assertEquals(1f, fieldStatistics.getVegetationMinValue(), 0.00f);
		Assert.assertEquals(22.73f, fieldStatistics.getVegetationAvg(), 0.00f);
		Assert.assertEquals(100f, fieldStatistics.getVegetationMaxValue(), 0.00f);
	}
}