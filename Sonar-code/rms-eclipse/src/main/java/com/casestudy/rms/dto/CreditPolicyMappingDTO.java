package com.casestudy.rms.dto;

import java.util.List;
import java.util.Map;

/**
 * DTO contain credit application details with Policy value detail.
 */
public class CreditPolicyMappingDTO {
  private String companyName;
  private int borrowerId;
  private int lenderId;
  private List<Map<String, String>> policyValues;

  /**
   * Get Company Name.
   * @return Company Name
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Set Company Name.
   * @param companyName - Company Name
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * Get Borrower ID.
   * @return Borrower ID
   */
  public int getBorrowerId() {
    return borrowerId;
  }

  /**
   * Set Borrower ID.
   * @param borrowerId - Borrower ID
   */
  public void setBorrowerId(int borrowerId) {
    this.borrowerId = borrowerId;
  }

  /**
   * Get Lender ID.
   * @return Lender ID
   */
  public int getLenderId() {
    return lenderId;
  }

  /**
   * Set Lender ID.
   * @param lenderId - Lender ID
   */
  public void setLenderId(int lenderId) {
    this.lenderId = lenderId;
  }

  /**
   * Get Policy name with policy value.
   * @return List of policy name with value
   */
  public List<Map<String, String>> getPolicyValues() {
    return policyValues;
  }

  /**
   * Set Policy name with policy value.
   * @param policyValues List of policy name with policy value
   */
  public void setPolicyValues(List<Map<String, String>> policyValues) {
    this.policyValues = policyValues;
  }

  @Override
  public String toString() {
    return "CreditPolicyMappingDTO [companyName=" + companyName + ", borrowerId=" + borrowerId + ", lenderId=" + lenderId + ", policyValues="
        + policyValues + "]";
  }

  

}
