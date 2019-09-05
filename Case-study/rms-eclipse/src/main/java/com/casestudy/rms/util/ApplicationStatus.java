package com.casestudy.rms.util;

/**
 * Helps to set the application Status.
 *
 */
public class ApplicationStatus {
	
	/**
	 * Return Application Status according to status value.
	 * @param status - status
	 * @return Application Status
	 */
	public static String getApplicationStatus(int status){
		
		if (status == ApplicationConstant.STATUS_HOLD) {
			return "ON HOLD";
		} else if (status == ApplicationConstant.STATUS_ASSIGNED) {
			return "ASSIGNED";
		} else if (status == ApplicationConstant.STATUS_REJECT) {
			return "REJECTED";
		} else {
			return "APPROVED";
		} 
	}
}

