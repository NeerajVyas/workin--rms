package com.casestudy.rms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/** Represent an User who can be Borrower, Lender and Financial Analyst. */
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "MODIFICATION_DATE")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "USER_AI_STATUS")
    private int userAIStatus;

    /**
     * No args Constructor.
     */
    public User() {
      super();
    }

    /**
     * 
     * Parameterized constructor.
     * @param userId - User ID
     * @param userName - User Name
     * @param userPassword - User Password
     * @param userEmail - User Email
     * @param userRole - User Role
     * @param creationDate - Creation Date
     * @param modificationDate - Modification Date
     * @param userAIStatus - User A/I Status
     */
    public User(int userId, String userName, String userPassword, String userEmail, String userRole, LocalDateTime creationDate,
        LocalDateTime modificationDate, int userAIStatus) {
      super();
      this.userId = userId;
      this.userName = userName;
      this.userPassword = userPassword;
      this.userEmail = userEmail;
      this.userRole = userRole;
      this.creationDate = creationDate;
      this.modificationDate = modificationDate;
      this.userAIStatus = userAIStatus;
    }

    /** Get ID of an User.
     * 
     * @return User ID */
    public int getUserId() {
        return userId;
    }

    /** Set ID of an User.
     * 
     * @param userId
     *            - User ID */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Get Name of an User.
     * 
     * @return User Name */
    public String getUserName() {
        return userName;
    }

    /** Set Name of an User.
     * 
     * @param userName
     *            - User Name */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Get Password of an User.
     * 
     * @return User Password */
    public String getUserPassword() {
        return userPassword;
    }

    /** Set Password of an User.
     * 
     * @param userPassword
     *            - User Password */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /** Get Email of an User.
     * 
     * @return User Email */
    public String getUserEmail() {
        return userEmail;
    }

    /** Set Email of an User.
     * 
     * @param userEmail
     *            - User Email */
    public void setUserEmail(String userEmail) {
        
        this.userEmail = userEmail;
    }

    /** Get Role of an User.Roles are Borrower, Lender and Financial Analyst.
     * 
     * @return User Role */
    public String getUserRole() {
        return userRole;
    }

    /** Set Role of an User. Roles are Borrower, Lender and Financial Analyst.
     * 
     * @param userRole
     *            - User Role */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /** Get Creation Date of an User.
     * 
     * @return Creation Date */

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /** Set Creation Date of an User.
     * 
     * @param creationDate
     *            - Creation Date */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /** Get Modification Date of an User.
     * 
     * @return Modification Date */
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    /** Set Password of an User.
     * 
     * @param modificationDate
     *            - Modification Date */
    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    /** Get Active-Inactive Status of an User.
     * 
     * @return A/I Status */
    public int getUserAIStatus() {
        return userAIStatus;
    }

    /** Set Active-Inactive Status of an User.
     * 
     * @param userAIStatus
     *            - A/I Status */
    public void setUserAIStatus(int userAIStatus) {
        this.userAIStatus = userAIStatus;
    }

}