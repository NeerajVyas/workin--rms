package com.casestudy.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *Represents One Time Password corresponding to Email ID. 
 */
@Entity
@Table(name="OTP")
public class OTP {
     
	@Id
    @Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="OTP")
	private String otp;
	
	@Column(name="VERIFIED")
	private String verified;

	/**
	 * Gets Email ID.
	 * @return Email ID
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets Email ID.
	 * @param email - Email ID
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get OTP.
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

	/**
	 * Gets verification of OTP.
	 * @return VERIFIED/NOT_VERIFIED
	 */
	public String getVerified() {
		return verified;
	}

	/**
	 * Sets verification of OTP.
	 * @param verified - VERIFIED/NOT_VERIFIED
	 */
	public void setVerified(String verified) {
		this.verified = verified;
	}
	
	
	
	
	
}
