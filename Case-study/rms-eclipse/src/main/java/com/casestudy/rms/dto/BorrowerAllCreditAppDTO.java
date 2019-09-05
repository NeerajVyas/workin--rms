package com.casestudy.rms.dto;
/**
 * class for viewing all previous credit applications filled by borrower.
 * @author impetus
 *
 */
public class BorrowerAllCreditAppDTO {
	
	private String applicationId;
	private String	creditScore;
	private String	companyName;
	private String	creationDate;
	private String	applicationStatus;
	private String	lenderName;
	
	/**
	 * No args constructor.
	 */
	public BorrowerAllCreditAppDTO() {
    super();
  }
	
	/**
	 * Parameterized Constructor.
	 * @param applicationId - Application ID
	 * @param creditScore - Credit Score
	 * @param companyName - Company Name
	 * @param creationDate - Creation Date
	 * @param applicationStatus - Application Status
	 * @param lenderName - Lender Name
	 */
  public BorrowerAllCreditAppDTO(String applicationId, String creditScore, String companyName, String creationDate, String applicationStatus,
      String lenderName) {
    super();
    this.applicationId = applicationId;
    this.creditScore = creditScore;
    this.companyName = companyName;
    this.creationDate = creationDate;
    this.applicationStatus = applicationStatus;
    this.lenderName = lenderName;
  }
  /**
	 * Gets Application ID.
	 * @return Application ID
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * method for setting application id.
	 * @param applicationId string
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	/**
	 * Gets credit score.
	 * @return credit score
	 */
	public String getCreditScore() {
		return creditScore;
	}
	/**
	 * method for setting credit score of application request.
	 * @param creditScore string
	 */
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	
	/**
	 * Gets company name.
	 * @return company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * method for setting company's name of borrower.
	 * @param companyName string
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Gets creation date.
	 * @return creation date
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * method for setting creation date.
	 * @param creationDate string
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Gets application status.
	 * @return application status
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}
	/**
	 * method for setting application status of application request.
	 * @param applicationStatus string
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * Gets lender name.
	 * @return Lender name
	 */
	public String getLenderName() {
		return lenderName;
	}
	/**
	 * method for setting lender.
	 * @param lenderName - lender name
	 */
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	@Override
	public String toString() {
		return "BorrowerAllCreditAppDTO [applicationId=" + applicationId + ", creditScore=" + creditScore
				+ ", companyName=" + companyName + ", creationDate=" + creationDate + ", applicationStatus="
				+ applicationStatus + ", lenderName=" + lenderName + "]";
	}
}
