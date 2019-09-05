package com.casestudy.rms.service;

import com.casestudy.rms.dto.NewPasswordDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.User;

/** Declare a set of services for User. */
public interface UserService {

    /** Register a Borrower.
     * 
     * @param user
     *            - User
     * @return response
    */ 
    ResponseModel registerBorrower(User user);
    
    /**
     * Sends OTP corresponding to email ID.
     * @param emailID - email ID
     * @return response
     */
    ResponseModel sendOTP(String emailID);
    
    /**
     * Check OTP to reset password.
     * @param emailID - email ID
     * @param otp - OTP
     * @return response
     */
    ResponseModel checkOTP(String emailID, String otp);
    
    /**
     * Update Password corresponding to User.
     * @param newPass - New Password
     * @return response
     */
    ResponseModel updatePassword(NewPasswordDTO newPass);
}