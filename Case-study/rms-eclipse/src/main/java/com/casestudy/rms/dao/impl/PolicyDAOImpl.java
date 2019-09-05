package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.PolicyDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Policy DAO. */
@Repository
public class PolicyDAOImpl implements PolicyDAO {

	public static final Logger LOGGER = Logger.getLogger(PolicyDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public boolean addPolicy(Policy policy) {
		LOGGER.info("PolicyDAOImpl :: addPolicy ");
		try {
			entityManager.persist(policy);
			return true;
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("PolicyDAOImpl :: addPolicy :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Policy> viewPolicy(int lenderId) {
		LOGGER.info("PolicyDAOImpl :: viewPolicy ");
		String hql = "FROM Policy where addedBy = ?1 OR addedBy=?2";
		try {
			return (List<Policy>) entityManager.createQuery(hql).setParameter(1, lenderId)
					.setParameter(2, ApplicationConstant.ADMIN_ID).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("PolicyDAOImpl :: viewPolicy :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: viewPolicy :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public int getMaxPolicyId() {
		LOGGER.info("PolicyDAOImpl :: getMaxPolicyId ");
		String hql = "SELECT MAX(policyId) FROM Policy";
		try {
			return (int) entityManager.createQuery(hql).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: getMaxPolicyId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDefaultPolicyId() {
		LOGGER.info("PolicyDAOImpl :: getDefaultPolicyId ");
		String hql = "SELECT policyId FROM Policy WHERE addedBy=?1";
		try {
			return (List<Integer>) entityManager.createQuery(hql).setParameter(1, ApplicationConstant.ADMIN_ID)
					.getResultList();
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: getDefaultPolicyId :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public Policy findPolicy(int policyId) {
		LOGGER.info("PolicyDAOImpl :: findPolicy ");
		String hql = "FROM Policy where policyId = ?1";
		try {
			return (Policy) entityManager.createQuery(hql).setParameter(1, policyId).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: findPolicy :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean policyNameExist(int lenderId, String policyName) {
		LOGGER.info("PolicyDAOImpl :: policyNameExist ");
		int count = 0;
		String hql = "FROM Policy where addedBy=?1";
		try {
			List<Policy> policyNameLst = entityManager.createQuery(hql).setParameter(1, lenderId).getResultList();
			for (int i = 0; i < policyNameLst.size(); i++) {
				if (policyNameLst.get(i).getPolicyName().equalsIgnoreCase(policyName)) {
					count++;
				}
			}
			return count > 0;
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: policyNameExist :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Policy> getPolicy(int lenderID) {
		LOGGER.info("PolicyDAOImpl :: getPolicy ");
		String hql = "FROM Policy where addedBy=?1 OR addedBy=?2";
		try {
			return (List<Policy>) entityManager.createQuery(hql).setParameter(1, lenderID)
					.setParameter(2, ApplicationConstant.ADMIN_ID).getResultList();
		} catch (Exception exception) {
			LOGGER.error("PolicyDAOImpl :: getPolicy :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

}