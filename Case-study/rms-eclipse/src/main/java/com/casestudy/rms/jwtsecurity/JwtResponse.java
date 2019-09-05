package com.casestudy.rms.jwtsecurity;

import java.io.Serializable;

/**
 * This is class is required for creating a response containing the JWT to be returned to the user.
 *
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	
	/** Constructor which initializes the jwt-token property.
	 * 
	 * @param jwttoken
	 */
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	/** Getter method which returns the token.
	 * 
	 * @return
	 */
	public String getToken() {
		return this.jwttoken;
	}
}