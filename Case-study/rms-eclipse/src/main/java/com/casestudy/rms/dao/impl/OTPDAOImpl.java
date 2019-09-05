package com.casestudy.rms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.OTPDAO;
import com.casestudy.rms.model.OTP;

/**
 * Represents OTP DAO.
 */
@Repository
public class OTPDAOImpl implements OTPDAO {

	public static final Logger LOGGER = Logger.getLogger(OTPDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(OTP otp) {
		LOGGER.info("OTPDAOImpl :: save ");
		entityManager.persist(otp);
		entityManager.flush();
		entityManager.close();
	}

	@Override
	public boolean checkOTP(String emailID, String otp) {
		LOGGER.info("OTPDAOImpl :: checkOTP ");
		String hql = "FROM OTP where email =?1 AND otp=?2";
		int count = entityManager.createQuery(hql).setParameter(1, emailID).setParameter(2, otp).getResultList().size();
		return count > 0;
	}

	@Override
	public boolean presentOTP(String emailID, String verified) {
		LOGGER.info("OTPDAOImpl :: presentOTP ");
		String hql = "FROM OTP where email =?1 and verified=?2";
		int count = entityManager.createQuery(hql).setParameter(1, emailID).setParameter(2, verified).getResultList()
				.size();
		return count > 0;

	}

	@Override
	@Transactional
	public void deleteOTP(String emailID) {
		LOGGER.info("OTPDAOImpl :: deleteOTP ");
		OTP otp = entityManager.find(OTP.class, emailID);
		entityManager.remove(otp);
	}

	@Override
	@Transactional
	public void updateOTP(String emailID, String verified) {
		LOGGER.info("OTPDAOImpl :: updateOTP ");
		OTP otp = entityManager.find(OTP.class, emailID);
		otp.setVerified(verified);
		entityManager.flush();
	}
}
