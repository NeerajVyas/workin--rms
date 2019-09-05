package com.casestudy.rms.dto;

/** Policy Response DTO. */
public class PolicyResponse implements Comparable<PolicyResponse> {

    private int policyId;
    private String policyName;
    private int policyWeightage;
    private String threshold;
    private int policyStatus;

    /**
     * No args constructor.
     */
    public PolicyResponse() {
      super();
    }

    /**
     * Parameterized constructor.
     * @param policyId - Policy ID
     * @param policyName - Policy Name
     * @param policyWeightage - Policy Weightage
     * @param threshold - Threshold
     * @param policyStatus - Policy A/I status
     */
    public PolicyResponse(int policyId, String policyName, int policyWeightage, String threshold, int policyStatus) {
      super();
      this.policyId = policyId;
      this.policyName = policyName;
      this.policyWeightage = policyWeightage;
      this.threshold = threshold;
      this.policyStatus = policyStatus;
    }

    /** Gets policy Id.
     * 
     * @return Policy ID */
    public int getPolicyId() {
        return policyId;
    }

    /** Sets Policy ID.
     * 
     * @param policyId
     *            - Policy ID */
    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    /** Gets Policy Name.
     * 
     * @return Policy Name */
    public String getPolicyName() {
        return policyName;
    }

    /** Sets Policy Name.
     * 
     * @param policyName
     *            - Policy Name */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    /** Gets Policy Weightage.
     * 
     * @return Policy Weightage */
    public int getPolicyWeightage() {
        return policyWeightage;
    }

    /** Sets Policy Weightage.
     * 
     * @param policyWeightage
     *            - Policy Weightage */
    public void setPolicyWeightage(int policyWeightage) {
        this.policyWeightage = policyWeightage;
    }

    /** Gets Threshold value.
     * 
     * @return Threshold value */
    public String getThreshold() {
        return threshold;
    }

    /** Sets Threshold value.
     * 
     * @param threshold
     *            - Threshold value */
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    /** Gets Policy status.
     * 
     * @return Policy Status */
    public int getPolicyStatus() {
        return policyStatus;
    }

    /** Sets policy Status.
     * 
     * @param policyStatus
     *            - Policy Status */
    public void setPolicyStatus(int policyStatus) {
        this.policyStatus = policyStatus;
    }

	@Override
	public int compareTo(PolicyResponse o) {
		if(policyStatus==o.policyStatus) {
			return 0;
		}else if(policyStatus<o.policyStatus) {
			return 1;
		}else {
			return -1;
		}
	}

}
