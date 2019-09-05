package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to check existence of user.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {
    
    
    /** Default version id. */
    private static final long serialVersionUID = 1L;
    
    /**
     * Public Constructor.
     * @param errorMessage - Error Message
     */
    public UserExistsException(String errorMessage) {
        super(errorMessage);
    }

}
