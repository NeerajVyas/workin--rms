package com.casestudy.rms.service;

import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;


/** Declare set of services for Administrator. */
public interface AdminService {
    
    /** Provides list of Lender based on status.
     * 
     * @return List of lenders */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	List<AdminViewLendersDTO> getLendersWithStatus(int status);

    
	/**
	 * Update A/I status of a lender.
	 * @param lenderId - Lender ID
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel updateLenderStatus(int lenderId,int status);
    
	/**
	 * Gets list of borrowers based on status.
	 * @return List of borrowers
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    List<AdminViewBorrowersDTO> getBorrowersWithStatus(int status);
    
	/**
	 * Update A/I status of a borrower.
	 * @param borrowerId - borrower ID
	 * @return response
	 */
//	@Secured("ROLE_ADMIN")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
    ResponseModel updateBorrowerStatus(int borrowerId,int status);
	
	
}