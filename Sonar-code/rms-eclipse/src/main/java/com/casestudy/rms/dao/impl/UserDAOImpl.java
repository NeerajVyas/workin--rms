package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.User;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents a User DAO. */
@Repository
public class UserDAOImpl implements UserDAO {

	public static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void registerBorrower(User user) {
		LOGGER.info("UserDAOImpl :: registerBorrower ");
		try {
			entityManager.persist(user);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			LOGGER.error("UserDAOImpl :: registerBorrower :: Exception ");
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.flush();
			entityManager.close();
		}
	}

	@Override
	public boolean userExists(User user) {
		LOGGER.info("UserDAOImpl :: userExists ");
		String hql = "FROM User WHERE userEmail = ?1";
		try {
			int count = entityManager.createQuery(hql).setParameter(1, user.getUserEmail()).getResultList().size();
			return count > 0;
		} catch (Exception exception) {
			LOGGER.error("UserDAOImpl :: userExists :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String userEmail) {
		LOGGER.info("UserDAOImpl :: getUserByEmail ");
		String hql = "FROM User WHERE userEmail =?1";
		try {
			List<User> userList = (List<User>) entityManager.createQuery(hql).setParameter(1, userEmail)
					.getResultList();
			return entityManager.find(User.class, userList.get(0).getUserId());
		} catch (Exception exception) {
			LOGGER.error("UserDAOImpl :: getUserByEmail :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public String getUserNamebyUserID(int bid) {
		LOGGER.info("UserDAOImpl :: getUserNamebyUserID ");
		String hql = "SELECT userName FROM User WHERE userId =?1";
		try {
			return (String) entityManager.createQuery(hql).setParameter(1, bid).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("UserDAOImpl :: getUserNamebyUserID :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public String getUserEmailbyUserID(int bid) {
		LOGGER.info("UserDAOImpl :: getUserEmailbyUserID ");
		String hql = "SELECT userEmail FROM User WHERE userId =?1";
		try {
			return (String) entityManager.createQuery(hql).setParameter(1, bid).getResultList().get(0);
		} catch (Exception exception) {
			LOGGER.error("UserDAOImpl :: getUserEmailbyUserID :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}
	}

	@Transactional
	@Override
	public void updateBorrowerStatus(int borrowerId, int status) {
		LOGGER.info("UserDAOImpl :: updateBorrowerStatus ");
		try {
			User user = entityManager.find(User.class, borrowerId);
			user.setUserAIStatus(status);
			entityManager.flush();
		} catch (Exception exception) {
			LOGGER.error("UserDAOImpl :: updateBorrowerStatus :: Exception");
			LOGGER.error(exception.getMessage());
			throw new DAOException(ApplicationConstant.PROBLEM_OCCURED_MSG);
		} finally {
			entityManager.close();
		}

	}

	@Transactional
	@Override
	public void updateUser(User user, String newPassword) {
		LOGGER.info("UserDAOImpl :: updateUser ");
		user.setUserPassword(newPassword);
		entityManager.flush();
	}

}
