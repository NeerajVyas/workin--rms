package com.casestudy.rms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/** Represents a policy. */
@Entity
@Table(name = "POLICY")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POLICY_ID")
    private int policyId;

    @Column(name = "POLICY_NAME")
    private String policyName;

    @Column(name = "LENDER_ID")
    private int addedBy;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "MODIFICATION_DATE")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    /**
     * No args constructor.
     */
    public Policy() {
      super();
    }

    /**
     * Parameterized constructor.
     * @param policyId - Policy ID
     * @param policyName - Policy Name
     * @param addedBy - Added By
     * @param creationDate - Creation Date
     * @param modificationDate - Modification Date
     */
    public Policy(int policyId, String policyName, int addedBy, LocalDateTime creationDate, LocalDateTime modificationDate) {
      super();
      this.policyId = policyId;
      this.policyName = policyName;
      this.addedBy = addedBy;
      this.creationDate = creationDate;
      this.modificationDate = modificationDate;
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

    /** Gets ID who added policy.
     * 
     * @return ID */
    public int getAddedBy() {
        return addedBy;
    }

    /** Sets ID who added policy.
     * 
     * @param addedBy
     *            - ID */
    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }

    /** Gets Creation date.
     * 
     * @return date */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /** Sets Creation date.
     * 
     * @param creationDate
     *            - date */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /** Gets Modification date.
     * 
     * @return date */
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    /** Sets Modification date.
     * 
     * @param modificationDate
     *            - date */
    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

}