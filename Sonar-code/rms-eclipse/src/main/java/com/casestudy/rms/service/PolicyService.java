package com.casestudy.rms.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

import com.casestudy.rms.model.Policy;

/**
 * Declares method for Policy Service.
 */
public interface PolicyService {

	/**
	 * Gets Policy according to Lender ID.
	 * @param userID - Lender ID 
	 * @return List of Policy
	 */
	@PreAuthorize ("hasRole('ROLE_BORROWER')")
	List<Policy> getPolicies(int userID);

}
