// Vegetationconstants.java - (insert one line description here)

package com.veg.stats.main.constants;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Constants
 */
public class VegetationConstants
{
    public static final String VALID_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";

    public final static DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern(VALID_DATE_FORMAT, Locale.ENGLISH);
}
