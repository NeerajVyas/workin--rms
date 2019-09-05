package com.casestudy.rms.dto;

/**
 * Contains details for new password.
 */
public class NewPasswordDTO {
	
	private String emailID;
	private String newPassword;
	private String otp;
	
	/**
	 * Gets Email ID.
	 * @return email ID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * Sets email ID.
	 * @param emailID - Email ID
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * Gets new Password.
	 * @return new password
	 */
	public String getNewPassword() {
		return newPassword;
	}
	
	/**
	 * Sets new password.
	 * @param newPassword - new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	/**
	 * Gets OTP.
	 * @return OTP
	 */
	public String getOtp() {
		return otp;
	}
    /**
     * Sets OTP.
     * @param otp - OTP
     */
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	

}
