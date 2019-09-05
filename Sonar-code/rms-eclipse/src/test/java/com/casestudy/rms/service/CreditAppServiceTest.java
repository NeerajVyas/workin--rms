package com.casestudy.rms.service;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.dao.impl.MappingCreditAppPolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDetailDAOImpl;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.dto.UpdatedCreditApplicationStatus;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.impl.CreditAppServiceImpl;

/**
 * These Class test the methods of CreditAppService service.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditAppServiceTest {
  
  /** The policy detail DAO. */
  @MockBean
  private PolicyDetailDAOImpl policyDetailDAO;
  
  /** The credit app DAO. */
  @MockBean
  private CreditAppDAOImpl creditAppDAO;
  
  /** The mapping credit app policy DAO. */
  @MockBean
  private MappingCreditAppPolicyDAOImpl mappingCreditAppPolicyDAO;
  
  /** The credit app service. */
  @Autowired
  private CreditAppServiceImpl creditAppService;
  
  int faId=1;

  /** The policy detail. */
  PolicyDetail policyDetail = new PolicyDetail(1, 1, 2, "10000", 5000, 1);
  
  /** The credit application. */
  CreditApplication creditApplication = new CreditApplication(100, 50, 1, "MockCompany", LocalDateTime.now(), LocalDateTime.now(), 1, 3, 2);
  
  /** The credit policy mapping DTO. */
  CreditPolicyMappingDTO creditPolicyMappingDTO = new CreditPolicyMappingDTO();

  BorrowerAllCreditAppDTO borrowerAllCreditAppDTO=new BorrowerAllCreditAppDTO("100","50", "MockCompany", "25/06/2019", "1", "MockLender");
  
  UpdatedCreditApplicationStatus updatedCreditApplicationStatus = new UpdatedCreditApplicationStatus("1", 100, "mock@gmail.com", "mockLender@gmail.com");
  
  /**
   * These method tests the submitCreditAppForm method present in CreditApp service. It checks submitCreditAppForm gives accurate result or not.
   */
  @Test
  @WithMockUser(authorities = "ROLE_BORROWER")
  public void submitCreditAppFormTest() {
    int i = 0;
    int applicationId = 100;
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

    Policy policyTest = new Policy();
    policyTest.setPolicyId(1);
    policyTest.setAddedBy(2);

    when(policyDetailDAO.findPolicyByID(Integer.parseInt(creditPolicyMappingDTO.getPolicyValues().get(i).get("policyId")),
    creditPolicyMappingDTO.getLenderId())).thenReturn(policyDetail);
    when(creditAppDAO.getMaxApplicationIdId()).thenReturn(applicationId);
    PolicyDetail result = policyDetailDAO.findPolicyByID(Integer.parseInt(creditPolicyMappingDTO.getPolicyValues().get(i).get("policyId")),
    creditPolicyMappingDTO.getLenderId());
    assertEquals(result, policyDetail);
    creditAppService.submitCreditAppForm(creditPolicyMappingDTO, borrowerId);
  }

//  /**
//   * These method tests getCreditAppRecords method present in CreditApp service and checks it successfully tests getCreditAppRecords or not. 
//   *
//   */
//  @Test
//  @WithMockUser(authorities = "ROLE_BORROWER")
//  public void getCreditAppRecordsTest() {
//    int borrowerId = 1;
//
//    when(creditAppDAO.getCreditApplicationByBorrowerId(borrowerId)).thenReturn(Stream.of(creditApplication).collect(Collectors.toList()));
//    List<CreditApplication> lst = new ArrayList<CreditApplication>();
//    lst.add(creditApplication);
//    creditAppService.getCreditAppRecords(borrowerId);
//  }
  @Test
  @WithMockUser(authorities = "ROLE_FINANCIAL_ANALYST")
  public void updateCreditApplicationStatusTest(){
    ResponseModel response = new ResponseModel();
    response.setMessage("Credit Application Updated Successfully");
      response.setStatus(HttpStatus.OK.value());
      response.setTimestamp(LocalDateTime.now());
      creditAppService.updateCreditApplicationStatus(updatedCreditApplicationStatus);
  } 

  @Test
  @WithMockUser(authorities = "ROLE_FINANCIAL_ANALYST")
  public void viewPreviousUpdatedApplicationTest(){
    List<CreditApplication> lst = new ArrayList<CreditApplication>();
    lst.add(creditApplication);
    when(creditAppDAO.viewPreviousUpdatedApplications(faId)).thenReturn(lst);
    List<BorrowerAllCreditAppDTO> previousCreditAppLst = new ArrayList<>();
    previousCreditAppLst.add(borrowerAllCreditAppDTO);
    creditAppService.viewPreviousUpdatedApplication(faId);
  }

}
