package com.casestudy.rms.jwtsecurity;

import java.io.Serializable;

/**
 * This class is required for storing the username and password we recieve from the client.
 *
 */
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	private String username;
	private String password;

	/**
	 * Default Constructor for JSON parsing.
	 * 
	 */
	public JwtRequest() {

	}

	/**
	 * Constructor to initializes the user-name and password properties.
	 * 
	 * @param username - initializes the user-name property.
	 * @param password - initializes the password property.
	 */
	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Getter method of user-name property.
	 * 
	 * @return - user-name of the user.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Setter method of user-name property.
	 * 
	 * @param username - reinitializes the user-name property.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter method of user-name property.
	 * 
	 * @return - user-name of the user.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Setter method of password property.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}