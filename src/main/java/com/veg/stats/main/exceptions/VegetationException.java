// VegetationException.java - (insert one line description here)

package com.veg.stats.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VegetationException extends Exception
{

    protected String errorMessage;
    protected HttpStatus httpStatusCode;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VegetationException(final String errorMessage, final HttpStatus httpsStatusCode)
    {
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpsStatusCode;
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
