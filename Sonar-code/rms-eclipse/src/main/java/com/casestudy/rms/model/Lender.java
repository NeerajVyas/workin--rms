package com.casestudy.rms.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/** Represents a Lender which extends User properties. */
@Entity
@Table(name = "LENDER")
@PrimaryKeyJoinColumn(name = "LENDER_ID")
public class Lender extends User {

    @Column(name = "LOAN_INTEREST")
    private int loanInterest;

    @Column(name = "LENDER_DESCRIPTION")
    private String lenderDescription;

    @Column(name = "TENURE_RANGE")
    private String tenureRange;

    @Column(name = "LOAN_AMOUNT_RANGE")
    private String loanAmountRange;

    @OneToMany
    @JoinColumn(name = "lender_LENDER_ID")
    private List<FinancialAnalyst> financialAnalysts;

    /**
     * No args constructor.
     */
    public Lender() {
      super();
    }

    /**
     * Parameterized constructor.
     * @param userId - User ID
     * @param userName - User Name
     * @param userPassword - User Password
     * @param userEmail - User Email
     * @param userRole - User Role
     * @param creationDate - Creation Date
     * @param modificationDate - Modification Date
     * @param userAIStatus - User A/I Status
     * @param loanInterest - Loan Interest
     * @param lenderDescription - Lender Description
     * @param tenureRange - Tenure Range
     * @param loanAmountRange - Loan Amount Range
     * @param financialAnalysts - Financial Analysts
     */
    public Lender(int userId, String userName, String userPassword, String userEmail, String userRole, LocalDateTime creationDate,
        LocalDateTime modificationDate, int userAIStatus, int loanInterest, String lenderDescription, String tenureRange, String loanAmountRange,
        List<FinancialAnalyst> financialAnalysts) {
      super(userId, userName, userPassword, userEmail, userRole, creationDate, modificationDate, userAIStatus);
      this.loanInterest = loanInterest;
      this.lenderDescription = lenderDescription;
      this.tenureRange = tenureRange;
      this.loanAmountRange = loanAmountRange;
      this.financialAnalysts = financialAnalysts;
    }




    /** Gets a list of Financial Analyst corresponding to Lender.
     * 
     * @return List of Financial Analyst */
    public List<FinancialAnalyst> getFinancialAnalysts() {
        return financialAnalysts;
    }

    /** Sets a list of Financial Analyst corresponding to Lender.
     * 
     * @param financialAnalysts
     *            - List of Financial Analyst */
    public void setFinancialAnalysts(List<FinancialAnalyst> financialAnalysts) {
        this.financialAnalysts = financialAnalysts;
    }

    /** Gets offered Loan Interest of Lender.
     * 
     * @return Loan Interest */
    public int getLoanInterest() {
        return loanInterest;
    }

    /** Sets offered Loan Interest of Lender.
     * 
     * @param loanInterest
     *            - Loan Interest */
    public void setLoanInterest(int loanInterest) {
        this.loanInterest = loanInterest;
    }

    /** Gets Description of Lender.
     * 
     * @return Lender Description */
    public String getLenderDescription() {
        return lenderDescription;
    }

    /** Sets Description of Lender.
     * 
     * @param lenderDescription
     *            - Lender Description */
    public void setLenderDescription(String lenderDescription) {
        this.lenderDescription = lenderDescription;
    }

    /** Gets offered Tenure range of Lender.
     * 
     * @return Tenure Range */
    public String getTenureRange() {
        return tenureRange;
    }

    /** Sets offered Tenure range of Lender.
     * 
     * @param tenureRange
     *            - Tenure Range */
    public void setTenureRange(String tenureRange) {
        this.tenureRange = tenureRange;
    }

    /** Gets offered Loan amount range.
     * 
     * @return - Loan Amount Range */
    public String getLoanAmountRange() {
        return loanAmountRange;
    }

    /** Sets offered Loan amount range.
     * 
     * @param loanAmountRange
     *            - Loan Amount Range */
    public void setLoanAmountRange(String loanAmountRange) {
        this.loanAmountRange = loanAmountRange;
    }

}