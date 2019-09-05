package com.casestudy.rms.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.dao.impl.FinancialAnalystDAOImpl;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FinancialAnalystDAOTest {
  @Autowired
  private FinancialAnalystDAOImpl financialAnalystDAO;

  Lender lender = new Lender(1, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,
      "hello", "10-20", "100-200", null); 
  @Test
  public void viewFinancialAnalystTest() {
    lender.setUserId(2);
    lender.setTenureRange("100-200");
    FinancialAnalyst financialAnalyst = new FinancialAnalyst(1, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
        LocalDateTime.now(), LocalDateTime.now(), 1, lender);

    List<FinancialAnalyst> lst = new ArrayList<FinancialAnalyst>();
    lst.add(financialAnalyst);
    financialAnalystDAO.viewFinancialAnalyst();
  }
  
  @Test 
  public void financialAnalystExistTest() {
    FinancialAnalyst financialAnalyst = new FinancialAnalyst(1, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
        LocalDateTime.now(), LocalDateTime.now(), 1, lender);

    List<FinancialAnalyst> lst = new ArrayList<FinancialAnalyst>();
    lst.add(financialAnalyst);
    financialAnalystDAO.financialAnalystExist(financialAnalyst);
  }
  
//  @Test
//  public void fetchLenderIdTest() {
//    FinancialAnalyst financialAnalyst = new FinancialAnalyst(1, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
//        LocalDateTime.now(), LocalDateTime.now(), 1, lender);
//
//    List<FinancialAnalyst> lst = new ArrayList<FinancialAnalyst>();
//    lst.add(financialAnalyst);
//    financialAnalystDAO.fetchLenderId(1);
//  }
  
  
  
  
  
  

}
