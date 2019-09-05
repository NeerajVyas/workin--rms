package com.casestudy.rms.dto;
import java.time.LocalDateTime;

/**
 * Represents a response.
 */
public class ResponseModel {
    
    private LocalDateTime timestamp;
    
    private String message;
    
    private int status;
    /**
     * Gets a message.
     * @return message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets a message.
     * @param message - message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * gets status.
     * @return integer
     */
    
    public int getStatus() {
		return status;
	}
/**
 * sets status.
 * @param status integer
 */
	public void setStatus(int status) {
		this.status = status;
	}
/**
 * gets time stamp.
 * @return local date time
 */
	
    public LocalDateTime getTimestamp() {
		return timestamp;
	}
/**
 * sets time stamp.
 * @param timestamp local date time
 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
    public String toString() {
        return "ResponseDTO [timestamp=" + timestamp + ", message=" + message + ", status=" + status + "]";
    }
    
    
    
    
}