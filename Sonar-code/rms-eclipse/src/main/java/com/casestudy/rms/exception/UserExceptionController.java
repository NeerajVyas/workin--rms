package com.casestudy.rms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** This exception controller class handles all the exception related to the user. */
@ControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler {
    /** Handles invalid input by user.
     * 
     * @param exception
     *            is the exception caused.
     * @param request
     *            is the web request.
     * @return the proper message along with the status code. */
    @ExceptionHandler(value = UserInputInvalidException.class)
    public ResponseEntity<Object> invalidInputException(UserInputInvalidException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

    }
    
    /**
     * Handles if existence of user.
     * @param exception - exception caused
     * @param request - web request
     * @return proper message along with the status code
     */
    @ExceptionHandler(value=UserExistsException.class)
    public ResponseEntity<Object> userExistsException(UserExistsException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);

    }
    

}