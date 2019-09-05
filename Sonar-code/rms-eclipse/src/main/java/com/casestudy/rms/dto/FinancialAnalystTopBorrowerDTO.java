package com.casestudy.rms.dto;

/**
 *DTO gives response for Top Borrowers. 
 */
public class FinancialAnalystTopBorrowerDTO {
    
    private String userName;
    private String userEmail;
    private String companyName;
    private int creditScore;
    
    /**
     * No args constructor.
     */
    public FinancialAnalystTopBorrowerDTO() {
      super();
    }

    /**
     * Parameterized constructor.
     * @param userName - User Name
     * @param userEmail - User Email
     * @param companyName - Company Name
     * @param creditScore - Credit Score
     */
    public FinancialAnalystTopBorrowerDTO(String userName, String userEmail, String companyName, int creditScore) {
      super();
      this.userName = userName;
      this.userEmail = userEmail;
      this.companyName = companyName;
      this.creditScore = creditScore;
    }

    /**
     * Gets User Name.
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Sets User Name.
     * @param userName - User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Gets User Email.
     * @return Email
     */
    public String getUserEmail() {
        return userEmail;
    }
    
    /**
     * Sets User Email.
     * @param userEmail - Email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    /**
     * Gets Company Name.
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * Sets company Name.
     * @param companyName - Company Name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * Gets Credit Score.
     * @return Credit score
     */
    public int getCreditScore() {
        return creditScore;
    }
    
    /**
     * Sets Credit Score.
     * @param creditScore - Credit Score
     */
    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
    
    

}
