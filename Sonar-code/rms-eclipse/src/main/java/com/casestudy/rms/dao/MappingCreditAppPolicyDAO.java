package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.model.MappingCreditAppPolicy;

/**
 * Declares method for Mapping credit application DAO.
 */
public interface MappingCreditAppPolicyDAO {

  /**
   * Submit values in mapping credit policy.
   * @param mappingCreditAppPolicy - Mapping Credit application Policy
   */
  void submitFormValue(MappingCreditAppPolicy mappingCreditAppPolicy); 
  
  /**
   * Fetch Policy values according to application ID.
   * @param appId - Application ID
   * @return List of Mapping Credit App Policy
   */
  List<MappingCreditAppPolicy> fetchPolicy(int appId);
}
