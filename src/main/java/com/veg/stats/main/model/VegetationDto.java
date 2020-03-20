// VegetationStats.java - (insert one line description here)

package com.veg.stats.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

/**
 * This is Model for consolidated Vegetation statistics
 */
@Entity
public class VegetationDto {

	@Id
	@Temporal(TemporalType.DATE)
	private Date occurenceDate;

	@Column
	private float vegetationMinValue;

	@Column
	private float vegetationMaxValue;

	@Column
	private float totalVegetation;

	@Column
	private long totalVegetationCount;

	public VegetationDto() {

	}

	public VegetationDto(Date occurenceDate, float vegetationMinValue, float vegetationMaxValue, float totalVegetation,
			long totalVegetationCount) {
		super();
		this.occurenceDate = occurenceDate;
		this.vegetationMinValue = vegetationMinValue;
		this.vegetationMaxValue = vegetationMaxValue;
		this.totalVegetation = totalVegetation;
		this.totalVegetationCount = totalVegetationCount;
	}

	public Date getOccurenceDate() {
		return occurenceDate;
	}

	public void setOccurenceDate(Date occurenceDate) {
		this.occurenceDate = occurenceDate;
	}

	public float getVegetationMinValue() {
		return vegetationMinValue;
	}

	public void setVegetationMinValue(float vegetationMinValue) {
		this.vegetationMinValue = vegetationMinValue;
	}

	public float getVegetationMaxValue() {
		return vegetationMaxValue;
	}

	public void setVegetationMaxValue(float vegetationMaxValue) {
		this.vegetationMaxValue = vegetationMaxValue;
	}

	public float getTotalVegetation() {
		return totalVegetation;
	}

	public void setTotalVegetation(float totalVegetation) {
		this.totalVegetation = totalVegetation;
	}

	public long getTotalVegetationCount() {
		return totalVegetationCount;
	}

	public void setTotalVegetationCount(long totalVegetationCount) {
		this.totalVegetationCount = totalVegetationCount;
	}

	@Override
	public String toString() {
		return "VegetationStats [occurenceDate=" + occurenceDate + ", vegetationMinValue=" + vegetationMinValue
				+ ", vegetationMaxValue=" + vegetationMaxValue + ", totalVegetation=" + totalVegetation
				+ ", totalVegetationCount=" + totalVegetationCount + "]";
	}

}
