// VegetationExceptionHandler.java - (insert one line description here)

package com.veg.stats.main.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.veg.stats.main.model.ExceptionResponse;

/**
 * 
 */
@ControllerAdvice
@RestController
public class VegetationExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VegetationException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(VegetationException ex, WebRequest request)
    {
        HttpStatus httpsCode = HttpStatus.I_AM_A_TEAPOT;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        if (ex instanceof VegetationsValidationException)
        {
            httpsCode = ex.getHttpStatusCode();
        }

        if (ex instanceof VegetationDaoException)
        {
            httpsCode = ex.getHttpStatusCode();
        }

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, httpsCode);
    }
}
