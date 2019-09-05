package com.casestudy.rms.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.dao.impl.PolicyDAOImpl;
import com.casestudy.rms.dao.impl.PolicyDetailDAOImpl;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.impl.PolicyServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyServiceTest {
  
  PolicyDetail policyDetail = new PolicyDetail(1, 1, 2, "10000", 5000, 1);
  Policy policy = new Policy(1, "Threshold", 2, LocalDateTime.now(), LocalDateTime.now());


  
  @MockBean
  private PolicyDAOImpl policyDAO;
  @MockBean
  private PolicyDetailDAOImpl policyDetailDAO;
  @Autowired
  private PolicyServiceImpl policyService;
  @Test
  @WithMockUser(authorities = "ROLE_BORROWER")
  public void getPoliciesTest() {
    int userID = 2;
    List<Policy> lst=new ArrayList<Policy>();
    lst.add(policy);
    Lender lender = new Lender();
    lender.setUserId(2);
    
    System.out.println(lst);
    System.out.println(policyDAO.getPolicy(userID));
    
    when(policyDAO.getPolicy(userID)).thenReturn(lst); 
    when(policyDetailDAO.findPolicyByID(policy.getPolicyId(), userID)).thenReturn(policyDetail);
    assertEquals(1,policyService.getPolicies(lender.getUserId()).size());
  }

}
 