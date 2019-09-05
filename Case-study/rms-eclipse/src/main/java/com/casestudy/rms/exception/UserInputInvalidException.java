package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception class for wrong user input. */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserInputInvalidException extends RuntimeException {

    /** Default version id. */
    private static final long serialVersionUID = 1L;

    /** Public constructor.
     * 
     * @param errorMessage
     *            - Message to be displayed. */
    public UserInputInvalidException(String errorMessage) {
        super(errorMessage);
    }

}
