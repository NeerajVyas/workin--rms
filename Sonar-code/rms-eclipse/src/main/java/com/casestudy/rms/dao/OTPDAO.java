package com.casestudy.rms.dao;

import com.casestudy.rms.model.OTP;

/**
 * Declares method for OTP DAO.
 */
public interface OTPDAO {
	/**
	 * Saves OTP in database corresponding to emailID.
	 * @param otp - OTP
	 */
	void save(OTP otp);

	/**
	 * Checks OTP corresponding to email ID.
	 * @param emailID - EmailID
	 * @param otp - OTP
	 * @return boolean
	 */
	boolean checkOTP(String emailID,String otp);
	
	/**
	 * Checks emailID applied for forgot Password or not.
	 * @param emailID - email ID
	 * @param verified - verified
	 * @return boolean
	 */
    boolean presentOTP(String emailID,String verified);
    
    /**
     * Delete OTP corresponding to email ID.
     * @param emailID - email ID
     */
    void deleteOTP(String emailID);
    
    /**
     * Update OTP status corresponding to email ID.
     * @param emailID - email ID
     * @param verified - Verified
     */
    void updateOTP(String emailID,String verified);
}
