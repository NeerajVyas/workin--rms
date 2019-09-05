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

/**
 * Represents a Credit Application.
 */
@Entity
@Table(name = "CREDIT_APPLICATION")
public class CreditApplication {
  @Id
  @Column(name = "APPLICATION_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int applicationId;

  @Column(name = "CREDIT_SCORE")
  private int creditScore;

  @Column(name = "APPLICATION_STATUS")
  private int applicationStatus;

  @Column(name = "COMPANY_NAME")
  private String companyName;

  
  @Column(name = "CREATION_DATE")
  @CreationTimestamp
  private LocalDateTime creationDate;

  @Column(name = "MODIFICATION_DATE")
  @UpdateTimestamp
  private LocalDateTime modificationDate;

 

  @Column(name = "BORROWER_ID")
  private int borrowerId;
  
  @Column(name="FINANCIAL_ANALYST_ID")
  private int financialAnalystId;

  @Column(name = "LENDER_ID")
  private int lenderId;

  /**
   * No args constructor.
   */
  public CreditApplication() {
    super();
  }

  /**
   * Parameterized constructor.
   * @param applicationId - Application ID
   * @param creditScore - Credit Score
   * @param applicationStatus - Application status
   * @param companyName - Company Name
   * @param creationDate - Creation Date
   * @param modificationDate - Modification Date
   * @param borrowerId - Borrower ID
   * @param financialAnalystId - Financial analyst ID
   * @param lenderId - Lender ID
   */
  public CreditApplication(int applicationId, int creditScore, int applicationStatus, String companyName, LocalDateTime creationDate,
      LocalDateTime modificationDate, int borrowerId, int financialAnalystId, int lenderId) {
    super();
    this.applicationId = applicationId;
    this.creditScore = creditScore;
    this.applicationStatus = applicationStatus;
    this.companyName = companyName;
    this.creationDate = creationDate;
    this.modificationDate = modificationDate;
    this.borrowerId = borrowerId;
    this.financialAnalystId = financialAnalystId;
    this.lenderId = lenderId;
  }

  /**
   * Gets a borrower Id.
   * @return borrower ID
   */
  public int getBorrowerId() {
    return borrowerId;
  }

  /**
   * Sets Borrower Id in credit application.
   * @param borrowerId - Borrower Id
   */
  public void setBorrowerId(int borrowerId) {
    this.borrowerId = borrowerId;
  }

  /**
   * Gets lender Id of credit application.
   * @return Lender Id
   */
  public int getLenderId() {
    return lenderId;
  }

  /**
   * Sets Lender Id of credit application.
   * @param lenderId - Lender ID
   */
  public void setLenderId(int lenderId) {
    this.lenderId = lenderId;
  }
  /**
   * Gets application ID of credit application.
   * @return application ID
   */
  public int getApplicationId() {
    return applicationId;
  }
  /**
   * Sets application ID of credit application.
   * @param applicationId - Application ID
   */
  public void setApplicationId(int applicationId) {
    this.applicationId = applicationId;
  }
  /**
   * Gets credit score of credit application.
   * @return credit score
   */
  public int getCreditScore() {
    return creditScore;
  }

  /**
   * Sets credit score of credit application.
   * @param creditScore - credit score
   */
  public void setCreditScore(int creditScore) {
    this.creditScore = creditScore;
  }

  /**
   * Gets application status of credit application.
   * @return application status
   */
  public int getApplicationStatus() {
    return applicationStatus;
  }

  /**
   * Sets application status of credit application.
   * @param applicationStatus - application status
   */
  public void setApplicationStatus(int applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  /**
   * Gets company name of credit application.
   * @return credit application
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Set company name of credit application.
   * @param companyName - Company Name
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * Gets creation date of application.
   * @return Creation date
   */
  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  /**
   * sets creation date of credit application.
   * @param creationDate - creation date
   */
  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Gets modification date of credit application. 
   * @return modification date
   */
  public LocalDateTime getModificationDate() {
    return modificationDate;
  }

  /**
   * Sets modification date of credit application.
   * @param modificationDate - modification date
   */
  public void setModificationDate(LocalDateTime modificationDate) {
    this.modificationDate = modificationDate;
  }
  /**
   * Gets Financial Analyst ID of credit application.
   * @return Financial Analyst ID
   */
  public int getFinancialAnalystId() {
    return financialAnalystId;
  }

  /**
   * Sets Financial Analyst ID of credit application.
   * @param financialAnalystId - Financial Analyst ID
   */
  public void setFinancialAnalystId(int financialAnalystId) {
    this.financialAnalystId = financialAnalystId;
  }
}
