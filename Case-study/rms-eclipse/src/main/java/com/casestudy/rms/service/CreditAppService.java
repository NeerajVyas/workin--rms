package com.casestudy.rms.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.dto.UpdatedCreditApplicationStatus;

/**
 * Declares method for Credit application service.
 */
public interface CreditAppService {
	

  /**
   * Submit Credit Application Form.
   * @param creditApp - Credit Application
   * @param borrowerId - Borrower ID
   * @return response
   */
//  @Secured("ROLE_BORROWER")
  @PreAuthorize ("hasRole('ROLE_BORROWER')")
  ResponseModel submitCreditAppForm(CreditPolicyMappingDTO creditApp, int borrowerId);
  
  /**
   * Gets credit application according to borrower ID.
   * @param borrowerId - Borrower ID
   * @return List of credit application
   */
  //@Secured("ROLE_BORROWER")
  @PreAuthorize ("hasRole('ROLE_BORROWER')")
  List<BorrowerAllCreditAppDTO> getCreditAppRecords(int borrowerId);
  
  /**
   * Updates credit application status.
   * @param creditApp - Credit Application
   * @return response
   */
  //@Secured("ROLE_FINANCIAL_ANALYST")
  @PreAuthorize ("hasRole('ROLE_FINANCIAL_ANALYST')")
  ResponseModel updateCreditApplicationStatus(UpdatedCreditApplicationStatus creditApp);
 
  /**
   * Gets previously updated application by financial analyst.
   * @param faId - Financial Analyst ID
   * @return Credit Application
   */
  //@Secured("ROLE_FINANCIAL_ANALYST")
  @PreAuthorize ("hasRole('ROLE_FINANCIAL_ANALYST')")
  List<BorrowerAllCreditAppDTO> viewPreviousUpdatedApplication(int faId);
  

  
 /**
  *Informs financial analyst for pending requests. 
  */
  void informFinancialAnalyst();
  
}
