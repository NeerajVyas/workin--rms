package com.casestudy.rms.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.casestudy.rms.dao.AdminDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.User;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Administrator DAO. */

@Repository
public class AdminDAOImpl implements AdminDAO {

	public static final Logger LOGGER = Logger.getLogger(AdminDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Lender> getLenderWithStatus(int status) {
		LOGGER.info("AdminDAOImpl :: getLenderWithStatus");
		String hql = "FROM User where userAIStatus = ?1 AND userRole= ?2 ORDER BY modificationDate desc";
		try {
			return entityManager.createQuery(hql).setParameter(1, status).setParameter(2, "ROLE_LENDER")
					.getResultList();
		} catch (Exception exception) {
			LOGGER.error("AdminDAOImpl :: getLenderWithStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public int creditApplicationCountToLender(int lenderId) {
		LOGGER.info("AdminDAOImpl :: creditApplicationCountToLender  ");
		String hql = "FROM CreditApplication where lenderId=?1";
		try {
			return entityManager.createQuery(hql).setParameter(1, lenderId).getResultList().size();
		} catch (Exception exception) {
			LOGGER.error("AdminDAOImpl :: creditApplicationCountToLender :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public int financialAnalystCountToLender(int lenderId) {
		LOGGER.info("AdminDAOImpl :: financialAnalystCountToLender  ");
		int count = 0;
		String hql = "FROM FinancialAnalyst";
		try {
			List<FinancialAnalyst> financialAnalystsLst = entityManager.createQuery(hql).getResultList();
			for (int i = 0; i < financialAnalystsLst.size(); i++) {
				if (financialAnalystsLst.get(i).getLender().getUserId() == lenderId) {
					count++;
				}
			}
			return count;
		} catch (Exception exception) {
			LOGGER.error("AdminDAOImpl :: financialAnalystCountToLender :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getBorrowerWithStatus(int status) {
		LOGGER.info("AdminDAOImpl :: getBorrowerWithStatus  ");
		String hql = "FROM User where userAIStatus = ?1 AND userRole= ?2 ORDER BY modificationDate desc";
		try {
			return (List<User>) entityManager.createQuery(hql).setParameter(1, status).setParameter(2, "ROLE_BORROWER")
					.getResultList();
		} catch (Exception exception) {
			LOGGER.error("AdminDAOImpl :: getBorrowerWithStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public int creditApplicationCountToBorrower(int borrowerId) {
		LOGGER.info("AdminDAOImpl :: creditApplicationCountToBorrower  ");
		String hql = "FROM CreditApplication where borrowerId=?1";
		try {
			return entityManager.createQuery(hql).setParameter(1, borrowerId).getResultList().size();

		} catch (NoResultException noResultException) {
			LOGGER.error("AdminDAOImpl :: creditApplicationCountToBorrower :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException("AdminDAOImpl :: creditApplicationCountToBorrower "+ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("AdminDAOImpl :: creditApplicationCountToBorrower :: Exception");
			LOGGER.error("AdminDAOImpl :: creditApplicationCountToBorrower "+exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

}