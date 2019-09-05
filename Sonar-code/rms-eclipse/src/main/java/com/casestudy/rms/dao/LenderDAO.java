package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.CreditApplication;

/** Declares method for Lender DAO. */
public interface LenderDAO {
    /** Register a Lender.
     * 
     * @param lender
     *            - Lender */
    void registerLender(Lender lender);

    /** Checks Lender exists or not.
     * 
     * @param lender
     *            - Lender
     * @return Exists or not */
    boolean lenderExists(Lender lender);

    /** Add financial Analyst corresponding to Lender.
     * 
     * @param financialAnalyst
     *            - Financial Analyst */
    void addFinancialAnalyst(FinancialAnalyst financialAnalyst);

    /** Gets Lender according to Lender ID.
     * 
     * @param lenderId
     *            - Lender ID
     * @return Lender */
    Lender getLender(int lenderId);

    /** Gets Maximum Lender Id.
     * 
     * @return Max Lender Id */
    int getMaxLenderId();


    /**
     * Gets Lender according to status.
     * @param status - A/I status
     * @return List of Lender
     */
    List<Lender> getLenderWithStatus(int status);
    
    
    
    
    /**
     *Returns list of credit application filled to lender.
     * @param lenderId - Lender ID
     * @return List of Credit Application
     */
    List<CreditApplication> getCreditApplication(int lenderId);
    
    /**
     * Gets pending requests of financial analyst.
     * @param financialAnalystId - Financial Analyst ID
     * @return number of pending request
     */
    long getPendingRequests(int financialAnalystId);
    
    /**
     * Update Lender A/I status.
     * @param lenderId - Lender ID
     * @param status - A/I status
     */
    void updateLenderStatus(int lenderId,int status);
    
    
    
}