package com.casestudy.rms.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.dao.impl.OTPDAOImpl;
import com.casestudy.rms.dto.NewPasswordDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.exception.UserExistsException;
import com.casestudy.rms.exception.UserInputInvalidException;
import com.casestudy.rms.model.OTP;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.UserService;
import com.casestudy.rms.util.ApplicationConstant;
import com.casestudy.rms.util.RmsUtil;

/** Provides services to User. */
@Service
public class UserServiceImpl implements UserService {

	public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private OTPDAOImpl otpDAO;

	@Override
	public ResponseModel registerBorrower(User user) {
		LOGGER.info("UserServiceImpl :: registerBorrower");
		if (!RmsUtil.validateUser(user)) {
			throw new UserInputInvalidException("User details are incorrect");
		} else if (userDAO.userExists(user)) {
			LOGGER.error("UserServiceImpl :: registerBorrower :: User already exits");
			throw new UserExistsException("User already exists");
		} else {
			user.setUserAIStatus(ApplicationConstant.ACTIVE);
			user.setUserRole("ROLE_BORROWER");
			userDAO.registerBorrower(user);
			ResponseModel response = new ResponseModel();
			response.setMessage("User registered successfully");
			response.setStatus(HttpStatus.CREATED.value());
			response.setTimestamp(LocalDateTime.now());
			return response;
		}

	}

	@Override
	public ResponseModel sendOTP(String emailID) {
		LOGGER.info("UserServiceImpl :: sendOTP");
		if (otpDAO.presentOTP(emailID,ApplicationConstant.NOT_VERIFIED_STATUS)) {
			otpDAO.deleteOTP(emailID);
		}
		User u = new User();
		u.setUserEmail(emailID);
		ResponseModel response = new ResponseModel();
		if (userDAO.userExists(u) && userDAO.getUserByEmail(emailID).getUserAIStatus() == ApplicationConstant.ACTIVE) {
			int randomNo = 100000 + new Random().nextInt(900000);
			emailService.sendSimpleMessage(emailID, "RMS",
					"Dear user\nPlease use this OTP to reset your password.\nYour OTP is " + randomNo
							+ "\nPlease do not share this OTP to anyone.");
			response.setMessage("OTP has been sent to Email ID");
			OTP otp = new OTP();
			otp.setEmail(emailID);
			otp.setOtp("" + randomNo);
			otp.setVerified(ApplicationConstant.NOT_VERIFIED_STATUS);
			otpDAO.save(otp);
			response.setStatus(HttpStatus.OK.value());
			response.setTimestamp(LocalDateTime.now());
		} else if (userDAO.getUserByEmail(emailID).getUserAIStatus() == ApplicationConstant.INACTIVE) {
			emailService.sendSimpleMessage(emailID, "RMS",
					"Dear user\nYour account has been deactivated by Admin. \n So your credentials will not work in RISK MANAGAMENT SYSTEM.");
			response.setMessage("Your Email ID has been deactivated");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setTimestamp(LocalDateTime.now());

		} else {
			response.setMessage("Email ID is not registered");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setTimestamp(LocalDateTime.now());
		}
		return response;
	}

	@Override
	public ResponseModel checkOTP(String emailID, String otp) {
		LOGGER.info("UserServiceImpl :: checkOTP");
		ResponseModel response = new ResponseModel();
		if (otpDAO.checkOTP(emailID, otp)) {
			otpDAO.updateOTP(emailID, "VERIFIED");
			response.setMessage("Your OTP is verified");
			response.setStatus(HttpStatus.OK.value());
			response.setTimestamp(LocalDateTime.now());
		} else if (otpDAO.presentOTP(emailID,ApplicationConstant.NOT_VERIFIED_STATUS)) {
			response.setMessage("Your OTP is not verified");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setTimestamp(LocalDateTime.now());
			otpDAO.deleteOTP(emailID);
		} else {
			response.setMessage("Your have not applied for Forgot Password");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setTimestamp(LocalDateTime.now());
		}
		return response;
	}

	@Override
	public ResponseModel updatePassword(NewPasswordDTO newPass) {
		LOGGER.info("UserServiceImpl :: updatePassword");
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		ResponseModel response = new ResponseModel();
		if (otpDAO.presentOTP(newPass.getEmailID(), "VERIFIED")) {
			User user = userDAO.getUserByEmail(newPass.getEmailID());
			userDAO.updateUser(user, bcrypt.encode(newPass.getNewPassword()));
			otpDAO.deleteOTP(newPass.getEmailID());
			emailService.sendSimpleMessage(user.getUserEmail(), "RMS",
					"Dear User\nYour password has been updated successfully");
			response.setMessage("Your password has been updated successfully");
			response.setStatus(HttpStatus.OK.value());
			response.setTimestamp(LocalDateTime.now());
		} else {
			response.setMessage("Your have not applied for Forgot Password");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setTimestamp(LocalDateTime.now());
		}
		return response;
	}

}