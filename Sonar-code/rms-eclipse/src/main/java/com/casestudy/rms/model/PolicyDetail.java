package com.casestudy.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Represents Policy Detail. */
@Entity
@Table(name = "POLICY_DETAIL")
public class PolicyDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DETAIL_ID")
    private int detailId;

    @Column(name = "POLICY_ID")
    private int policyId;

    @Column(name = "LENDER_ID")
    private int lenderId;

    @Column(name = "THRESHOLD")
    private String threshold;

    @Column(name = "POLICY_WEIGHTAGE")
    private int policyWeightage;

    @Column(name = "POLICY_STATUS")
    private int policyStatus;

    /**
     * No args constructor.
     */
    public PolicyDetail() {
      super();
    }

    /**
     * Parameterized constructor.
     * @param detailId - Detail ID
     * @param policyId - Policy ID
     * @param lenderId - Lender ID
     * @param threshold - threshold
     * @param policyWeightage - Policy Weightage
     * @param policyStatus - Policy Status
     */
    public PolicyDetail(int detailId, int policyId, int lenderId, String threshold, int policyWeightage, int policyStatus) {
      super();
      this.detailId = detailId;
      this.policyId = policyId;
      this.lenderId = lenderId;
      this.threshold = threshold;
      this.policyWeightage = policyWeightage;
      this.policyStatus = policyStatus;
    }

    /** Gets Detail ID.
     * 
     * @return Detail ID */
    public int getDetailId() {
        return detailId;
    }

    /** Sets Detail ID.
     * 
     * @param detailId
     *            - Detail ID */
    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    /** Gets Policy ID.
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

    /** Gets Lender ID.
     * 
     * @return Lender ID */
    public int getLenderId() {
        return lenderId;
    }

    /** Sets Lender ID.
     * 
     * @param lenderId
     *            - Lender ID */
    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
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

    /** Gets Policy Status.
     * 
     * @return Policy Status */
    public int getPolicyStatus() {
        return policyStatus;
    }

    /** Sets Policy Status.
     * 
     * @param policyStatus
     *            - Policy Status */
    public void setPolicyStatus(int policyStatus) {
        this.policyStatus = policyStatus;
    }

}
