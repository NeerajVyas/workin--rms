package com.casestudy.rms.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.casestudy.rms.controller.AdminController;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.service.impl.AdminServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

  int lenderId=2;
  int borrowerId=1;
  String stringLenderId="LR2";
  String stringBorrowerId="LR1";

  /**
   * constructor for lender.
   */
  Lender lender = new Lender(lenderId, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,"hello", "10-20", "100-200", null);
  AdminViewLendersDTO adminViewLendersDTO =new AdminViewLendersDTO("2", "Mock Lender", "mockLender@gmail.com", "10", "100-200",
  "100000-2000000", "5", "4", "1");
  AdminViewBorrowersDTO adminViewBorrowersDTO = new AdminViewBorrowersDTO("1", "Mock Borrower", "mockBorrower@gmail.com", "1","1");

  /**
   * admin service which has to be mocked.
   */
  @MockBean
  private AdminServiceImpl adminService;
  /**
   * admin controller. 
   */
  @Autowired
  private AdminController adminController; 
  
 /**
  * It gets all the active lenders.
  */
  @Test
  public void getActiveLendersTest() {
    List<AdminViewLendersDTO> lst = new ArrayList<AdminViewLendersDTO>();
    lst.add(adminViewLendersDTO);
    when(adminService.getActiveLenders()).thenReturn(lst);
    assertEquals(new ResponseEntity<>(lst, HttpStatus.OK),adminController.getActiveLenders());
  }
  /**
   * It gets all the inactive lenders.
   */
  @Test
  public void getInactiveLendersTest() {
    List<AdminViewLendersDTO> lst = new ArrayList<AdminViewLendersDTO>();
    lst.add(adminViewLendersDTO);
    when(adminService.getInactiveLenders()).thenReturn(lst);
    assertEquals(new ResponseEntity<>(lst, HttpStatus.OK),adminController.getInactiveLenders());
  }
  @Test
  public void deactiveLenderTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender has been deactivated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    when(adminService.deactivateLender(lenderId)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK),adminController.deactiveLender(stringLenderId));
  }
  
  @Test
  public void activeLenderTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender has been activated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    when(adminService.activateLender(lenderId)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK),adminController.activeLender(stringLenderId));
  }
  @Test
  public void getActiveBorrowersTest() {
    List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
    activeBorrowerLst.add(adminViewBorrowersDTO);
    when(adminService.getActiveBorrowers()).thenReturn(activeBorrowerLst);
    assertEquals(new ResponseEntity<>(activeBorrowerLst, HttpStatus.OK),adminController.getActiveBorrowers());
  }
  
  @Test
  public void getInactiveBorrowersTest() {
    List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
    activeBorrowerLst.add(adminViewBorrowersDTO);
    when(adminService.getInactiveBorrowers()).thenReturn(activeBorrowerLst);
    assertEquals(new ResponseEntity<>(activeBorrowerLst, HttpStatus.OK),adminController.getInactiveBorrowers());
  }
  @Test
  public void deactiveBorrowerTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Borrower has been deactivated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    when(adminService.deactivateBorrower(borrowerId)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK),adminController.deactiveBorrower(stringBorrowerId));
    
  }
  @Test
  public void activeBorrowerTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Borrower has been activated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    when(adminService.activateBorrower(borrowerId)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK),adminController.activeBorrower(stringBorrowerId));
    
  }
}
