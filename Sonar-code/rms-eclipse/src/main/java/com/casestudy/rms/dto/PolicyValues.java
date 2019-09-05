package com.casestudy.rms.dto;
/**
 * DTO for getting and setting policy values.
 *
 */
public class PolicyValues {
  
  private int policyId;
  private String policyValue;
  private String policyName;
  
  
  
  
  public PolicyValues() {
	super();
}
public PolicyValues(int policyId, String policyValue, String policyName) {
	super();
	this.policyId = policyId;
	this.policyValue = policyValue;
	this.policyName = policyName;
}
/**
   * gets policy id.
   * @return integer
   */
  public int getPolicyId() {
    return policyId;
  }
  /**
   * sets policy id.
   * @param policyId integer
   */
  public void setPolicyId(int policyId) {
    this.policyId = policyId;
  }
  /**
   * gets policy value.
   * @return string
   */
  public String getPolicyValue() {
    return policyValue;
  }
  /**
   * sets policy value.
   * @param policyValue string
    */
  public void setPolicyValue(String policyValue) {
    this.policyValue = policyValue;
  }
  /**
   * Get Policy Name.
   * @return Policy Name
   */
  public String getPolicyName() {
	return policyName;
  }
  /**
   * Sets Policy Name.
   * @param policyName - Policy Name
   */
  public void setPolicyName(String policyName) {
	this.policyName = policyName;
  }
  @Override
  public String toString() {
    return "PolicyValues [policyId=" + policyId + ", policyValue=" + policyValue + "]";
  }
  
  

}
