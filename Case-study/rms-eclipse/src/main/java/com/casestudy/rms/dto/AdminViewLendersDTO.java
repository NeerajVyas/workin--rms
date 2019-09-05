package com.casestudy.rms.dto;

/**
 *  DTO which contains the List of active and inactive lenders.  
 */
public class AdminViewLendersDTO {
	
	private String lenderId;
	private String lenderName;
	private String lenderEmail;
	private String loanInterest;
	private String tenureRange;
	private String lenderAmountRange;
	private String numberOfApplicationRequests;
	private String numberOfFinancialAnalyst;
	private String lenderActiveInactivestate;
	
	/**
	 * No-args constructor.
	 */
	public AdminViewLendersDTO() {
    super();
  }
	
	/**
	 * Parameterized constructor.
	 * @param lenderId - Lender ID
	 * @param lenderName - Lender Name
	 * @param lenderEmail - Lender Email
	 * @param loanInterest - Loan Interest
	 * @param tenureRange - Tenure Range
	 * @param lenderAmountRange - Lender Amount Range
	 * @param numberOfApplicationRequests - No. of application requests
	 * @param numberOfFinancialAnalyst - No. of Financial Analyst
	 * @param lenderActiveInactivestate - A/I status
	 */
  public AdminViewLendersDTO(String lenderId, String lenderName, String lenderEmail, String loanInterest, String tenureRange,
      String lenderAmountRange, String numberOfApplicationRequests, String numberOfFinancialAnalyst, String lenderActiveInactivestate) {
    super();
    this.lenderId = lenderId;
    this.lenderName = lenderName;
    this.lenderEmail = lenderEmail;
    this.loanInterest = loanInterest;
    this.tenureRange = tenureRange;
    this.lenderAmountRange = lenderAmountRange;
    this.numberOfApplicationRequests = numberOfApplicationRequests;
    this.numberOfFinancialAnalyst = numberOfFinancialAnalyst;
    this.lenderActiveInactivestate = lenderActiveInactivestate;
  }
  /**
	 * method for getting lender's id.
	 * @return string
	 */
	public String getLenderId() {
		return lenderId;
	}
	/**
	 * method for setting lender' id.
	 * @param lenderId string
	 */
	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}
	/**
	 * method for getting lender's name.
	 * @return string
	 */
	public String getLenderName() {
		return lenderName;
	}
	/**
	 * method for setting lender's name.
   	 * @param lenderName string
	 */
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	/**
	 * method for getting lender's email.
	 * @return string
	 */
	public String getLenderEmail() {
		return lenderEmail;
	}
	/**
	 * method for setting lender's email.
	 * @param lenderEmail string
	 */
	public void setLenderEmail(String lenderEmail) {
		this.lenderEmail = lenderEmail;
	}
	/**
	 * method for getting lender's id.
	 * @return string
	 */
	public String getLoanInterest() {
		return loanInterest;
	}
	/**
	 * method for setting  loan interest by lender.
	 * @param loanInterest string
	 */
	public void setLoanInterest(String loanInterest) {
		this.loanInterest = loanInterest;
	}
	/**
	 * method for getting tenure range.
	 * @return string
	 */
	public String getTenureRange() {
		return tenureRange;
	}
	/**
	 * method for setting tenure range.
	 * @param teunreRange string
	 */
	public void setTenureRange(String teunreRange) {
		this.tenureRange = teunreRange;
	}
	/**
	 * method for getting loan amount range.
	 * @return string
	 */
	public String getLenderAmountRange() {
		return lenderAmountRange;
	}
	/**
	 * method for setting lender's loan amount range.
	 * @param lenderAmountRange string
	 */
	public void setLenderAmountRange(String lenderAmountRange) {
		this.lenderAmountRange = lenderAmountRange;
	}
	/**
	 * method for getting number of application requests.
	 * @return string
	 */
	public String getNumberOfApplicationRequests() {
		return numberOfApplicationRequests;
	}
	/**
	 * method for setting number of application requests received.
	 * @param numberOfApplicationRequests string
	 */
	public void setNumberOfApplicationRequests(String numberOfApplicationRequests) {
		this.numberOfApplicationRequests = numberOfApplicationRequests;
	}
	/**
	 * method for getting number of financial analysts of lender.
	 * @return string
	 */
	public String getNumberOfFinancialAnalyst() {
		return numberOfFinancialAnalyst;
	}
	/**
	 * method for setting number of financial analysts.
	 * @param numberOfFinancialAnalyst string
	 */
	public void setNumberOfFinancialAnalyst(String numberOfFinancialAnalyst) {
		this.numberOfFinancialAnalyst = numberOfFinancialAnalyst;
	}
	/**
	 * method for getting state of lender.
	 * @return string
	 */
	public String getLenderActiveInactivestate() {
		return lenderActiveInactivestate;
	}
	/**
	 * method for setting state of lender.
	 * @param lenderActiveInactivestate string
	 */
	public void setLenderActiveInactivestate(String lenderActiveInactivestate) {
		this.lenderActiveInactivestate = lenderActiveInactivestate;
	}
	
	
}
