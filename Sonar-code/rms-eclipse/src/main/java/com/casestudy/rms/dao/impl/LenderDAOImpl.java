package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.LenderDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Lender DAO. */

@Repository
public class LenderDAOImpl implements LenderDAO {

	public static final Logger LOGGER = Logger.getLogger(LenderDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void registerLender(Lender lender) {
		LOGGER.info("LenderDAOImpl :: registerLender ");
		try {
			entityManager.persist(lender);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("LenderDAOImpl :: registerLender :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@Override
	public boolean lenderExists(Lender lender) {
		LOGGER.info("LenderDAOImpl :: lenderExists ");
		String hql = "FROM User WHERE userEmail = ?1";
		try {
			int count = entityManager.createQuery(hql).setParameter(1, lender.getUserEmail()).getResultList().size();
			return count > 0;
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: lenderExists :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Transactional
	@Override
	public void addFinancialAnalyst(FinancialAnalyst financialAnalyst) {
		LOGGER.info("LenderDAOImpl :: addFinancialAnalyst ");
		try {
			entityManager.persist(financialAnalyst);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("LenderDAOImpl :: addFinancialAnalyst :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@Override
	public Lender getLender(int lenderId) {
		LOGGER.info("LenderDAOImpl :: getLender ");
		try {
			return entityManager.find(Lender.class, lenderId);
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: getLender :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public int getMaxLenderId() {
		LOGGER.info("LenderDAOImpl :: getMaxLenderId ");
		String hql = "SELECT MAX(userId) FROM Lender";
		try {
			return (int) entityManager.createQuery(hql).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: getMaxLenderId :: Exception");
			LOGGER.error("LenderDAOImpl :: getMaxLenderId "+exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lender> getLenderWithStatus(int status) {
		LOGGER.info("LenderDAOImpl :: getLenderWithStatus ");
		String hql = "FROM User where userAIStatus = ?1 AND userRole= ?2";
		try {
			return (List<Lender>) entityManager.createQuery(hql).setParameter(1, status).setParameter(2, "ROLE_LENDER")
					.getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("LenderDAOImpl :: getLenderWithStatus :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: getLenderWithStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditApplication> getCreditApplication(int lenderId) {
		LOGGER.info("LenderDAOImpl :: getCreditApplication ");
		String hql = "FROM CreditApplication where lenderId=?1 ORDER BY modificationDate desc";
		try {
			return (List<CreditApplication>) entityManager.createQuery(hql).setParameter(1, lenderId).getResultList();
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: getCreditApplication :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public long getPendingRequests(int financialAnalystId) {
		LOGGER.info("LenderDAOImpl :: getPendingRequests ");
		String hql = "SELECT count(applicationStatus) FROM CreditApplication where financialAnalystId=?1 AND applicationStatus=?2";
		try {
			return (long) entityManager.createQuery(hql).setParameter(1, financialAnalystId)
					.setParameter(2, ApplicationConstant.STATUS_ASSIGNED).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: getPendingRequests :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Transactional
	@Override
	public void updateLenderStatus(int lenderId, int status) {
		LOGGER.info("LenderDAOImpl :: updateLenderStatus ");
		try {
			Lender lender = entityManager.find(Lender.class, lenderId);
			lender.setUserAIStatus(status);
			entityManager.flush();
		} catch (Exception exception) {
			LOGGER.error("LenderDAOImpl :: updateLenderStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

}