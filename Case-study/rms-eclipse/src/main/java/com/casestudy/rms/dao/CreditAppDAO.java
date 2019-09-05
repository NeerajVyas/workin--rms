package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.CreditApplication;

/**
 * Declares methods for Credit Application DAO.
 */
public interface CreditAppDAO {
 

  /**
   * Submit Credit Application by Borrower.
   * @param creditApp - Credit Application
   */
  void submitCreditAppForm(CreditApplication creditApp);
  
  /** Fetch List of credit application.
   * 
   * @param faid - current financial analyst logged in.
   * @param status - hold,approve,reject.
   * @return List of Credit Application
   */
  List<CreditApplication> getCreditApplication(int faid, int status);
  
  /**
   * Gets Maximum Application ID.
   * @return Max Application ID
   */
  int getMaxApplicationIdId();
  
  /**
   * Assigns Credit Application to Financial Analyst. 
   * @param creditApp - Credit Application
   * @return true/false
   */
  boolean assignCreditApplication(CreditApplication creditApp);
  
  /**
   * Update Credit score according to credit score formula.
   * @param applicationId - Application ID
   * @param creditScore - evaluated credit score
   * @param applicationStatus - Application Status
   */
  void updateCreditScore(int applicationId, int creditScore,int applicationStatus);
  
  /**
   * Gets Top Borrower corresponding to Lender.
   * @param lenderId - Lender ID
   * @return List of credit application
   */
  List<CreditApplication> getTopBorrowerByLenderID(int lenderId);
  
  /**
   * Gets credit application according to borrower ID.
   * @param borrowerId - Borrower ID
   * @return List of Credit application
   */
  List<CreditApplication> getCreditApplicationByBorrowerId(int borrowerId);
  
  /**
   * Gets credit application with the help of application ID.
   * @param appId - Application ID
   * @return credit application
   */
  CreditApplication getCreditApplicationByAppId(int appId);
  
  /**
   * Update credit application by FinancialAnalyst.
   * @param applicationId - Application ID
   * @param applicationStatus - Application Status
   */
  void updateCreditApplication(int applicationId, int applicationStatus);
  
  /**
   * Returns list of previously updated credit application by financial analyst.
   * @param faId - Financial Analyst ID
   * @return List of credit application
   */
  List<CreditApplication> viewPreviousUpdatedApplications(int faId);
  

}
