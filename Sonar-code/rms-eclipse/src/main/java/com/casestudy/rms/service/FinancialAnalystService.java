package com.casestudy.rms.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.casestudy.rms.dto.FinancialAnalystReceivedCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystTopBorrowerDTO;
import com.casestudy.rms.dto.FinancialAnalystViewDetailsDTO;

/**
 * Declares set of services for FinancialAnalystService.
 */
public interface FinancialAnalystService {
    /** Fetch Credit Application.
    * @param faid - financial analyst ID.
    * @return - List of Credit application corresponding to FA.
    */
//	@Secured("ROLE_FINANCIAL_ANALYST")
	@PreAuthorize ("hasRole('ROLE_FINANCIAL_ANALYST')")
    List<FinancialAnalystReceivedCreditAppDTO>fetchCreditApp(int faid);
    
    
    /**  Fetch Details of Credit Application , Lender and BorrowerEmail.
    * 
    * @param faid - financial analyst ID.
    * @param appId - Credit Application ID.
    * @param borrowerEmail - Borrower Email.
    * @return - required details, so that Financial analyst can approve or reject.
    */
//	@Secured("ROLE_FINANCIAL_ANALYST")
	@PreAuthorize ("hasRole('ROLE_FINANCIAL_ANALYST')")
	FinancialAnalystViewDetailsDTO fetchDetailsforFinancialAnalyst(int faid,int appId, String borrowerEmail );

	/**
	 * Gets Top borrower of lender.
	 * @param financialAnalystId - Financial Analyst ID
	 * @return List of Top Borrowers
	 */
//	@Secured("ROLE_FINANCIAL_ANALYST") 
	@PreAuthorize ("hasRole('ROLE_FINANCIAL_ANALYST')")
    List<FinancialAnalystTopBorrowerDTO> getTopBorrower(int financialAnalystId);
}