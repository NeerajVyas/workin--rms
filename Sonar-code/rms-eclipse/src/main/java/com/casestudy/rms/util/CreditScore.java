package com.casestudy.rms.util;

/** 
 * Represents Credit Score.
 *
 */
public final class CreditScore {
    
    /**
     * Private Constructor.
     */
    private CreditScore() {
        
    }
    /**
     * Calculate credit score.
     * @param creditScore - Previous credit score
     * @param policyThreshold - Policy Threshold
     * @param policyWeightage - Policy Weightage
     * @param policyValue - Policy Value
     * @return Credit Score
     */
    public static int calculateCreditScore(int creditScore,int policyThreshold,int policyWeightage,long policyValue) {
        
        if(policyValue>=policyThreshold) {
            creditScore = creditScore + policyWeightage;
        }else {
            creditScore = (int) (creditScore + (policyValue/policyThreshold)*policyWeightage);
        }
        
        return creditScore;
    }

}
