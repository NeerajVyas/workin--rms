package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to check existence of policy.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PolicyExistsException extends RuntimeException {
    
    /** Default version id. */
    private static final long serialVersionUID = 1L;
    
    /**
     * Public Constructor.
     * @param errorMessage - Error Message
     */
    public PolicyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
