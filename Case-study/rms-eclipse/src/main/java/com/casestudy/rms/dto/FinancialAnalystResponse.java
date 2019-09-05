package com.casestudy.rms.dto;
/**
 * DTO for financial analyst.
 * @author impetus
 *
 */
public class FinancialAnalystResponse implements Comparable<FinancialAnalystResponse> {

    private String financialAnalystId;
    private String financialAnalystEmail;
    private String financialAnalystName;
    private long pendingRequests;
    
    /**
     * Gets financial Analyst ID.
     * @return Financial Analyst ID
     */
    public String getFinancialAnalystId() {
      return financialAnalystId;
  }
    
    /**
     * Parameterized constructor.
     * @param financialAnalystId - FA ID
     * @param financialAnalystEmail - FA Email
     * @param financialAnalystName - FA Name
     * @param pendingRequests - No. of Pending Requests
     */
    public FinancialAnalystResponse(String financialAnalystId, String financialAnalystEmail, String financialAnalystName, long pendingRequests) {
      super();
      this.financialAnalystId = financialAnalystId;
      this.financialAnalystEmail = financialAnalystEmail;
      this.financialAnalystName = financialAnalystName;
      this.pendingRequests = pendingRequests;
    }
    /**
     * gets financial analyst's id.
     * @return string
     */
  
    /**
     * sets financial analyst's id.
     * @param financialAnalystId string
     */
    public void setFinancialAnalystId(String financialAnalystId) {
        this.financialAnalystId = financialAnalystId;
    }
    /**
     * No args constructor.
     */
    public FinancialAnalystResponse() {
      super();
    }
    /**
     * gets financial analyst's name.
     * @return string
     */
    public String getFinancialAnalystName() {
        return financialAnalystName;
    }
    /**
     * sets financial analyst's name.
     * @param financialAnalystName string
     */
    public void setFinancialAnalystName(String financialAnalystName) {
        this.financialAnalystName = financialAnalystName;
    }
    /**
     * gets financial analyst's pending requests.
     * @return string
     */
    public long getPendingRequests() {
        return pendingRequests;
    }
    /**
     * sets pending requests.
     * @param pendingRequests long
     */
    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
    /**
     * gets financial analyst's email..
     * @return string
     */
    public String getFinancialAnalystEmail() {
        return financialAnalystEmail;
    }
    /**
     * sets financial analyst email.
     * @param financialAnalystEmail string
     */
    public void setFinancialAnalystEmail(String financialAnalystEmail) {
        this.financialAnalystEmail = financialAnalystEmail;
    }

	@Override
	public int compareTo(FinancialAnalystResponse o) {
		if(pendingRequests==o.pendingRequests) {
			return 0;
		}else if(pendingRequests>o.pendingRequests) {
			return 1;
		}else {
			return -1;
		}
		
	}    
    
}