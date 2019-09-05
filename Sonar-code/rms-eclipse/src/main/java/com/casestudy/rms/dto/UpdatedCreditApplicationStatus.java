package com.casestudy.rms.dto;

/**
 * DTO contains Updated status of Credit Application.
 */
public class UpdatedCreditApplicationStatus {
	
	private String applicationStatus;
	private int applicationId;
	private String borrowerEmail;
	private String lenderEmail;
	
	
	
	public UpdatedCreditApplicationStatus() {
		super();
	}
	public UpdatedCreditApplicationStatus(String applicationStatus, int applicationId, String borrowerEmail,
			String lenderEmail) {
		super();
		this.applicationStatus = applicationStatus;
		this.applicationId = applicationId;
		this.borrowerEmail = borrowerEmail;
		this.lenderEmail = lenderEmail;
	}
	/**
	 * gets application status.
	 * @return string
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}
	/**
	 * sets application status.
	 * @param applicationStatus string.
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * gets application id.
	 * @return integer
	 */
	public int getApplicationId() {
		return applicationId;
	}
	/**
	 * sets application id.
	 * @param applicationId integer
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * gets borrower email.
	 * @return string
	 */
	public String getBorrowerEmail() {
		return borrowerEmail;
	}
	/**
	 * sets borrower email.
	 * @param borrowerEmail string 
	 */
 	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}
 	/**
 	 * gets lender email.
 	 * @return string
 	 */
	public String getLenderEmail() {
		return lenderEmail;
	}
	/**
	 * sets lender email.
	 * @param lenderEmail string
	 */
	public void setLenderEmail(String lenderEmail) {
		this.lenderEmail = lenderEmail;
	}
	
	

}
