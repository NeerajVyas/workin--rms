package com.casestudy.rms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.dao.impl.FinancialAnalystDAOImpl;
import com.casestudy.rms.dao.impl.MappingCreditAppPolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDetailDAOImpl;
import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.FinancialAnalystReceivedCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystTopBorrowerDTO;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.impl.FinancialAnalystServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;

/** The Class FinancialAnalystServiceTest. */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialAnalystServiceTest {

  /** The credit app DAO. */
  @MockBean
  private CreditAppDAOImpl creditAppDAO;

  /** The user DAO. */
  @MockBean
  private UserDAOImpl userDAO;
  @MockBean
  private PolicyDetailDAOImpl policyDetailDAO;
  @MockBean
  private MappingCreditAppPolicyDAOImpl mappingCreditAppPolicyDAO;
  @MockBean
  private PolicyDAOImpl policyDAO;

  /** The financial analyst DAO. */
  @MockBean
  private FinancialAnalystDAOImpl financialAnalystDAO;

  /** The financial analyst service. */
  @Autowired
  private FinancialAnalystServiceImpl financialAnalystService;

  int faid = 3;

  /** The lender. */
  Lender lender = new Lender();
  PolicyDetail policyDetail = new PolicyDetail(10, 100, 2, "10000", 50000, 1);
  Policy policy = new Policy(1, "Threshold", 2, LocalDateTime.now(), LocalDateTime.now());

  /** The CreditApplication constructor used to initialize fields present in class CreditApplication. */
  CreditApplication creditApplication = new CreditApplication(100, 50, 1, "CompanyMock", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);

  /** The FinancialAnalystReceivedCreditAppDTO constructor used to initialize fields present in class FinancialAnalystReceivedCreditAppDTO. */
  FinancialAnalystReceivedCreditAppDTO financialAnalystReceivedCreditAppDTO = new FinancialAnalystReceivedCreditAppDTO("APP5", "MockBorrower",
      "mock@gmail.com", "MockCompany", 50);
  /** The financial analyst. */
  FinancialAnalyst financialAnalyst = new FinancialAnalyst(1, "FA_Mock", "12345678", "mockFA@gmail.com", "ROLE_FINANCIAL_ANALYST",
      LocalDateTime.now(), LocalDateTime.now(), 1, lender);

  MappingCreditAppPolicy mappingCreditAppPolicy = new MappingCreditAppPolicy(4, 5, 100, "10000");

  /** These method tests fetchCreditApp method present in FinancialAnalystService. It checks fetchCredtApp method is correctly giving required output
   * or not. */ 
  @Test
  @WithMockUser(authorities = "ROLE_FINANCIAL_ANALYST")
  public void fetchCreditAppTest() {
    int faid = 1;
    int i = 0;
    String emailId = "test@gmail.com";
    List<CreditApplication> list = new ArrayList<CreditApplication>();
    list.add(creditApplication);

    when(creditAppDAO.getCreditApplication(faid, ApplicationConstant.CREDIT_APP_HOLD))
        .thenReturn(Stream.of(creditApplication).collect(Collectors.toList()));
    List<CreditApplication> result = creditAppDAO.getCreditApplication(faid, ApplicationConstant.CREDIT_APP_HOLD);
    List<FinancialAnalystReceivedCreditAppDTO> listFAReceivedCreditAppDTO = new ArrayList<FinancialAnalystReceivedCreditAppDTO>();
    listFAReceivedCreditAppDTO.add(financialAnalystReceivedCreditAppDTO);
    when(userDAO.getUserNamebyUserID(result.get(i).getBorrowerId())).thenReturn("CompanyMock");
    when(userDAO.getUserEmailbyUserID(result.get(i).getBorrowerId())).thenReturn(emailId);
   
    financialAnalystService.fetchCreditApp(faid);
  }

  /** These method tests getTopBorrower method present in FinancialAnalystService. It checks getTopBorrower method is correctly giving required output
   * or not. */
  @Test
  @WithMockUser(authorities = "ROLE_FINANCIAL_ANALYST")
  public void getTopBorrowerTest() {
    FinancialAnalystTopBorrowerDTO financialAnalystTopBorrowerDTO = new FinancialAnalystTopBorrowerDTO("MockBorrower", "mock@gmail.com",
        "MockCompany", 50);
    int i = 0;
    int faid = 1;
    Lender lender = new Lender();
    lender.setUserId(2);
    lender.setTenureRange("100-200");
    List<FinancialAnalyst> lst1 = new ArrayList<FinancialAnalyst>();
    lst1.add(financialAnalyst);
    List<FinancialAnalystTopBorrowerDTO> lst2 = new ArrayList<FinancialAnalystTopBorrowerDTO>();
    lst2.add(financialAnalystTopBorrowerDTO);
    when(financialAnalystDAO.viewFinancialAnalyst()).thenReturn(lst1);
    when(creditAppDAO.getTopBorrowerByLenderID(lender.getUserId())).thenReturn(Stream.of(creditApplication).collect(Collectors.toList()));
    when(userDAO.getUserNamebyUserID(lst1.get(i).getUserId())).thenReturn("CompanyMock");
    when(userDAO.getUserEmailbyUserID(lst1.get(i).getUserId())).thenReturn("mock@gmail.com");
    financialAnalystService.getTopBorrower(faid);
  }

  @Test
  @WithMockUser(authorities = "ROLE_FINANCIAL_ANALYST")
  public void fetchDetailsforFinancialAnalystTest() {
    int lenderId = 2;
    int appId = 5;
    String borrowerEmail = "mockBorrower@gmail.com";
    when(financialAnalystDAO.fetchLenderId(faid)).thenReturn(lenderId);

    List<MappingCreditAppPolicy> lst = new ArrayList<MappingCreditAppPolicy>();
    lst.add(mappingCreditAppPolicy);
    when(mappingCreditAppPolicyDAO.fetchPolicy(appId)).thenReturn(lst);

    List<PolicyDetail> lst2 = new ArrayList<PolicyDetail>();
    lst2.add(policyDetail);
    when(policyDetailDAO.viewpolicyDetail(lenderId)).thenReturn(lst2);

    when(userDAO.getUserEmailbyUserID(lenderId)).thenReturn("mock@gmail.com");
    when(creditAppDAO.getCreditApplicationByAppId(appId)).thenReturn(creditApplication);
    when(policyDAO.findPolicy(mappingCreditAppPolicy.getPolicyId())).thenReturn(policy);

    List<String> policyLst1 = new ArrayList<String>();
    List<String> policyLst2 = new ArrayList<String>();
    List<String> policyLst3 = new ArrayList<String>();
    List<String> policyLst4 = new ArrayList<String>();
    List<String> policyLst5 = new ArrayList<String>();

    policyLst1.add("1");
    policyLst2.add("10000");
    policyLst3.add("Threshold");
    policyLst4.add("50000");
    policyLst5.add("80");
    
    financialAnalystService.fetchDetailsforFinancialAnalyst(faid, appId, borrowerEmail);
  }
}
