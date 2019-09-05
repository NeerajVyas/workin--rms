package com.casestudy.rms.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.BorrowerViewLenderDTO;
import com.casestudy.rms.dto.CreditApplicationDetails;
import com.casestudy.rms.dto.FinancialAnalystResponse;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;

/** Declares set of services for Lender. */
public interface LenderService {

    /** Register a Lender.
     * 
     * @param lender
     *            - Lender
     * @return Response
      */
    ResponseModel registerLender(Lender lender);

    /** Add Financial Analyst to Lender.
     * 
     * @param financialAnalyst
     *            - Financial Analyst
     * @param lender
     *            - Lender
     * @return Response
      */
//    @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    ResponseModel addFinancialAnalyst(FinancialAnalyst financialAnalyst, Lender lender);

    /** Gets Lender corresponding to Lender ID.
     * 
     * @param lenderId
     *            - Lender ID
     * @return Lender */
    
//    @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    Lender getLender(int lenderId);

    /** Add Policy.
     * 
     * @param policyResponse
     *            - Policy
     * @param lender
     *            - Lender
     * @return Response          
     */
  //  @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    ResponseModel addPolicy(PolicyResponse policyResponse, Lender lender);

    /** Update Policy.
     * 
     * @param policyResponses
     *            - Policy
     * @param lender
     *            - Lender
     * @return boolean */
   // @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    ResponseModel updatePolicyDetail(PolicyResponse[] policyResponses, Lender lender);

    /** View Policy.
     * 
     * @param lenderId
     *            - Lender ID
     * @return List of Policy */
    //@Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    List<PolicyResponse> viewPolicy(int lenderId);
    
    /**
     * View Financial Analysts.
     * @param lenderId - Lender ID
     * @return List of Financial Analyst
     */
//    @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    List<FinancialAnalystResponse> viewFinancialAnalyst(int lenderId);
    
    
    /** Provides list of Lender that are Active.
     * 
     * @return List of Active lenders */
//    @Secured("ROLE_BORROWER")
    @PreAuthorize ("hasRole('ROLE_BORROWER')")
    List<BorrowerViewLenderDTO> getActiveLenders();
    
    /**
     * Get Credit Application corresponding to Lender.
     * @param lenderId - Lender ID
     * @return List of Credit Application
     */
//    @Secured("ROLE_LENDER")
    @PreAuthorize ("hasRole('ROLE_LENDER')")
    List<BorrowerAllCreditAppDTO> getCreditApplication(int lenderId);
    
    /**
     * Assign Credit Application to Financial Analyst.
     * @param creditApp - Credit Application 
     * @return Response
     */
//    @Secured("ROLE_LENDER")
      @PreAuthorize ("hasRole('ROLE_LENDER')")
      ResponseModel assignCreditApplication(CreditApplication creditApp);
    
      /**
       * Return credit application details.
       * @param lenderId - lender ID
       * @param applicationId - application ID
       * @return Credit application Details
       */
      @PreAuthorize ("hasRole('ROLE_LENDER') or hasRole('ROLE_BORROWER')")
      CreditApplicationDetails viewCreditApplicationDetails(int lenderId,int applicationId);
}