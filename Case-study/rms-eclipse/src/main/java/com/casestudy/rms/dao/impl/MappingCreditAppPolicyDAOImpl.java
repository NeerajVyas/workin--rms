package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.MappingCreditAppPolicyDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Represents a Mapping Credit Application Policy DTO.
 */
@Repository
public class MappingCreditAppPolicyDAOImpl implements MappingCreditAppPolicyDAO {

	public static final Logger LOGGER = Logger.getLogger(MappingCreditAppPolicyDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void submitFormValue(MappingCreditAppPolicy mappingCreditAppPolicy) {
		LOGGER.info("MappingCreditAppPolicyDAOImpl :: submitFormValue ");
		try {
			entityManager.persist(mappingCreditAppPolicy);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("MappingCreditAppPolicyDAOImpl :: submitFormValue :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MappingCreditAppPolicy> fetchPolicy(int appId) {
		LOGGER.info("MappingCreditAppPolicyDAOImpl :: fetchPolicy ");
		String hql = "FROM MappingCreditAppPolicy where applicationId=?1 ";
		try {
			return entityManager.createQuery(hql).setParameter(1, appId).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("MappingCreditAppPolicyDAOImpl :: fetchPolicy :: NoResultException");
			LOGGER.error(noResultException.getMessage());
			throw new DAOException(ApplicationConstant.NO_RESULT);
		} catch (Exception exception) {
			LOGGER.error("MappingCreditAppPolicyDAOImpl :: fetchPolicy :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

}