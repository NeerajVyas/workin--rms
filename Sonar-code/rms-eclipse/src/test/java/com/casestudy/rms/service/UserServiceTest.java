package com.casestudy.rms.service;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.dao.impl.OTPDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDetailDAOImpl;
import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.NewPasswordDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.impl.UserServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
  @MockBean
  private UserDAOImpl userDAO;
  @MockBean
  private CreditAppDAOImpl creditAppDAO;
  @MockBean
  private PolicyDetailDAOImpl policyDetailDAO;
  @MockBean
  private OTPDAOImpl otpDAO;
  @Autowired
  private UserServiceImpl userService;
  
  String emailID="malviyaaman474@gmail.com";
  String otp="123456";

  CreditApplication creditApplication = new CreditApplication(100, 50, 1, "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);
  User user = new User(100, "MockUser", "12345678", emailID, "ROLE_LENDER",
      LocalDateTime.now(), LocalDateTime.now(), 1);
  NewPasswordDTO newPasswordDTo = new NewPasswordDTO();
      
//  @Test 
//  public void sendOTPTest() {
//    when(otpDAO.presentOTP(emailID,"NOT_VERIFIED")).thenReturn(true);
//    user.setUserEmail(emailID);
//    ResponseModel response = new ResponseModel();
//    when(userDAO.userExists(user)).thenReturn(true);
//    response.setMessage("OTP has been sent to Email ID");
//    response.setStatus(HttpStatus.OK.value());
//    userService.sendOTP(emailID);
//  }
  
  @Test
  public void checkOTPTest() {
    when(otpDAO.checkOTP(emailID, otp)).thenReturn(true);
    when(otpDAO.presentOTP(emailID,"NOT_VERIFIED")).thenReturn(true);
    when(otpDAO.presentOTP(emailID,"NOT_VERIFIED")).thenReturn(true);
    ResponseModel response = new ResponseModel();
    response.setMessage("Your OTP is not verified");
    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.setTimestamp(LocalDateTime.now());  
    userService.checkOTP(emailID, otp);
  }
  
  @Test
  public void updatePasswordTest() {
    newPasswordDTo.setEmailID(emailID);
    when(otpDAO.presentOTP(newPasswordDTo.getEmailID(),"NOT_VERIFIED")).thenReturn(true);
    when(userDAO.getUserByEmail(newPasswordDTo.getEmailID())).thenReturn(user);
    ResponseModel response = new ResponseModel();
    response.setMessage("Your password has been updated successfully");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now()); 
    userService.updatePassword(newPasswordDTo);
  }
  @Test
  public void registerBorrowerTest() {
    User user = new User(100, "MockUser", "12345678", "mock@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1);
    when(userDAO.userExists(user)).thenReturn(true);
    boolean result = userDAO.userExists(user);
    assertEquals(true,result);
    when(userDAO.userExists(user)).thenReturn(false);
    boolean result2 = userDAO.userExists(user);
    assertEquals(false, result2);
    ResponseModel response = new ResponseModel();
    response.setMessage("User registered successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    userService.registerBorrower(user);
  }
}
