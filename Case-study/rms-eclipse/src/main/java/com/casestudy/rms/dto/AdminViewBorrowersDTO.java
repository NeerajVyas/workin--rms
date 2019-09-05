package com.casestudy.rms.dto;

/**
 * class for view borrowers by admin.
 * @author impetus
 *
 */
public class AdminViewBorrowersDTO {

	private String borrowerId;
	private	String borrowerName;
	private	String borrowerEmail;
	private	String borrowerStatus;
	private String noOfApplication;
	
	/**
	 * No args constructor.
	 */
	public AdminViewBorrowersDTO() {
    super();
  }
	
	/**
	 * Parameterized constructor.
	 * @param borrowerId - Borrower ID
	 * @param borrowerName - Borrower Name
	 * @param borrowerEmail - Borrower Email
	 * @param borrowerStatus - Borrower Status
	 * @param noOfApplication - No. of Application
	 */
  public AdminViewBorrowersDTO(String borrowerId, String borrowerName, String borrowerEmail, String borrowerStatus, String noOfApplication) {
    super();
    this.borrowerId = borrowerId;
    this.borrowerName = borrowerName; 
    this.borrowerEmail = borrowerEmail;
    this.borrowerStatus = borrowerStatus;
    this.noOfApplication = noOfApplication;
  }
  /**
	 * getter for borrower id.
	 * @return string
	 */
	public String getBorrowerId() {
		return borrowerId;
	}
	/**
	 * setter for borrower id.
	 * @param borrowerId void
	 */
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	/**
	 * getter for borrower name.
	 * @return string
	 */
	public String getBorrowerName() {
		return borrowerName;
	}
	/**
	 * setter for borrower name.
	 * @param borrowerName string
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	/**
	 * getter for borrower email.
	 * @return string
	 */
	public String getBorrowerEmail() {
		return borrowerEmail;
	}
	/**
	 * setter for borrower email.
	 * @param borrowerEmail string
	 */
	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}
	/**
	 * getter for borrower status.
	 * @return string
	 */
	public String getBorrowerStatus() {
		return borrowerStatus;
	}
	/**
	 * setter for borrower status.
	 * @param borrowerStatus string
	 */
	public void setBorrowerStatus(String borrowerStatus) {
		this.borrowerStatus = borrowerStatus;
	}
	/**
	 * getter for number of application requests by borrower.
	 * @return string
	 */
	public String getNoOfApplication() {
		return noOfApplication;
	}
	/**
	 * setter for number application requests by borrowers.
	 * @param noOfApplication string
	 */
	public void setNoOfApplication(String noOfApplication) {
		this.noOfApplication = noOfApplication;
	}
	@Override
	public String toString() {
		return "AdminViewBorrowersDTO [borrowerId=" + borrowerId + ", borrowerName=" + borrowerName + ", borrowerEmail="
				+ borrowerEmail + ", borrowerStatus=" + borrowerStatus + ", noOfApplication=" + noOfApplication + "]";
	}
	
	
}
