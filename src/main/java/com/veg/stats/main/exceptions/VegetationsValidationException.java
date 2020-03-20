// VegetationsValidationException.java - (insert one line description here)

package com.veg.stats.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VegetationsValidationException extends VegetationException
{

    /**
     * throws all validation Exceptions
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public VegetationsValidationException(final String errorMessage, final HttpStatus httpStatusCode)
    {
        super(errorMessage, httpStatusCode);
    }

    /*
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage()
    {
        return errorMessage;
    }

    public HttpStatus getHttpStatusCode()
    {
        return httpStatusCode;
    }
}
