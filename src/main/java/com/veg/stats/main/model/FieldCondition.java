// Vegetation.java - (insert one line description here)

package com.veg.stats.main.model;

/**
 * This is a Model class for Vegetation Data
 */
public class FieldCondition
{

    //Vaegetation value
    private float vegetation;

    //Vegetation record date
    private String occurrenceAt;

    /**
     * @param vegetation
     * @param occurrenceAt
     */
    public FieldCondition(float vegetation, String occurrenceAt)
    {
        super();
        this.vegetation = vegetation;
        this.occurrenceAt = occurrenceAt;
    }

    /**
     * @return the vegetation
     */
    public float getVegetation()
    {
        return vegetation;
    }

    /**
     * @param vegetation the vegetation to set
     */
    public void setVegetation(float vegetation)
    {
        this.vegetation = vegetation;
    }

    /**
     * @return the occurrenceAt
     */
    public String getOccurrenceAt()
    {
        return occurrenceAt;
    }

    /**
     * @param occurrenceAt the occuranceDate to set
     */
    public void setOccurrenceAt(String occurrenceAt)
    {
        this.occurrenceAt = occurrenceAt;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((occurrenceAt == null) ? 0 : occurrenceAt.hashCode());
        result = prime * result + Float.floatToIntBits(vegetation);
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
        FieldCondition other = (FieldCondition) obj;
        if (occurrenceAt == null)
        {
            if (other.occurrenceAt != null)
                return false;
        }
        else if (!occurrenceAt.equals(other.occurrenceAt))
            return false;
        if (Float.floatToIntBits(vegetation) != Float.floatToIntBits(other.vegetation))
            return false;
        return true;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Vegetation [vegetation=" + vegetation + ", occurrenceAt=" + occurrenceAt + "]";
    }

}
