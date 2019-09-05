package com.casestudy.rms.dto;

import java.util.List;

/**
 * DTO for financial analyst view details.
 *
 */
public class FinancialAnalystViewDetailsDTO {

	private String applicationNumber;
	private String applicationStatus;
	private String applicationCreditScore;
	private String lenderID;
	private String lenderEmail;
	private String borrowerEmail;
	private List<String> policyId;
	private List<String> policyNamebyLender;
	private List<String> policyValuebyBorrower;
	private List<String> policyWeightagebyLender;
	private List<String> policyThresholdbyLender;
	
	
	

	public FinancialAnalystViewDetailsDTO() {
		super();
	}

	public FinancialAnalystViewDetailsDTO(String applicationNumber, String applicationStatus,
			String applicationCreditScore, String lenderID, String lenderEmail, String borrowerEmail,
			List<String> policyId, List<String> policyNamebyLender, List<String> policyValuebyBorrower,
			List<String> policyWeightagebyLender, List<String> policyThresholdbyLender) {
		super();
		this.applicationNumber = applicationNumber;
		this.applicationStatus = applicationStatus;
		this.applicationCreditScore = applicationCreditScore;
		this.lenderID = lenderID;
		this.lenderEmail = lenderEmail;
		this.borrowerEmail = borrowerEmail;
		this.policyId = policyId;
		this.policyNamebyLender = policyNamebyLender;
		this.policyValuebyBorrower = policyValuebyBorrower;
		this.policyWeightagebyLender = policyWeightagebyLender;
		this.policyThresholdbyLender = policyThresholdbyLender;
	}

	/**
	 * gets application's credit score.
	 * 
	 * @return string
	 */
	public String getApplicationCreditScore() {
		return applicationCreditScore;
	}

	/**
	 * sets application's credit score.
	 * 
	 * @param applicationCreditScore string
	 */
	public void setApplicationCreditScore(String applicationCreditScore) {
		this.applicationCreditScore = applicationCreditScore;
	}

	/**
	 * gets application number.
	 * 
	 * @return string
	 */
	public String getApplicationNumber() {
		return applicationNumber;
	}

	/**
	 * sets application number.
	 * 
	 * @param applicationNumber string
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * gets application status.
	 * 
	 * @return string
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * sets application status.
	 * 
	 * @param applicationStatus string
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * gets lender id.
	 * 
	 * @return string
	 */
	public String getLenderID() {
		return lenderID;
	}

	/**
	 * sets lender id.
	 * 
	 * @param lenderID string
	 */
	public void setLenderID(String lenderID) {
		this.lenderID = lenderID;
	}

	/**
	 * gets lender email.
	 * 
	 * @return string
	 */
	public String getLenderEmail() {
		return lenderEmail;
	}

	/**
	 * sets lender email.
	 * 
	 * @param lenderEmail string
	 */
	public void setLenderEmail(String lenderEmail) {
		this.lenderEmail = lenderEmail;
	}

	/**
	 * gets borrower email.
	 * 
	 * @return string
	 */
	public String getBorrowerEmail() {
		return borrowerEmail;
	}

	/**
	 * sets borrower email.
	 * 
	 * @param borrowerEmail string
	 */
	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}

	/**
	 * gets all policy ids.
	 * 
	 * @return list
	 */
	public List<String> getPolicyId() {
		return policyId;
	}

	/**
	 * sets all policy ids.
	 * 
	 * @param policyId list
	 */
	public void setPolicyId(List<String> policyId) {
		this.policyId = policyId;
	}

	/**
	 * gets policy names.
	 * 
	 * @return list
	 */
	public List<String> getPolicyNamebyLender() {
		return policyNamebyLender;
	}

	/**
	 * sets policy names.
	 * 
	 * @param policyNamebyLender list
	 */
	public void setPolicyNamebyLender(List<String> policyNamebyLender) {
		this.policyNamebyLender = policyNamebyLender;
	}

	/**
	 * gets policy value filled by borrower.
	 * 
	 * @return list
	 */
	public List<String> getPolicyValuebyBorrower() {
		return policyValuebyBorrower;
	}

	/**
	 * sets policy value filled by borrower.
	 * 
	 * @param policyValuebyBorrower list
	 */
	public void setPolicyValuebyBorrower(List<String> policyValuebyBorrower) {
		this.policyValuebyBorrower = policyValuebyBorrower;
	}

	/**
	 * gets policy weightage.
	 * 
	 * @return list
	 */
	public List<String> getPolicyWeightagebyLender() {
		return policyWeightagebyLender;
	}

	/**
	 * sets policy weightage.
	 * 
	 * @param policyWeightagebyLender list
	 */
	public void setPolicyWeightagebyLender(List<String> policyWeightagebyLender) {
		this.policyWeightagebyLender = policyWeightagebyLender;
	}

	/**
	 * gets policy threshold set by lender.
	 * 
	 * @return list
	 */
	public List<String> getPolicyThresholdbyLender() {
		return policyThresholdbyLender;
	}

	/**
	 * sets policy threshold by lender.
	 * 
	 * @param policyThresholdbyLender list
	 */
	public void setPolicyThresholdbyLender(List<String> policyThresholdbyLender) {
		this.policyThresholdbyLender = policyThresholdbyLender;
	}
}