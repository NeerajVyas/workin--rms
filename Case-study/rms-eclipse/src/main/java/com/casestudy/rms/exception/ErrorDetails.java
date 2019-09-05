package com.casestudy.rms.exception;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/** Error Message Format Class. */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorDetails {

    
    private Date timestamp;
    
    private String message; 
    
    private String details;
    
    private int status;

    /** Constructor.
     * 
     * @param timestamp
     *            current time.
     * @param message
     *            is the message/status to be displayed.
     * @param details
     *            details.
     * @param status
     *            status            */
    public ErrorDetails(Date timestamp, String message, String details, int status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    /**
     * Gets a time stamp.
     * @return time stamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets a time stamp.
     * @param timestamp - time stamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets a error message.
     * @return error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a error message.
     * @param message - error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
   
    /**
     * Gets the detail of exception.
     * @return detail of exception
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the detail of exception.
     * @param details - detail of exception
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets a status.
     * @return Status  
     */
    public int getStatus() {
        return status;
    }

    /**
     * sets a status.
     * @param status - Status
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    
   
}
