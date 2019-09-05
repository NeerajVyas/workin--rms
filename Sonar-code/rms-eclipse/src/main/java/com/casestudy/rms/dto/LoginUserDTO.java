package com.casestudy.rms.dto;

/**
 * DTO for login by user.
 *
 */
public class LoginUserDTO {
	
	private int userId;
	private String userName;
    private String userEmail;
    private String userRole;
    private int userAIStatus;
    
    /**
     * No args constructor.
     */
    public LoginUserDTO() {
      super();
    }
    
    /**
     * Parameterized constructor.
     * @param userId - User ID
     * @param userName - User Name
     * @param userEmail - User Email
     * @param userRole - User Role
     * @param userAIStatus - A/I Status
     */
    public LoginUserDTO(int userId, String userName, String userEmail, String userRole, int userAIStatus) {
      super();
      this.userId = userId;
      this.userName = userName;
      this.userEmail = userEmail;
      this.userRole = userRole;
      this.userAIStatus = userAIStatus;
    }
    /**
     * gets user id.
     * @return integer
     */
	public int getUserId() {
		return userId;
	}
	/**
	 * sets user id.
	 * @param userId integer
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * gets user name.
	 * @return string
 	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * sets user name.
	 * @param userName string
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * gets user email.
	 * @return string	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * sets user email.
	 * @param userEmail string
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * gets user role.
	 * @return string
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * sets user role.
	 * @param userRole string
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * gets user status.
	 * @return integer
	 */
	public int getUserAIStatus() {
		return userAIStatus;
	}
	/**
	 * sets user status.
	 * @param userAIStatus integer
	 */
	public void setUserAIStatus(int userAIStatus) {
		this.userAIStatus = userAIStatus;
	}
    
}
