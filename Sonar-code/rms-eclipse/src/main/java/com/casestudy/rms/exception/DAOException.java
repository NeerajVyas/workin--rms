package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents DAO Exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DAOException extends RuntimeException{
	
	/** Default version id. */
    private static final long serialVersionUID = 1L;
    
    /**
     * Public Constructor.
     * @param errorMessage - Error Message
     */
    public DAOException(String errorMessage) {
        super(errorMessage);
    }

}
