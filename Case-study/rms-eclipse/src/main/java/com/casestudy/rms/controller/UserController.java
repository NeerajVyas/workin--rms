package com.casestudy.rms.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.NewPasswordDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.CreditAppService;
import com.casestudy.rms.service.UserService;
import com.casestudy.rms.service.impl.EmailServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents an User Controller. */
@RestController
@RequestMapping("/rms")
@CrossOrigin(origins = { ApplicationConstant.ANGULAR_URL })
public class UserController {

	public static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CreditAppService creditAppService;

	@Autowired
	private EmailServiceImpl emailService;

	/**
	 * Register a Borrower.
	 * 
	 * @param user - Borrower
	 * @return Status
	 */
	@PostMapping(value = "/registerborrower", consumes = "application/JSON")
	public ResponseEntity<ResponseModel> registerBorrower(@RequestBody User user) {
		LOGGER.info("UserController :: registerBorrower ");
		ResponseModel response = userService.registerBorrower(user);
		emailService.sendSimpleMessage(user.getUserEmail(), "RMS", "Dear " + user.getUserName()
				+ ",\nYou have been registered successfully.\nGenerate your Credit Score free using RMS");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Submit a Credit Application form.
	 * 
	 * @param creditApp  - Credit Policy DTO
	 * @param borrowerId - Borrower ID
	 * @return Response
	 */
	@PostMapping(value = "/submitcreditappform", consumes = "application/JSON")
	public ResponseEntity<ResponseModel> submitCreditAppForm(@RequestBody CreditPolicyMappingDTO creditApp,
			@RequestParam("id") int borrowerId) {
		LOGGER.info("UserController :: submitCreditAppForm ");
		ResponseModel response = creditAppService.submitCreditAppForm(creditApp, borrowerId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Get previous credit application details.
	 * 
	 * @param id - Borrower Id
	 * @return List of Credit application corresponding to borrower
	 */
	@GetMapping("/creditScore")
	public ResponseEntity<List<BorrowerAllCreditAppDTO>> getCreditApplicationByBorrowerId(@RequestParam("id") int id) {
		LOGGER.info("UserController :: getCreditApplicationByBorrowerId ");
		List<BorrowerAllCreditAppDTO> creditApplication = creditAppService.getCreditAppRecords(id);
		return new ResponseEntity<>(creditApplication, HttpStatus.OK);
	}

	/**
	 * Generates OTP to reset password.
	 * 
	 * @param emailId - email ID
	 * @return response
	 */
	@GetMapping("/forgotPassword/user")
	public ResponseEntity<ResponseModel> getOTP(@RequestParam("emailId") String emailId) {
		LOGGER.info("UserController :: getOTP ");
		ResponseModel response = userService.sendOTP(emailId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method checks OTP to reset Password.
	 * 
	 * @param newPass - OTP and emailID
	 * @return response
	 */
	@PostMapping("/forgotPassword/checkOTP")
	public ResponseEntity<ResponseModel> checkOTP(@RequestBody NewPasswordDTO newPass) {
		LOGGER.info("UserController :: checkOTP ");
		ResponseModel response = userService.checkOTP(newPass.getEmailID(), newPass.getOtp());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Updates new password after verifying OTP.
	 * 
	 * @param newPass - New Password
	 * @return response
	 */
	@PutMapping("/forgotPassword/createnewpassword")
	public ResponseEntity<ResponseModel> setNewPassword(@RequestBody NewPasswordDTO newPass) {
		LOGGER.info("UserController :: setNewPassword ");
		ResponseModel response = userService.updatePassword(newPass);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}