package com.casestudy.rms.dto;

/**
 * Class contain lender details.
 */
public class BorrowerViewLenderDTO {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String lenderDescription;
	private String tenureRange;
	private String loanAmountRange;
	private int loanInterest;
	
	/**
	 * No args constructor.
	 */
	public BorrowerViewLenderDTO() {
    super();
  }

	/**
	 * Parameterized constructor.
	 * @param userId - User ID
	 * @param userName - User Name
	 * @param userEmail - User Email
	 * @param lenderDescription - Lender description
	 * @param tenureRange - Tenure Range
	 * @param loanAmountRange - Loan Amount Range
	 * @param loanInterest - Loan Interest
	 */
  public BorrowerViewLenderDTO(int userId, String userName, String userEmail, String lenderDescription, String tenureRange, String loanAmountRange,
      int loanInterest) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.userEmail = userEmail;
    this.lenderDescription = lenderDescription;
    this.tenureRange = tenureRange;
    this.loanAmountRange = loanAmountRange;
    this.loanInterest = loanInterest;
  }

  /**
	 * Gets User ID.
	 * @return User ID
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets User ID.
	 * @param userId - User ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets User name.
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Set User name.
	 * @param userName - User Name 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Gets User email.
	 * @return string
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * Sets user email.
	 * @param userEmail string
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * Gets lender's description.
	 * @return string
	 */
	public String getLenderDescription() {
		return lenderDescription;
	}
	/**
	 * Sets lender's description.
	 * @param lenderDescription string
	 */
	public void setLenderDescription(String lenderDescription) {
		this.lenderDescription = lenderDescription;
	}
	/**
	 * Gets tenure range.
	 * @return string
	 */
	public String getTenureRange() {
		return tenureRange;
	}
	/**
	 * sets tenure range.
	 * @param tenureRange string
	 */
	public void setTenureRange(String tenureRange) {
		this.tenureRange = tenureRange;
	}
	/**
	 * Gets loan amount range.
	 * @return string
	 */
	public String getLoanAmountRange() {
		return loanAmountRange;
	}
	/**
	 * sets loan amount range.
	 * @param loanAmountRange string
	 */
	public void setLoanAmountRange(String loanAmountRange) {
		this.loanAmountRange = loanAmountRange;
	}
	/**
	 * Gets loan interest.
	 * @return integer
	 */
	public int getLoanInterest() {
		return loanInterest;
	}
	/**
	 * sets loan interest.
	 * @param loanInterest integer
	 */
	public void setLoanInterest(int loanInterest) {
		this.loanInterest = loanInterest;
	}
	@Override
	public String toString() {
		return "BorrowerViewLenderDTO [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", lenderDescription=" + lenderDescription + ", tenureRange=" + tenureRange + ", loanAmountRange="
				+ loanAmountRange + ", loanInterest=" + loanInterest + "]";
	}
	
	

}
