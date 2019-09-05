package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.PolicyDetailDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Policy Detail DAO. */

@Repository
public class PolicyDetailDAOImpl implements PolicyDetailDAO {

	public static final Logger LOGGER = Logger.getLogger(PolicyDetailDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public boolean addPolicyDetail(PolicyDetail policyDetail) {
		LOGGER.info("PolicyDetailDAOImpl :: addPolicyDetail ");
		try {
			entityManager.persist(policyDetail);
			return true;
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("PolicyDetailDAOImpl :: addPolicyDetail :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@Override
	public boolean policyDetailExist(PolicyDetail policyDetail) {
		LOGGER.info("PolicyDetailDAOImpl :: policyDetailExist ");
		String hql = "FROM PolicyDetail where policyId =?1 AND lenderId = ?2";
		try {
			int count = entityManager.createQuery(hql).setParameter(1, policyDetail.getPolicyId())
					.setParameter(2, policyDetail.getLenderId()).getResultList().size();
			return count > 0;
		} catch (Exception exception) {
			LOGGER.error("PolicyDetailDAOImpl :: policyDetailExist :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Transactional
	@Override
	public boolean updatePolicyDetail(PolicyDetail policyDetail) {
		LOGGER.info("PolicyDetailDAOImpl :: updatePolicyDetail ");
		entityManager.flush();
		entityManager.close();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyDetail> viewpolicyDetail(int lenderId) {
		LOGGER.info("PolicyDetailDAOImpl :: viewpolicyDetail ");
		String hql = "FROM PolicyDetail where lenderId=?1";
		try {
			return (List<PolicyDetail>) entityManager.createQuery(hql).setParameter(1, lenderId).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("PolicyDetailDAOImpl :: viewpolicyDetail :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("PolicyDetailDAOImpl :: viewpolicyDetail :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public PolicyDetail findPolicyDetail(int policyId, Lender lender) {
		LOGGER.info("PolicyDetailDAOImpl :: findPolicyDetail ");
		String hql = "FROM PolicyDetail where lenderId=?1 and policyId=?2";
		try {
			return (PolicyDetail) entityManager.createQuery(hql).setParameter(1, lender.getUserId())
					.setParameter(2, policyId).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("PolicyDetailDAOImpl :: findPolicyDetail :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Override
	public PolicyDetail findPolicyByID(int policyId, int lenderId) {
		LOGGER.info("PolicyDetailDAOImpl :: findPolicyDetailByID ");
		String hql = "FROM PolicyDetail where lenderId=?1 and policyId=?2";
		try {
			return (PolicyDetail) entityManager.createQuery(hql).setParameter(1, lenderId).setParameter(2, policyId)
					.getResultList().get(0);
		} catch (NoResultException noResultException) {
			LOGGER.error("PolicyDetailDAOImpl :: findPolicyByID :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("PolicyDetailDAOImpl :: findPolicyByID :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

}
