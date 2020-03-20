// VegetationDaoException.java - (insert one line description here)

package com.veg.stats.main.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 */
public class VegetationDaoException extends VegetationException
{

    /**
     * Exception handling for persistence layer
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param errorMessage
     * @param httpsStatusCode
     */
    public VegetationDaoException(final String errorMessage, final HttpStatus httpsStatusCode)
    {
        super(errorMessage, httpsStatusCode);
    }

}
