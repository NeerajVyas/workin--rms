package com.casestudy.rms.dto;

import java.util.List;

/**
 * Provides details of credit application filled by borrower.
 *
 */
public class CreditApplicationDetails {
	
	private String applicationId;
	private String financialAnalyst;
	private String borrowerName;
	private String borrowerEmail;
	private String applicationStatus;
	private String creditScore;
	private String date;
	private String companyName;
	private List<PolicyValues> policyValues;
	
	
	
	public CreditApplicationDetails() {
		super();
	}

	public CreditApplicationDetails(String applicationId, String financialAnalyst, String borrowerName,
			String borrowerEmail, String applicationStatus, String creditScore, String date, String companyName,
			List<PolicyValues> policyValues) {
		super();
		this.applicationId = applicationId;
		this.financialAnalyst = financialAnalyst;
		this.borrowerName = borrowerName;
		this.borrowerEmail = borrowerEmail;
		this.applicationStatus = applicationStatus;
		this.creditScore = creditScore;
		this.date = date;
		this.companyName = companyName;
		this.policyValues = policyValues;
	}

	/**
	 * Gets Application Id.
	 * @return application ID
	 */
	public String getApplicationId() {
		return applicationId;
	}
	
	/**
	 * Sets Application ID.
	 * @param applicationId - application ID
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	/**
	 * Gets Financial Analyst Name.
	 * @return Financial Analyst name
	 */
	public String getFinancialAnalyst() {
		return financialAnalyst;
	}
	
	/**
	 * Sets Financial Analyst Name.
	 * @param financialAnalyst - Financial Analyst Name
	 */
	public void setFinancialAnalyst(String financialAnalyst) {
		this.financialAnalyst = financialAnalyst;
	}
	
	/**
	 * Gets Borrower Name.
	 * @return Borrower Name
	 */
	public String getBorrowerName() {
		return borrowerName;
	}
	
	/**
	 * Sets Borrower Name.
	 * @param borrowerName - Borrower Name
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
	/**
	 * Gets Borrower Email.
	 * @return Borrower Email
	 */
	public String getBorrowerEmail() {
		return borrowerEmail;
	}
	
	/**
	 * Sets Borrower Email.
	 * @param borrowerEmail - Borrower Email
	 */
	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}
	
	/**
	 * Gets Application Status.
	 * @return Application Status
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}
	
	/**
	 * Sets Application Status.
	 * @param applicationStatus - Application Status
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	/**
	 * Gets Credit Score.
	 * @return credit score
	 */
	public String getCreditScore() {
		return creditScore;
	}
	
	/**
	 * Sets Credit Score.
	 * @param creditScore - Credit Score
	 */
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	
	/**
	 * Gets List of policy values.
	 * @return List of Policy values
	 */
	public List<PolicyValues> getPolicyValues() {
		return policyValues;
	}
	
	/**
	 * Sets List of Policy Values.
	 * @param policyValues - Policy Values
	 */
	public void setPolicyValues(List<PolicyValues> policyValues) {
		this.policyValues = policyValues;
	}
	
	/**
	 * Gets Modification date.
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets Modification Date.
	 * @param date - date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets Company Name.
	 * @return company Name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets company Name.
	 * @param companyName - company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	

}
