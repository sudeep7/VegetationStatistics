// FieldStatistics.java - (insert one line description here)

package com.veg.stats.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for Field Statistics
 */
public class FieldStatistics
{

    private float vegetationAvg;
    private float vegetationMinValue;
    private float vegetationMaxValue;

    
    public FieldStatistics()
    {
        super();
        this.vegetationAvg = 0;
        this.vegetationMinValue = 0;
        this.vegetationMaxValue = 0;
    }
    
    
    /**
     * @param vegetationAvg
     * @param vegetationMinValue
     * @param vegetationMaxValue
     */
    public FieldStatistics(float vegetationAvg, float vegetationMinValue, float vegetationMaxValue)
    {
        super();
        this.vegetationAvg = vegetationAvg;
        this.vegetationMinValue = vegetationMinValue;
        this.vegetationMaxValue = vegetationMaxValue;
    }

    /**
     * @return the occeranceDate
     */
    @JsonProperty("avg")
    public final float getVegetationAvg()
    {
        return vegetationAvg;
    }

    /**
     * @param vegetationAvg the occeranceDate to set
     */
    public final void setVegetationAvg(float vegetationAvg)
    {
        this.vegetationAvg = vegetationAvg;
    }

    /**
     * @return the vegetationMinValue
     */
    @JsonProperty("min")
    public final float getVegetationMinValue()
    {
        return vegetationMinValue;
    }

    /**
     * @param vegetationMinValue the vegetationMinValue to set
     */
    public final void setVegetationMinValue(float vegetationMinValue)
    {
        this.vegetationMinValue = vegetationMinValue;
    }

    /**
     * @return the vegetationMaxValue
     */
    @JsonProperty("max")
    public final float getVegetationMaxValue()
    {
        return vegetationMaxValue;
    }

    /**
     * @param vegetationMaxValue the vegetationMaxValue to set
     */
    public final void setVegetationMaxValue(float vegetationMaxValue)
    {
        this.vegetationMaxValue = vegetationMaxValue;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(vegetationAvg);
        result = prime * result + Float.floatToIntBits(vegetationMaxValue);
        result = prime * result + Float.floatToIntBits(vegetationMinValue);
        return result;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldStatistics other = (FieldStatistics) obj;
        if (Float.floatToIntBits(vegetationAvg) != Float.floatToIntBits(other.vegetationAvg))
            return false;
        if (Float.floatToIntBits(vegetationMaxValue) != Float.floatToIntBits(other.vegetationMaxValue))
            return false;
        if (Float.floatToIntBits(vegetationMinValue) != Float.floatToIntBits(other.vegetationMinValue))
            return false;
        return true;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "FieldStatistics [occeranceDate="
                + vegetationAvg
                + ", vegetationMinValue="
                + vegetationMinValue
                + ", vegetationMaxValue="
                + vegetationMaxValue
                + "]";
    }

}
