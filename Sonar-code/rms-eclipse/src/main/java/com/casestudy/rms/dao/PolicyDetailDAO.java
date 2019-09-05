package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.PolicyDetail;

/**
 * Declares method for PolicyDetail DAO.
 */
public interface PolicyDetailDAO {
 
     /**
      * Add Policy Detail.    
      * @param policyDetail - Policy Detail
      * @return boolean
      */
     boolean addPolicyDetail(PolicyDetail policyDetail);
     
     /**
      * Check Policy Detail exists or not.
      * @param policyDetail - Policy Detail
      * @return boolean
      */
     boolean policyDetailExist(PolicyDetail policyDetail);
     
     /**
      * Update Policy Detail.
      * @param policyDetail - Policy Detail
      * @return boolean
      */
     boolean updatePolicyDetail(PolicyDetail policyDetail);
     
     /**
      * View Policy Detail based on Lender ID.
      * @param lenderId - Lender ID
      * @return boolean
      */
     List<PolicyDetail> viewpolicyDetail(int lenderId);
     
     /**
      * Find Policy Detail based on Lender ID and Policy ID.
      * @param policyId - Policy ID
      * @param lender - Lender
      * @return Policy Detail
      */
     PolicyDetail findPolicyDetail(int policyId, Lender lender);
     
     /**
      * Find Policy Detail according to Policy ID and Lender ID.
      * @param policyId - Policy ID
      * @param lenderId - Lender ID
      * @return Policy Detail
      */
     PolicyDetail findPolicyByID(int policyId, int lenderId);
}
