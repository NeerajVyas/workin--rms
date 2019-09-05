package com.casestudy.rms.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.controller.LenderController;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.BorrowerViewLenderDTO;
import com.casestudy.rms.dto.FinancialAnalystResponse;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.service.impl.LenderServiceImpl;
import com.casestudy.rms.service.impl.PolicyServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LenderControllerTest {
/**
 * lender service which has to be mocked.
 */
  @MockBean
  private LenderServiceImpl lenderService;
  /**
   * policy service which has to be mocked.
   */
  @MockBean
  private PolicyServiceImpl policyService;
  /**
   * lender controller.
   */
  @Autowired
  private LenderController lenderController;

  int FAId = 3;
  int lenderId = 2;
  /**
   * constructor for financial analyst.
   */
  FinancialAnalyst financialAnalysts = new FinancialAnalyst(FAId, "Amisha", "12345678", "amisha@gmail.com", "ROLE_FINANCIAL_ANALYST",
      LocalDateTime.now(), LocalDateTime.now(), 1, null);
  /**
   * constructor for policy response.
   */
  PolicyResponse policyResponses = new PolicyResponse(0, "Income", 10000, "5000", 1);
  /**
   * constructor for financial analyst reponse.
   */
  FinancialAnalystResponse financialAnalystResponse = new FinancialAnalystResponse("3", "mockFA@gmail.com", "MockFA", 2);
  /**
   * constructor for policy model.
   */
  Policy policy = new Policy(101, "Threshold", 0, LocalDateTime.now(), LocalDateTime.now());
  /**
   * constructor for credit application.
   */
  CreditApplication creditApplication = new CreditApplication(100, 50, 1, "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);
  
  BorrowerAllCreditAppDTO borrowerAllCreditAppDTO = new BorrowerAllCreditAppDTO("5", "70", "Company Mock", "20/07/2019", "1",
      "Mock Lender");
  
  BorrowerViewLenderDTO borrowerViewLenderDTO = new BorrowerViewLenderDTO(1, "Mock Borrower", "mockBorrower@gmail.com", "Mock Description", "100-200", "100000-200000",
      20);
/**
 * It will test registration of a lender.
 */
  @Test
  public void registerLenderTest() {

    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender registered successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(lenderService.registerLender(lender)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.CREATED), lenderController.registerLender(lender));
  }
/**
 * It will test registration of financial analyst.
 */
  @Test
  public void addFinancialAnalystTest() {
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);

    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    when(lenderService.getLender(lenderId)).thenReturn(lender);
    ResponseModel response = new ResponseModel();
    response.setMessage("Financial Analyst added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(lenderService.addFinancialAnalyst(financialAnalysts, lender)).thenReturn(response);

    assertEquals(new ResponseEntity<>(response, HttpStatus.CREATED), lenderController.addFinancialAnalyst(financialAnalysts, lenderId));

  }
/**
 * It will add policy.
 */
  @Test
  public void addPolicyTest() {
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    when(lenderService.getLender(lenderId)).thenReturn(lender);
    ResponseModel response = new ResponseModel();
    response.setMessage("Policy added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(lenderService.addPolicy(policyResponses, lender)).thenReturn(response);

    assertEquals(new ResponseEntity<>(response, HttpStatus.CREATED), lenderController.addPolicy(policyResponses, lenderId));
  }
/**
 * It displays all policies of particluar lender.
 */
  @Test
  public void viewPolicyTest() {

    List<PolicyResponse> lst = new ArrayList<PolicyResponse>();
    lst.add(policyResponses);
    when(lenderService.viewPolicy(lenderId)).thenReturn(lst);
    assertEquals(new ResponseEntity<>(lst, HttpStatus.OK), lenderController.viewPolicy(lenderId));
  }
/**
 * It will update policy details .
 */
  @Test
  public void updatePolicyDetailsTest() {
    PolicyResponse policyResponse[] = new PolicyResponse[1];
    policyResponse[0] = policyResponses;
    policyResponses.setPolicyId(1);
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    when(lenderService.getLender(lenderId)).thenReturn(lender);
    ResponseModel response = new ResponseModel();
    response.setMessage("Policy added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    when(lenderService.updatePolicyDetail(policyResponse, lender)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK), lenderController.updatePolicyDetails(policyResponse, lenderId));
  }
/**
 * It will display all the financial analyst of lender.
 */
  @Test
  public void viewFinancialAnalystTest() {
    List<FinancialAnalystResponse> lst = new ArrayList<FinancialAnalystResponse>();
    lst.add(financialAnalystResponse);
    when(lenderService.viewFinancialAnalyst(lenderId)).thenReturn(lst);
    assertEquals(new ResponseEntity<List<FinancialAnalystResponse>>(lst, HttpStatus.OK), lenderController.viewFinancialAnalyst(lenderId));
  }
/**
 * It will return all the active lenders.
 */
  @Test
  public void getActiveLendersTest() {
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    List<BorrowerViewLenderDTO> lst = new ArrayList<BorrowerViewLenderDTO>();
    lst.add(borrowerViewLenderDTO);
    when(lenderService.getActiveLenders()).thenReturn(lst);
    assertEquals(new ResponseEntity<>(lst,HttpStatus.OK), lenderController.getActiveLenders());
  }
/**
 * It will display all policies of lender.
 */
  @Test
  public void getPoliciesTest() {
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    List<Policy> lst = new ArrayList<Policy>();
    when(policyService.getPolicies(lender.getUserId())).thenReturn(lst);
    assertEquals(new ResponseEntity<List<Policy>>(lst, HttpStatus.OK), lenderController.getPolicies(lender));
  }
/**
 * It will display all the credit applications received by lender.
 */
  @Test
  public void getCreditApplication() {
    List<BorrowerAllCreditAppDTO> lst = new ArrayList<BorrowerAllCreditAppDTO>();
    lst.add(borrowerAllCreditAppDTO);
    when(lenderService.getCreditApplication(lenderId)).thenReturn(lst);
    assertEquals(new ResponseEntity<List<BorrowerAllCreditAppDTO>>(lst, HttpStatus.OK), lenderController.getCreditApplication(lenderId));
  }
/**
 * It will assign particular credit application to one of the financial analyst of lender
 */
  @Test
  public void assignCreditApplicationTest() {
    ResponseModel response = new ResponseModel();
    response.setMessage("Assigned Credit Application to Financial Analyst successfully");
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    when(lenderService.assignCreditApplication(creditApplication)).thenReturn(response);
    assertEquals(new ResponseEntity<>(response, HttpStatus.OK), lenderController.assignCreditApplication(creditApplication, lenderId));
  }
}
