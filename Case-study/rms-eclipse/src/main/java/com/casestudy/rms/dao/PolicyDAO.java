package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.Policy;

/** Declares method for Policy DAO. */
public interface PolicyDAO {

    /**
     * Add Policy.
     * @param policy - Policy
     * @return boolean
     */
    boolean addPolicy(Policy policy);

    /**
     * View Policy based on Lender ID.
     * @param lenderId - Lender ID
     * @return List of Policy
     */
    List<Policy> viewPolicy(int lenderId);

    /**
     * Gets maximum Policy ID.
     * @return Max Policy ID
     */
    int getMaxPolicyId();

    /**
     * Gets Default Policy ID.
     * @return List of default Policy
     */
    List<Integer> getDefaultPolicyId();
    
    /**
     * Find Policy by Policy ID.
     * @param policyId - Policy ID
     * @return Policy
     */
    Policy findPolicy(int policyId);

    /**
     * Checks Policy name exists or not corresponding to Lender.
     * @param lenderId - Lender Id
     * @param policyName - Policy Name
     * @return boolean
     */
    boolean policyNameExist(int lenderId, String policyName);
    
    /**
     * Gets policy according to user ID.
     * @param userId - User ID
     * @return List of Policy
     */
    List<Policy> getPolicy(int userId);
}