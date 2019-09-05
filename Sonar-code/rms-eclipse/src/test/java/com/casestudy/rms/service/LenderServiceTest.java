package com.casestudy.rms.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.dao.impl.FinancialAnalystDAOImpl;
import com.casestudy.rms.dao.impl.LenderDAOImpl;
import com.casestudy.rms.dao.impl.MappingCreditAppPolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDetailDAOImpl;
import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.FinancialAnalystResponse;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.PolicyValues;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.exception.UserInputInvalidException;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.impl.LenderServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LenderServiceTest {

  @Autowired
  private LenderServiceImpl lenderService;
  @MockBean
  private LenderDAOImpl lenderDAO;
  @MockBean
  private PolicyDAOImpl policyDAO;
  @MockBean
  private PolicyDetailDAOImpl policyDetailDAO;
  @MockBean
  private FinancialAnalystDAOImpl financialAnalystDAO;
  @MockBean
  private CreditAppDAOImpl creditApplicationDAO;
  @MockBean
  private UserDAOImpl userDAO;
  @MockBean
  private MappingCreditAppPolicyDAOImpl mappingCreditAppPolicyDAO;

  @PersistenceContext
  private EntityManager entityManager;
  
  int lenderId = 2;
  int FAId = 3;
  User user = new User(100, "MockUser", "12345678", "mock@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1);
  Lender lender = new Lender(lenderId, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,
      "hello", "10-20", "100-200", null);
  Policy policy = new Policy(101, "Threshold", 0, LocalDateTime.now(), LocalDateTime.now());
  PolicyDetail policyDetail = new PolicyDetail(101, 1, 2, "10000", 5000, 1);
  PolicyResponse policyResponses = new PolicyResponse(101, "Income", 10000, "5000", 1);
  FinancialAnalyst financialAnalysts = new FinancialAnalyst(FAId, "Amisha", "12345678", "amisha@gmail.com", "ROLE_FINANCIAL_ANALYST",
      LocalDateTime.now(), LocalDateTime.now(), 1, null);
  CreditApplication creditApplication = new CreditApplication(50, 50, 1, "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);

  
  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void getCreditApplicationTest() {
    int lenderId = 2;
    when(lenderDAO.getCreditApplication(lenderId)).thenReturn(
        (Stream.of(new CreditApplication(1, 50, 2, "TestImpetus", LocalDateTime.now(), LocalDateTime.now(), 1, 0, 2)).collect(Collectors.toList())));
    assertEquals(1, lenderService.getCreditApplication(lenderId).size());
  }

  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void viewPolicyTest() {
    int lenderId = 2;
    when(policyDAO.viewPolicy(lenderId))
        .thenReturn(Stream.of(new Policy(4100, "Total Assets", lenderId, LocalDateTime.now(), LocalDateTime.now())).collect(Collectors.toList()));
    when(policyDetailDAO.viewpolicyDetail(lenderId))
        .thenReturn(Stream.of(new PolicyDetail(0, 4100, lenderId, "10000", 5000, 1)).collect(Collectors.toList()));
    assertEquals(1, lenderService.viewPolicy(lenderId).size());
  }

  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void viewFinancialAnalystTest() {
    int FAId = 100;
    int userId = 2;
    Lender lender = new Lender();
    lender.setUserId(2);
    long count = 1;
    FinancialAnalyst financialAnalysts = new FinancialAnalyst(FAId, "Amisha", "12345678", "amisha@gmail.com", "ROLE_FINANCIAL_ANALYST",
        LocalDateTime.now(), LocalDateTime.now(), 1, lender);

    when(financialAnalystDAO.viewFinancialAnalyst()).thenReturn(Stream.of(financialAnalysts).collect(Collectors.toList()));
    when(lenderDAO.getPendingRequests(financialAnalysts.getUserId())).thenReturn(count);
    assertEquals(1, lenderService.viewFinancialAnalyst(userId).size());

  }

  @Test
  public void registerLenderTest() {
    int lenderId = 2;
    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
    fa.add(financialAnalysts);
    Lender lender = new Lender(120, "Mock Lender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
        "TestDescription", "20000-3000000", "100000-2000000", fa);
    PolicyDetail policyDetail = new PolicyDetail();
    
    List<Integer> policyId = new ArrayList<Integer>();
    policyId.add(101);
    when(lenderDAO.lenderExists(lender)).thenReturn(false);
    when(policyDAO.getDefaultPolicyId()).thenReturn(policyId);
    when(policyDetailDAO.addPolicyDetail(policyDetail)).thenReturn(true);
    when(lenderDAO.getMaxLenderId()).thenReturn(lenderId);
    ResponseModel response = new ResponseModel();
    response.setMessage("Lender registered successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    lender.setUserName("Mock Lender");
    lenderService.registerLender(lender);
  }

  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void addFinancialAnalystTest() {
    int lenderId = 2;
    
    FinancialAnalyst financialAnalyst = new FinancialAnalyst(3, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
        LocalDateTime.now(), LocalDateTime.now(), 1, lender);
    List<FinancialAnalyst> lstFA = new ArrayList<FinancialAnalyst>();
    lstFA.add(financialAnalyst);
    FinancialAnalystResponse financialAnalystResponse = new FinancialAnalystResponse("3", "mockFA@gmail.com", "MockFA", 2);
    List<FinancialAnalystResponse> lstFAResponse = new ArrayList<FinancialAnalystResponse>();
    lstFAResponse.add(financialAnalystResponse);
    Lender mockLenderRegistration = new Lender(lenderId, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(),
        LocalDateTime.now(), 1, 10, "hello", "10-20", "100-200", lstFA);
    when(lenderDAO.getLender(lenderId)).thenReturn(mockLenderRegistration);
    ResponseModel response = new ResponseModel();
    response.setMessage("Financial Analyst added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    lenderService.addFinancialAnalyst(financialAnalyst, mockLenderRegistration);
  }
  
//  @Test(expected=UserInputInvalidException.class)
//  @WithMockUser(authorities = "ROLE_LENDER")
//  public void registerLenderNegativeTest() throws UserInputInvalidException {
//    Lender lenderNegative=new Lender();
//    List<FinancialAnalyst> fa = new ArrayList<FinancialAnalyst>();
//    fa.add(financialAnalysts);
//    Lender lender = new Lender(120, "MockLender", "12345678", "mockLender@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 20,
//        "TestDescription", "20000-3000000", "100000-2000000", fa);  
//    PolicyDetail policyDetail = new PolicyDetail();
//    int lenderId = 2;
//    List<Integer> policyId = new ArrayList<Integer>();
//    policyId.add(101);
//    when(lenderDAO.lenderExists(lender)).thenReturn(true);
//    when(policyDAO.getDefaultPolicyId()).thenReturn(policyId);
//    when(policyDetailDAO.addPolicyDetail(policyDetail)).thenReturn(true);
//    when(lenderDAO.getMaxLenderId()).thenReturn(lenderId);
//    ResponseModel response = new ResponseModel();
//    response.setMessage("Lender registered successfully");
//    response.setStatus(HttpStatus.CREATED.value());
//    response.setTimestamp(LocalDateTime.now());
//    lenderNegative.setUserName(null);
//    when(lenderService.registerLender(lenderNegative)).thenThrow(new UserInputInvalidException("Name cannot be empty"));
//  }


  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void updatePolicyDetailTest() {
    int lenderId = 2;
    Lender lender = new Lender(lenderId, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,
        "hello", "10-20", "100-200", null);
    PolicyResponse policyResponse[] = new PolicyResponse[1];
    policyResponse[0] = policyResponses;
    policyResponses.setPolicyId(1);
    PolicyDetail policyDetailmock = new PolicyDetail(1, 1, 2, "10000", 5000, 1);
    when(lenderDAO.getLender(lenderId)).thenReturn(lender);
    Lender result = lenderDAO.getLender(lenderId);
    assertEquals(result, lender);
    when(policyDetailDAO.findPolicyDetail(policyResponses.getPolicyId(), lender)).thenReturn(policyDetailmock);
    when(policyDetailDAO.updatePolicyDetail(policyDetailmock)).thenReturn(true);
    ResponseModel response = new ResponseModel();
    response.setMessage("Policy added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    response.setTimestamp(LocalDateTime.now());
    lenderService.updatePolicyDetail(policyResponse, lender);
  }

  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void assignCreditApplicationTest() {

    CreditApplication creditApp = new CreditApplication(100, 50, 1, "CompanyMock", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);
    when(creditApplicationDAO.assignCreditApplication(creditApp)).thenReturn(true);
    boolean result = creditApplicationDAO.assignCreditApplication(creditApp);
    assertEquals(true, result);
    lenderService.assignCreditApplication(creditApp);
  }

  @Test
  @WithMockUser(authorities = "ROLE_BORROWER")
  public void getActiveLendersTest() {
    List<Lender> list = new ArrayList<Lender>();
    list.add(lender);
    when(lenderDAO.getLenderWithStatus(ApplicationConstant.ACTIVE)).thenReturn(Stream.of(lender).collect(Collectors.toList()));
    lenderService.getActiveLenders();
  }

  @Test 
  @WithMockUser(authorities = "ROLE_LENDER")
  public void addPolicyTest() {
    when(policyDAO.addPolicy(policy)).thenReturn(true);
    when(policyDetailDAO.addPolicyDetail(policyDetail)).thenReturn(false);
    ResponseModel response = new ResponseModel();
    response.setMessage("Policy added successfully");
    response.setStatus(HttpStatus.CREATED.value());
    lenderService.addPolicy(policyResponses, lender);
  }

  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void getLenderTest() {
    when(lenderDAO.getLender(lenderId)).thenReturn(lender);
    lenderService.getLender(lenderId);
  }
  @Test
  @WithMockUser(authorities = "ROLE_LENDER")
  public void viewCreditApplicationDetails() {
    int applicationId=50;
    PolicyValues policyValues = new PolicyValues(50, "70","Family Income");
    List<PolicyValues> lst = new ArrayList<>();
    lst.add(policyValues);
    MappingCreditAppPolicy mappingCreditAppPolicy = new MappingCreditAppPolicy(4, 5, 100, "10000");

    when(creditApplicationDAO.getCreditApplicationByAppId(applicationId)).thenReturn(creditApplication);
    when(userDAO.getUserNamebyUserID(creditApplication.getFinancialAnalystId())).thenReturn("MockCompany");
    List<MappingCreditAppPolicy> lst2 = new ArrayList<>();
    lst2.add(mappingCreditAppPolicy);
    
    when(mappingCreditAppPolicyDAO.fetchPolicy(applicationId)).thenReturn(lst2);
    when(policyDAO.findPolicy(lst2.get(0).getPolicyId())).thenReturn(policy);
    lenderService.viewCreditApplicationDetails(lenderId, applicationId);
  }
}
