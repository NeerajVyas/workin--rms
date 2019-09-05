package com.casestudy.rms.dao;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.model.CreditApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditAppDAOTest {
  @Autowired
  private CreditAppDAOImpl creditAppDAO;
  
  int faid=3;
  int lenderId=2;
  int borrowerId=1;
  
  CreditApplication creditApplication = new CreditApplication(100, 50, 1, "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);

  @Test
  public void getCreditApplicationTest() {
    List<CreditApplication> lst=new ArrayList<>();
    lst.add(creditApplication);
    creditAppDAO.getCreditApplication(faid, 1);
  }
  
  @Test
  public void getMaxApplicationIdIdTest() {
 
    creditAppDAO.getMaxApplicationIdId();
  }
  
//  @Test
//  public void assignCreditApplicationTest() {
// 
//    creditAppDAO.assignCreditApplication(creditApplication);
//  }
  
  @Test
  public void getTopBorrowerByLenderID() {
    List<CreditApplication> lst=new ArrayList<>();
    lst.add(creditApplication);
    creditAppDAO.getTopBorrowerByLenderID(lenderId);
  }
  
  @Test
  public void getCreditApplicationByBorrowerIdTest() {
    List<CreditApplication> lst=new ArrayList<>();
    lst.add(creditApplication);
    creditAppDAO.getCreditApplicationByBorrowerId(borrowerId);
  }
  
//  @Test
//  public void getCreditApplicationByAppIdTest() {
//    
//    creditAppDAO.getCreditApplicationByAppId(50);
//  }
  @Test
  public void viewPreviousUpdatedApplicationsTest() {
    List<CreditApplication> lst=new ArrayList<>();
    lst.add(creditApplication);
    creditAppDAO.viewPreviousUpdatedApplications(faid);
  }
}
