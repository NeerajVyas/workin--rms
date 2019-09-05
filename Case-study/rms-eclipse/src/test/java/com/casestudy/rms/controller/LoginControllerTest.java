package com.casestudy.rms.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.rms.controller.LoginController;
import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.LoginUserDTO;
import com.casestudy.rms.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
  /**
   * userdao which has to be mocked.
   */
  @MockBean
  private UserDAOImpl userDAO;
  /**
   * Login controller.
   */
  @Autowired
  private LoginController loginController;
  /**
   * constructor for user model.
   */
  User user = new User(100, "Mock User", "12345678", "mockUser@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1);
  /**
   * constructor for loginuserDTO.
   */
  LoginUserDTO logUserDTO = new LoginUserDTO(100, "Mock User", "mockUser@gmail.com", "ROLE_LENDER", 1);
/**
 * It tests user object can be fetched by particular email id.
 */
  @Test
  public void getUserByEmailTest() {
    String emailId = "mockUser@gmail.com";
    when(userDAO.getUserByEmail(emailId)).thenReturn(user);
    User result = userDAO.getUserByEmail(emailId);
    System.out.println(user + "         " + result);
    assertEquals(result, user);
    System.out.println(logUserDTO);
    System.out.println(loginController.validateLogin(emailId));
    loginController.validateLogin(emailId);
  }

}
