package com.casestudy.rms.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.rms.controller.UserController;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.impl.CreditAppServiceImpl;
import com.casestudy.rms.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
  /**
   * user service which has to be mocked.
   */
  @MockBean
  private UserServiceImpl userService;
  /**
   * credit application service which has to be mocked.
   */
  @MockBean
  private CreditAppServiceImpl creditAppService;
  /**
   * User controller.
   */
  @Autowired
  private UserController userController;
  /**
   * User constructor.
   */
  User user = new User(100, "MockUser", "12345678", "mock@gmail.com", "ROLE_LENDER",
      LocalDateTime.now(), LocalDateTime.now(), 1);
  /**
   * constructor for mapping credit and policy.
   */
  CreditPolicyMappingDTO creditPolicyMappingDTO 
  = new CreditPolicyMappingDTO();
  /**
   * credit application constructor.
   */
  CreditApplication creditApplication = new CreditApplication(100, 50, 1, 
      "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);
  
  BorrowerAllCreditAppDTO borrowerAllCreditAppDTO = new BorrowerAllCreditAppDTO("5", "70", "Company Mock", "20/07/2019", "1",
      "Mock Lender");
 /**
  * It test register borrower method of user controller class.
  */
  @Test
  public void registerBorrowerTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("User registered successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(userService.registerBorrower(user)).thenReturn(response);
    ResponseEntity<ResponseModel> responseController = new ResponseEntity<ResponseModel>(
        response, HttpStatus.CREATED);
    assertEquals(responseController, userController.registerBorrower(user));
  }
/**
 * It test credit application is submitted or not.
 */
  @Test
  public void submitCreditAppFormTest() {
    int borrowerId = 1;
    Map<String, String> map = new HashMap<String, String>();
    map.put("policyId", "1");
    map.put("policyValue", "10000");
    List<Map<String, String>> policyValues = new ArrayList<>();
    policyValues.add(map);
    creditPolicyMappingDTO.setPolicyValues(policyValues);
    creditPolicyMappingDTO.setLenderId(2);
    creditPolicyMappingDTO.setCompanyName("test");
    creditPolicyMappingDTO.setBorrowerId(1);
    
    ResponseModel response = new ResponseModel();
    response.setMessage("Credit Application submitted successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(creditAppService.submitCreditAppForm(creditPolicyMappingDTO, borrowerId)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.CREATED), userController.submitCreditAppForm(creditPolicyMappingDTO, borrowerId));
  }
/**
 * It test application can be fetch by borrower's id.
 */
  @Test
  public void getCreditApplicationByBorrowerId() {
    int borrowerId = 1;
    List<BorrowerAllCreditAppDTO> lst = new ArrayList<BorrowerAllCreditAppDTO>();
    lst.add(borrowerAllCreditAppDTO);
    when(creditAppService.getCreditAppRecords(borrowerId)).thenReturn(lst);
    ResponseEntity<List<BorrowerAllCreditAppDTO>> responseController = new ResponseEntity<>(lst, HttpStatus.OK);
    assertEquals(responseController, userController.getCreditApplicationByBorrowerId(borrowerId));
  }

}
