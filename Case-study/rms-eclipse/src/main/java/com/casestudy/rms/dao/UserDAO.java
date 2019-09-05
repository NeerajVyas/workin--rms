package com.casestudy.rms.dao;

import com.casestudy.rms.model.User;

/** Declares method for User DAO. */
public interface UserDAO {

	/**
	 * Register a Borrower.
	 * 
	 * @param user - User
	 */
	void registerBorrower(User user);

	/**
	 * Checks Borrower exists or not.
	 * 
	 * @param user - Borrower
	 * @return - Exists or not
	 */
	boolean userExists(User user);

	/**
	 * Get userDetails by Email.
	 * 
	 * @param userEmail - Email of the user.
	 * @return - User Object.
	 */
	User getUserByEmail(String userEmail);

	/**
	 * Get user name by borrower Id.
	 * 
	 * @param bid - borrower ID.
	 * @return - name of the user.
	 */
	String getUserNamebyUserID(int bid);

	/**
	 * Get user email by borrower Id.
	 * 
	 * @param bid - borrower ID.
	 * @return - Email of the user.
	 */
	String getUserEmailbyUserID(int bid);
	
	/**
	 * Updates Borrower A/I status.
	 * @param borrowerId - Borrower ID
	 * @param status - A/I status
	 */
	void updateBorrowerStatus(int borrowerId,int status);
	
	/**
	 * Updates User Password.
	 * @param user - User
	 * @param newPassword - New Password
	 */
	void updateUser(User user,String newPassword);
	

}
