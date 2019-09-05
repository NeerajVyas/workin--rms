package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.FinancialAnalyst;

/** Declares method for Financial Analyst DAO. */
public interface FinancialAnalystDAO {
    /** Checks Financial Analyst exists or not.
     * 
     * @param financialAnalyst
     *            - Financial Analyst
     * @return Exists or not */
    boolean financialAnalystExist(FinancialAnalyst financialAnalyst);
    
    /**
     * View Financial Analyst.
     * @return List of Financial Analyst
     */
    List<FinancialAnalyst> viewFinancialAnalyst();
    
    /**
     * Fetching lender id from Financial Analyst table.
     * @param faid - financial analyst id.
     * @return lender-id.
     */
    int fetchLenderId(int faid);
   
    /**
     * Update Financial analyst status.
     * @param faId - Financial Analyst ID
     * @param status A/I status of financial Analyst
     */
    void updateFinancialAnalystStatus(int faId,int status);

}