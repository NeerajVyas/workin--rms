package com.casestudy.rms.service;

import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;


/** Declare set of services for Administrator. */
public interface AdminService {
    
    /** Provides list of Lender that are Active.
     * 
     * @return List of Active lenders */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	List<AdminViewLendersDTO> getActiveLenders();

    /** Provides list of Lender that are Inactive.
     * 
     * @return List of Inactive lenders */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	List<AdminViewLendersDTO> getInactiveLenders();
    
	/**
	 * Deactivates a lender.
	 * @param lenderId - Lender ID
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel deactivateLender(int lenderId);
    
	/**
	 * Activates a Lender.
	 * @param lenderId - Lender ID
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel activateLender(int lenderId);
    
	/**
	 * Gets list of active borrowers.
	 * @return List of active borrowers
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    List<AdminViewBorrowersDTO> getActiveBorrowers();
    
	/**
	 * Gets list of inactive borrowers.
	 * @return list of inactive borrowers
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    List<AdminViewBorrowersDTO> getInactiveBorrowers();
    
	/**
	 * Deactivate a borrower.
	 * @param borrowerId - borrower ID
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel deactivateBorrower(int borrowerId);
	
	/**
	 * Activates a borrower.
	 * @param borrowerId - Borrower Id
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel activateBorrower(int borrowerId);
}