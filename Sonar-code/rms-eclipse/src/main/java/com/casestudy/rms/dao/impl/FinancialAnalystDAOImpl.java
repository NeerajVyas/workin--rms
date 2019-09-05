package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.casestudy.rms.dao.FinancialAnalystDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Financial Analyst DAO. */

@Repository
public class FinancialAnalystDAOImpl implements FinancialAnalystDAO {

	public static final Logger LOGGER = Logger.getLogger(FinancialAnalystDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean financialAnalystExist(FinancialAnalyst financialAnalyst) {

		LOGGER.info("FinancialAnalystDAOImpl :: financialAnalystExist");
		String hql = "FROM User WHERE userEmail = ?1";
		try {
			int count = entityManager.createQuery(hql).setParameter(1, financialAnalyst.getUserEmail()).getResultList()
					.size();
			return count > 0;
		}catch (Exception exception) {
			LOGGER.error("FinancialAnalystDAOImpl :: financialAnalystExist :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinancialAnalyst> viewFinancialAnalyst() {
		LOGGER.info("FinancialAnalystDAOImpl :: viewFinancialAnalyst");
		String hql = "FROM FinancialAnalyst ORDER BY modificationDate desc";
		try {
			return entityManager.createQuery(hql).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("FinancialAnalystDAOImpl :: viewFinancialAnalyst :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("FinancialAnalystDAOImpl :: viewFinancialAnalyst :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public int fetchLenderId(int faid) {
		LOGGER.info("FinancialAnalystDAOImpl :: fetchLenderId");
		String hql = "from FinancialAnalyst  as fa where fa.userId=?1";
		try {
			FinancialAnalyst fa = (FinancialAnalyst) entityManager.createQuery(hql).setParameter(1, faid)
					.getResultList().get(0);
			return fa.getLender().getUserId();
		}catch (Exception exception) {
			LOGGER.error("FinancialAnalystDAOImpl :: fetchLenderId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Transactional
	@Override
	public void updateFinancialAnalystStatus(int faId, int status) {
		LOGGER.info("FinancialAnalystDAOImpl :: updateFinancialAnalystStatus");
		try {
			FinancialAnalyst financialAnalyst = entityManager.find(FinancialAnalyst.class, faId);
			financialAnalyst.setUserAIStatus(status);
			entityManager.flush();
		}catch (Exception exception) {
			LOGGER.error("FinancialAnalystDAOImpl :: updateFinancialAnalystStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

}