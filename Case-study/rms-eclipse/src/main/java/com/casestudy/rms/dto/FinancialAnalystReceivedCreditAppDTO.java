package com.casestudy.rms.dto;

/**
 * DTO contain Application details with credit score.
 */
public class FinancialAnalystReceivedCreditAppDTO {
	private String applicationNumber;
	private String borrowerName;
	private String borrowerEmail;
	private String companyName;
	private int creditScore;
	
	/**
	 * No args constructor.
	 */
	public FinancialAnalystReceivedCreditAppDTO() {
    super();
  }
	
	/**
	 * Parameterized constructor.
	 * @param applicationNumber - Application number
	 * @param borrowerName - Borrower Name
	 * @param borrowerEmail - Borrower Email
	 * @param companyName - Company Name
	 * @param creditScore - Credit score
	 */
  public FinancialAnalystReceivedCreditAppDTO(String applicationNumber, String borrowerName, String borrowerEmail, String companyName,
      int creditScore) {
    super();
    this.applicationNumber = applicationNumber;
    this.borrowerName = borrowerName;
    this.borrowerEmail = borrowerEmail;
    this.companyName = companyName;
    this.creditScore = creditScore;
  }
  /**
	 * Get Application number.
	 * @return Application number
	 */
	public String getApplicationNumber() {
		return applicationNumber;
	}
	/**
	 * Set Application number.
	 * @param applicationNumber - Application number
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	
	/**
	 * Get Borrower Email.
	 * @return Borrower Email
	 */
	public String getBorrowerEmail() {
		return borrowerEmail;
	}
	
	/**
	 * Set Borrower Email.
	 * @param borrowerEmail - Borrower Email
	 */
	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}
	
	/** Get Borrower Name.
	 * @return Borrower Name
	 */
	public String getBorrowerName() {
		return borrowerName;
	}
	
	/**
	 * Set Borrower Name.
	 * @param borrowerName - Borrower Name
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
	/**
	 * Get Company Name.
	 * @return Company Name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Set Company  Name.
	 * @param companyName - Company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Get Credit Score.
	 * @return Credit Score
	 */
	public int getCreditScore() {
		return creditScore;
	}
	
	/**
	 * Set Credit Score.
	 * @param creditScore - Credit Score
	 */
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	
	
}
