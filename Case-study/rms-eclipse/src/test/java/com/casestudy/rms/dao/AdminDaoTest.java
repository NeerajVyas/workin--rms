package com.casestudy.rms.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.casestudy.rms.dao.impl.AdminDAOImpl;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminDaoTest {

  @Autowired
  private AdminDAOImpl adminDAO;

  @PersistenceContext
  private EntityManager entityManager;


  int lenderId = 2;
  String emailID="malviyaaman474@gmail.com";

  Lender lender = new Lender(lenderId, "harsh", "12345678", "harsh@gmail.com", "ROLE_LENDER", LocalDateTime.now(), LocalDateTime.now(), 1, 10,
      "hello", "10-20", "100-200", null);
  User user = new User(100, "MockUser", "12345678", emailID, "ROLE_LENDER",
      LocalDateTime.now(), LocalDateTime.now(), 1);
  
  @Test
  public void getLenderWithStatusTest() throws DAOException {
    List<Lender> lst = new ArrayList<Lender>();
    lst.add(lender);
    adminDAO.getLenderWithStatus(1);
  }
  
  
  @Test
  public void creditApplicationCountToLenderTest() {
    
    adminDAO.creditApplicationCountToLender(lenderId);
  }
  
  @Test
  public void financialAnalystCountToLenderTest() {
    
    adminDAO.financialAnalystCountToLender(lenderId);
  }
  
  @Test
  public void getBorrowerWithStatusTest() {
    List<User> lst = new ArrayList<>();
    lst.add(user);
    adminDAO.getBorrowerWithStatus(1);
  }
  
  @Test
  public void creditApplicationCountToBorrowerTest() {
    List<User> lst = new ArrayList<>();
    lst.add(user);
    adminDAO.creditApplicationCountToBorrower(1);
  }
  
  

}