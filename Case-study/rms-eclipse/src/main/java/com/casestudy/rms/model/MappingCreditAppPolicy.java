package com.casestudy.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Represents model for Mapping Credit Application Policy.
 */
@Entity
@Table(name = "MAPPING_CREDITAPP_POLICY")
public class MappingCreditAppPolicy {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "FIELD_ID")
  private int fieldId;

  @Column(name = "APPLICATION_ID")
  private int applicationId;

  @Column(name = "POLICY_ID")
  private int policyId;

  @Column(name = "POLICY_VALUE")
  private String policyValue;

  /**
   * No args Constructor.
   */
  public MappingCreditAppPolicy() {
    super();
  }

  /**
   * Parameterized constructor.
   * @param fieldId - Field ID
   * @param applicationId - Application ID
   * @param policyId - Policy ID
   * @param policyValue - Policy Value
   */
  public MappingCreditAppPolicy(int fieldId, int applicationId, int policyId, String policyValue) {
    super();
    this.fieldId = fieldId;
    this.applicationId = applicationId;
    this.policyId = policyId;
    this.policyValue = policyValue;
  }

  /**
   *Gets a field ID.
   * @return field ID
   */
  public int getFieldId() {
    return fieldId;
  }

  /**
   * Sets a field ID.
   * @param fieldId - field ID
   */
  public void setFieldId(int fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * Gets an application ID.
   * @return application ID
   */
  public int getApplicationId() {
    return applicationId;
  }

  /**
   * Sets an application ID.
   * @param applicationId - application ID
   */
  public void setApplicationId(int applicationId) {
    this.applicationId = applicationId;
  }

  /**
   * Gets a policy ID.
   * @return policy ID
   */
  public int getPolicyId() {
    return policyId;
  }

  /**
   * Sets a policy ID.
   * @param policyId - Policy ID
   */
  public void setPolicyId(int policyId) {
    this.policyId = policyId;
  }

  /**
   * Gets the policy Value.
   * @return Policy Value - Policy value
   */
  public String getPolicyValue() {
    return policyValue;
  }

  /**
   * Sets the policy Value.
   * @param policyValue - Policy Value
   */
  public void setPolicyValue(String policyValue) {
    this.policyValue = policyValue;
  }
  
  @Override
  public String toString() {
    return "MappingCreditAppPolicy [fieldId=" + fieldId + ", applicationId=" + applicationId + ", policyId=" + policyId + ", policyValue="
        + policyValue + "]";
  }

}