package com.casestudy.rms.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.casestudy.rms.controller.FinancialAnalystController;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystReceivedCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystTopBorrowerDTO;
import com.casestudy.rms.dto.FinancialAnalystViewDetailsDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.dto.UpdatedCreditApplicationStatus;
import com.casestudy.rms.service.impl.CreditAppServiceImpl;
import com.casestudy.rms.service.impl.FinancialAnalystServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialAnalystControllerTest {
  /**
   * Financial Analyst service.
   */
  @MockBean
  private FinancialAnalystServiceImpl financialAnalystService;
  /**
   * Finacial Analyst controller.
   */
  @Autowired
  private FinancialAnalystController financialAnalystController;
  @MockBean
  private CreditAppServiceImpl creditAppService ;
  
  String borrowerEmail="mockBorrower@gmail.com";
  String lenderEmail="mockLender@gmail.com";
  int faId=3;
  /**
   * constructor for financial analyst recieved dto
   */
  FinancialAnalystReceivedCreditAppDTO financialAnalystReceivedCreditAppDTO = new FinancialAnalystReceivedCreditAppDTO("80", "Mock Borrower",
      "mockBorrower@gmail.com", "Mock Company", 50);
  FinancialAnalystTopBorrowerDTO financialAnalystTopBorrowerDTO = new FinancialAnalystTopBorrowerDTO("Mock FA", "mockFA@gmail.com", "Mock Company",
      50);
  UpdatedCreditApplicationStatus updatedCreditApplicationStatus=new UpdatedCreditApplicationStatus("1", 50,borrowerEmail, lenderEmail) ;
  BorrowerAllCreditAppDTO borrowerAllCreditAppDTO=new BorrowerAllCreditAppDTO("100","50", "MockCompany", "25/06/2019", "1", "MockLender");

  @Test
  public void viewCreditApplicationsTest() {
    int faid = 3;
    List<FinancialAnalystReceivedCreditAppDTO> listFAReceivedCreditAppDTO = new ArrayList<FinancialAnalystReceivedCreditAppDTO>();
    listFAReceivedCreditAppDTO.add(financialAnalystReceivedCreditAppDTO);
    when(financialAnalystService.fetchCreditApp(faid)).thenReturn(listFAReceivedCreditAppDTO);
    assertEquals(new ResponseEntity<List<FinancialAnalystReceivedCreditAppDTO>>(listFAReceivedCreditAppDTO, HttpStatus.OK),
        financialAnalystController.viewCreditApplications(faid));
  }

  @Test
  public void getTopBorrowersTest() {
    int faid = 3;
    List<FinancialAnalystTopBorrowerDTO> topBorrowerLst = new ArrayList<FinancialAnalystTopBorrowerDTO>();
    topBorrowerLst.add(financialAnalystTopBorrowerDTO);
    when(financialAnalystService.getTopBorrower(faid)).thenReturn(topBorrowerLst);
    assertEquals(new ResponseEntity<List<FinancialAnalystTopBorrowerDTO>>(topBorrowerLst, HttpStatus.OK),
        financialAnalystController.getTopBorrowers(faid));
  }
  
  @Test
  @WithMockUser(authorities="ROLE_FINANCIAL_ANALYST")
  public void viewMoreDetailsTest() {
    String applicationId="APP5";
    List<FinancialAnalystReceivedCreditAppDTO> listFAReceivedCreditAppDTO = new ArrayList<FinancialAnalystReceivedCreditAppDTO>();
    listFAReceivedCreditAppDTO.add(financialAnalystReceivedCreditAppDTO);
    List<String> policyLst1= new ArrayList<String>();
    List<String> policyLst2= new ArrayList<String>();
    List<String> policyLst3= new ArrayList<String>();
    List<String> policyLst4= new ArrayList<String>();
    List<String> policyLst5= new ArrayList<String>();
    
    policyLst1.add("1");
    policyLst2.add("10000");
    policyLst3.add("Threshold");
    policyLst4.add("50000");
    policyLst5.add("80");

    FinancialAnalystViewDetailsDTO financialAnalystViewDetailsDTO = new FinancialAnalystViewDetailsDTO("5", "1","50","LR2",borrowerEmail, "mockBorrower@gmail.com",
        policyLst1, policyLst2, policyLst3,policyLst4, policyLst5); 
    when(financialAnalystService.fetchDetailsforFinancialAnalyst(faId, 5, borrowerEmail)).thenReturn(financialAnalystViewDetailsDTO);
    financialAnalystController.viewMoreDetails(faId, applicationId, borrowerEmail);
  }
  @Test 
  @WithMockUser(authorities="ROLE_FINANCIAL_ANALYST")
  public void updateCreditApplicationStatusTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Credit Application Updated Successfully");
      response.setStatus(HttpStatus.OK.value());
      response.setTimestamp(LocalDateTime.now());
    when(creditAppService.updateCreditApplicationStatus(updatedCreditApplicationStatus)).thenReturn(response);
    financialAnalystController.updateCreditApplicationStatus(updatedCreditApplicationStatus, faId);
  }
  
  @Test
  public void viewPreviousUpdatedApplicationTest() {
    List<BorrowerAllCreditAppDTO> previousCreditAppLst = new ArrayList<>();
    previousCreditAppLst.add(borrowerAllCreditAppDTO);
    when(creditAppService.viewPreviousUpdatedApplication(faId)).thenReturn(previousCreditAppLst);
    assertEquals(new ResponseEntity<>(previousCreditAppLst,HttpStatus.OK),financialAnalystController.viewPreviousUpdatedApplication(faId));
  }
  
  
}
