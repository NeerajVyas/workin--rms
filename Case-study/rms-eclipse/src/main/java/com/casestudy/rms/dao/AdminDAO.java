package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.User;

/** Declare methods for Administrator DAO. */
public interface AdminDAO {

    /** Get list of Lenders according to status.
     * 
     * @param status
     *            - User NotApproved/Active/Inactive status
     * @return - List of Lender */
    List<Lender> getLenderWithStatus(int status);
    
    /**
     * Returns number of credit application filled to Lender.
     * @param lenderId - Lender ID
     * @return No. of Credit Application
     */
    int creditApplicationCountToLender(int lenderId);
    
    /**
     * Returns number of financial analyst added by Lender.
     * @param lenderId - Lender ID
     * @return Number of Financial Analyst
     */
    int financialAnalystCountToLender(int lenderId);
    
    /**
     * Returns List of borrower according to status.
     * @param status - Active/Inactive status
     * @return List of User
     */
    List<User> getBorrowerWithStatus(int status);
    
    /**
     * Returns number of credit application filled by borrower.
     * @param borrowerId - Borrower ID
     * @return Number of Credit Application
     */
    int creditApplicationCountToBorrower(int borrowerId);
}