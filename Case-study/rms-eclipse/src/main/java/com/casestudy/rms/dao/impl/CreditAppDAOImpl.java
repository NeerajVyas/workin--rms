package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.CreditAppDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Represents a Credit Application DAO.
 */
@Repository
public class CreditAppDAOImpl implements CreditAppDAO {

	public static final Logger LOGGER = Logger.getLogger(CreditAppDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void submitCreditAppForm(CreditApplication creditApp) {
		LOGGER.info("CreditAppDAOImpl :: submitCreditAppForm  ");
		try {
			entityManager.persist(creditApp);
			entityManager.flush();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("CreditAppDAOImpl :: submitCreditAppForm :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditApplication> getCreditApplication(int faid, int status) {
		LOGGER.info("CreditAppDAOImpl :: getCreditApplication  ");
		String hql = "FROM CreditApplication WHERE financialAnalystId =?1 and applicationStatus=?2 ORDER BY modificationDate desc";
		try {
			return (List<CreditApplication>) entityManager.createQuery(hql).setParameter(1, faid)
					.setParameter(2, status).getResultList();
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: getCreditApplication :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public int getMaxApplicationIdId() {
		LOGGER.info("CreditAppDAOImpl :: getMaxApplicationIdId  ");
		String hql = "SELECT MAX(applicationId) FROM CreditApplication";
		try {
			return (int) entityManager.createQuery(hql).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: getMaxApplicationIdId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Transactional
	@Override
	public boolean assignCreditApplication(CreditApplication creditApp) {
		LOGGER.info("CreditAppDAOImpl :: assignCreditApplication  ");
		try {
			CreditApplication ca = entityManager.find(CreditApplication.class, creditApp.getApplicationId());
			ca.setFinancialAnalystId(creditApp.getFinancialAnalystId());
			ca.setApplicationStatus(creditApp.getApplicationStatus());
			entityManager.flush();
			return true;
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: assignCreditApplication :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();

		}
	}

	@Transactional
	@Override
	public void updateCreditScore(int applicationId, int creditScore, int applicationStatus) {
		LOGGER.info("CreditAppDAOImpl :: updateCreditScore  ");
		try {
			CreditApplication creditApplication = entityManager.find(CreditApplication.class, applicationId);
			creditApplication.setCreditScore(creditScore);
			creditApplication.setApplicationStatus(applicationStatus);
			entityManager.flush();
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: updateCreditScore :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditApplication> getTopBorrowerByLenderID(int lenderId) {
		LOGGER.info("CreditAppDAOImpl :: getTopBorrowerByLenderID  ");
		String hql = "FROM CreditApplication where lenderId=?1 AND applicationStatus=?2 ORDER BY creditScore DESC";
		try {
			return (List<CreditApplication>) entityManager.createQuery(hql)
					.setMaxResults(ApplicationConstant.TOP_CREDIT_SCORE_TABLE_LENGTH).setParameter(1, lenderId)
					.setParameter(2, ApplicationConstant.STATUS_APPROVED).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("CreditAppDAOImpl :: getTopBorrowerByLenderID :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: getTopBorrowerByLenderID :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditApplication> getCreditApplicationByBorrowerId(int borrowerId) {
		LOGGER.info("CreditAppDAOImpl :: getCreditApplicationByBorrowerId  ");
		String hql = "FROM CreditApplication WHERE borrowerId=?1 ORDER BY modificationDate desc";
		try {
			return (List<CreditApplication>) entityManager.createQuery(hql).setParameter(1, borrowerId).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("CreditAppDAOImpl :: getCreditApplicationByBorrowerId :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: getCreditApplicationByBorrowerId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public CreditApplication getCreditApplicationByAppId(int appId) {
		LOGGER.info("CreditAppDAOImpl :: getCreditApplicationByAppId  ");
		String hql = "FROM CreditApplication WHERE applicationId=?1";
		try {
			return (CreditApplication) entityManager.createQuery(hql).setParameter(1, appId).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: getCreditApplicationByAppId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Transactional
	@Override
	public void updateCreditApplication(int applicationId, int applicationStatus) {
		LOGGER.info("CreditAppDAOImpl :: updateCreditApplication  ");
		try {
			CreditApplication creditApplication = entityManager.find(CreditApplication.class, applicationId);
			creditApplication.setApplicationStatus(applicationStatus);
			entityManager.flush();
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: updateCreditApplication :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditApplication> viewPreviousUpdatedApplications(int faId) {
		LOGGER.info("CreditAppDAOImpl :: viewPreviousUpdatedApplications  ");
		String hql = "FROM CreditApplication WHERE financialAnalystId=?1 AND applicationStatus!=?2 ORDER BY modificationDate desc";
		try {
			return (List<CreditApplication>) entityManager.createQuery(hql).setParameter(1, faId)
					.setParameter(2, ApplicationConstant.STATUS_ASSIGNED).getResultList();
		} catch (Exception exception) {
			LOGGER.error("CreditAppDAOImpl :: viewPreviousUpdatedApplications :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

}
