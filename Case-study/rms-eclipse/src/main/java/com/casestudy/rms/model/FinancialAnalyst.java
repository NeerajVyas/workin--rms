package com.casestudy.rms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



/** Represents a Financial Analyst who extends User properties. */
@Entity
@Table(name = "FINANCIAL_ANALYST")
@PrimaryKeyJoinColumn(name = "FINANCIAL_ANALYST_ID")
public class FinancialAnalyst extends User {
    
   

  @JsonBackReference
    @ManyToOne
    private Lender lender;

    /**
     * No args constructor.
     */
    public FinancialAnalyst() {
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
     * @param lender - Lender
     */
    public FinancialAnalyst(int userId, String userName, String userPassword, String userEmail, String userRole, LocalDateTime creationDate,
        LocalDateTime modificationDate, int userAIStatus, Lender lender) {
      super(userId, userName, userPassword, userEmail, userRole, creationDate, modificationDate, userAIStatus);
      this.lender = lender;
    }



    /**
     * Gets lender corresponding to financial analyst.
     * @return lender
     */
    public Lender getLender() {
        return lender;
    }

    /**
     * Sets lender corresponding to financial analyst.
     * @param lender - lender
     */
    public void setLender(Lender lender) {
        this.lender = lender;
    }
    
    
   

}