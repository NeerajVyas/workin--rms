package com.casestudy.rms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Exception Controller for DAO layer exception.
 *
 */
@ControllerAdvice
public class DAOExceptionController {

	/**
	 * Represents DAO Exception.
	 * @param exception - DAO Exception
	 * @param request - web request
	 * @return Error Detail object
	 */
	@ExceptionHandler(value = DAOException.class)
    public ResponseEntity<Object> daoException(DAOException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
