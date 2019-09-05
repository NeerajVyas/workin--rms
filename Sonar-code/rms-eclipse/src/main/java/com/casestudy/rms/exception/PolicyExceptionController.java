package com.casestudy.rms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/** This exception controller class handles all the exception related to the policy. */
@ControllerAdvice
public class PolicyExceptionController {
    
    /** Handles invalid policy input by user.
     * 
     * @param exception
     *            is the exception caused.
     * @param request
     *            is the web request.
     * @return the proper message along with the status code. */
    @ExceptionHandler(value = PolicyInputInvalidException.class)
    public ResponseEntity<Object> invalidInputException(PolicyInputInvalidException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    
    
    /**
     * Handles Policy already Exists.
     * @param exception
     *            is the exception caused.
     * @param request
     *            is the web request.
     * @return the proper message along with the status code.*/
    @ExceptionHandler(value = PolicyExistsException.class)
    public ResponseEntity<Object> policyExistException(PolicyExistsException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    
    }

}
