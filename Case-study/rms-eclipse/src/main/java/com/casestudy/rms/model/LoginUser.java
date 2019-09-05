package com.casestudy.rms.model;

/** class used to validate login.
 */
public class LoginUser {
	private String status;
	private String userId;
	private String userRole;
	private String userEmail;

	/**
	 * No args constructor.
	 */
	public LoginUser() {
    super();
  }

	/**
	 * Parameterized Constructor.
	 * @param status - Status
	 * @param userId - User ID
	 * @param userRole - User role
	 * @param userEmail - User Email
	 */
  public LoginUser(String status, String userId, String userRole, String userEmail) {
    super();
    this.status = status;
    this.userId = userId;
    this.userRole = userRole;
    this.userEmail = userEmail;
  }

  /**
	 * Gets user email.
	 * @return user email
	 */
	public String getUserEmail() {
		return userEmail;
	}
    
	/**
	 * Set user email.
	 * @param userEmail - User email
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets user role.
	 * @return user role
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * Sets user role.
	 * @param userRole - User Role
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * Parameterized constructor.
	 * @param status A/I status
	 */
	public LoginUser(String status) {
		this.status=status;
	}	
	
	/**
	 * Gets User Status.
	 * @return A/I Status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets User status.
	 * @param status A/I status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get User Id.
	 * @return User ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set User ID.
	 * @param userId - User ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
