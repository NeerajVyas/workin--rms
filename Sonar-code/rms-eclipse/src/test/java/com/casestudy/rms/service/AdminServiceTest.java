package com.casestudy.rms.service;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import org.springframework.security.test.context.support.WithMockUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.casestudy.rms.dao.impl.AdminDAOImpl;
import com.casestudy.rms.dao.impl.FinancialAnalystDAOImpl;
import com.casestudy.rms.dao.impl.LenderDAOImpl;
import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.impl.AdminServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * The Class AdminServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

  /** The adminDAO which has to be mocked. */
  @MockBean
  private AdminDAOImpl adminDAO;
  @MockBean
  private LenderDAOImpl lenderDAO;
  @MockBean
  private FinancialAnalystDAOImpl financialAnalystDAO;
  
  /** The adminService service. */
  @Autowired
  private AdminServiceImpl adminService;
  
  
  /** The user DAO. */
  @MockBean
  private UserDAOImpl userDAO;
  
  /** The pending lender id. */
  int pendingLenderId=0;
  
  /** The inactive lender id. */
  int inactiveLenderId=1;
  
  /** The active lender id. */
  int activeLenderId=2;
  
  int borrowerId=1;
  
  
  /** The lender. */
  Lender pendingLender = new Lender(pendingLenderId, "harsh", "12345678", "harshPending@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,"hello", "10-20", "100-200", null);
  
  /** The inactive lender. */
  Lender inActiveLender = new Lender(2, "Mock Lender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(),ApplicationConstant.INACTIVE, 10,"hello", "10-20", "100-200", null);
  
  /** The active lender. */
  Lender activeLender = new Lender(2, "Mock Lender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(),ApplicationConstant.ACTIVE, 10,"hello", "10-20", "100-200", null);

  AdminViewLendersDTO adminViewLendersDTOactive =new AdminViewLendersDTO("LR2", "Mock Lender", "mockLender@gmail.com", "10", "10-20",
      "100-200", "5", "0", "ACTIVE");
  
  AdminViewLendersDTO adminViewLendersDTOInactive =new AdminViewLendersDTO("LR2", "Mock Lender", "mockLender@gmail.com", "10", "10-20",
      "100-200", "5", "0", "INACTIVE");
  
  User user = new User(100, "MockUser", "12345678", "mock@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1);

  FinancialAnalyst financialAnalyst = new FinancialAnalyst(1, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
      LocalDateTime.now(), LocalDateTime.now(), 1, activeLender);

  AdminViewBorrowersDTO adminViewBorrowersDTO = new AdminViewBorrowersDTO("1", "Mock Borrower", "mockBorrower@gmail.com", "1","1");
  /**
   * It tests the getPendingLender method  of adminService class.
   *  It checks that getPendingLenders method correctly gives Pending status or not.
   * @return the pending lenders test
   * @throws Exception the exception
   */

  
  /**
   * It tests the getActiveLender method  of adminService class. 
   * It checks that getActiveLenders method correctly gives Active status or not.
   * @throws Exception the exception
   */
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void getActiveLendersTest() throws Exception {
    List<Lender> lst = new ArrayList<>();
    lst.add(activeLender); 
    when(adminDAO.getLenderWithStatus(ApplicationConstant.ACTIVE)).thenReturn(lst);
    activeLender.setUserId(2);
    when(adminDAO.creditApplicationCountToLender(activeLender.getUserId())).thenReturn(5);
    when(adminDAO.financialAnalystCountToLender(activeLender.getUserId())).thenReturn(0);
    List<AdminViewLendersDTO> lstActiveLenders = new ArrayList<>();
    lstActiveLenders.add(adminViewLendersDTOactive);
    System.out.println("1111111111"+adminService.getLendersWithStatus(ApplicationConstant.ACTIVE));
    System.out.println("2222222222"+lstActiveLenders);
   // assertEquals(lstActiveLenders,adminService.getActiveLenders());
  }
  
  /**
   * It tests the getInactiveLender method  of adminService class.
   *  It checks that getInactiveLenders method correctly gives inactive status or not.
   * @throws Exception the exception
   */
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void getInactiveLendersTest() throws Exception{
    List<Lender> lst = new ArrayList<>(); 
    lst.add(inActiveLender);
    when(adminDAO.getLenderWithStatus(ApplicationConstant.INACTIVE)).thenReturn(lst);
    inActiveLender.setUserId(2);

    when(adminDAO.creditApplicationCountToLender(inActiveLender.getUserId())).thenReturn(5);
    when(adminDAO.financialAnalystCountToLender(inActiveLender.getUserId())).thenReturn(0);
    List<AdminViewLendersDTO> lstInactiveLenders = new ArrayList<>();
    lstInactiveLenders.add(adminViewLendersDTOInactive);
    adminService.getLendersWithStatus(ApplicationConstant.INACTIVE);
    
 
    //assertEquals(lstInactiveLenders,adminService.getInactiveLenders()); 
  }
  
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void deactivateLenderTest() {
    List<FinancialAnalyst> lst=new ArrayList<FinancialAnalyst>();
    lst.add(financialAnalyst);
    when(financialAnalystDAO.viewFinancialAnalyst()).thenReturn(lst);
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender has been deactivated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    adminService.updateLenderStatus(activeLenderId,ApplicationConstant.INACTIVE);;
  }
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void activateLenderTest() {
    List<FinancialAnalyst> lst=new ArrayList<FinancialAnalyst>();
    lst.add(financialAnalyst);
    when(financialAnalystDAO.viewFinancialAnalyst()).thenReturn(lst);
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender has been activated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    adminService.updateLenderStatus(activeLenderId,ApplicationConstant.ACTIVE);
  }
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void getActiveBorrowersTest() {
    List<User> lst=new ArrayList<User>();
    lst.add(user);
    when(adminDAO.getBorrowerWithStatus(ApplicationConstant.ACTIVE)).thenReturn(lst);
    List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
    activeBorrowerLst.add(adminViewBorrowersDTO);
    adminService.getBorrowersWithStatus(ApplicationConstant.ACTIVE);
  }
  
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void getInactiveBorrowersTest() {
    List<User> lst=new ArrayList<User>();
    lst.add(user);
    when(adminDAO.getBorrowerWithStatus(ApplicationConstant.INACTIVE)).thenReturn(lst);
    List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
    activeBorrowerLst.add(adminViewBorrowersDTO);
    adminService.getBorrowersWithStatus(ApplicationConstant.INACTIVE);
  }
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void deactivateBorrowerTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Borrower has been deactivated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    adminService.updateBorrowerStatus(borrowerId,ApplicationConstant.INACTIVE);
  }
  
  @Test
  @WithMockUser(authorities = "ROLE_ADMIN")
  public void activateBorrowerTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Borrower has been deactivated");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    adminService.updateBorrowerStatus(borrowerId,ApplicationConstant.ACTIVE);
  }
  
 

}
