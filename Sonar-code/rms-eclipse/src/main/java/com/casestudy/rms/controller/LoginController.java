package com.casestudy.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dao.impl.UserDAOImpl;
import com.casestudy.rms.dto.LoginUserDTO;
import com.casestudy.rms.model.User;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * 
 *
 */
@RestController
@RequestMapping("/rms/login")
@CrossOrigin(origins = { ApplicationConstant.ANGULAR_URL })
public class LoginController {
	@Autowired
	private UserDAOImpl userDAO;

	/**
	 * It validates User according to role.
	 * 
	 * @param emailId - Email ID
	 * @return Login User
	 */
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public LoginUserDTO validateLogin(@RequestParam("emailId") String emailId) {

		User user = userDAO.getUserByEmail(emailId);
		LoginUserDTO logUserDTO = new LoginUserDTO();

		logUserDTO.setUserEmail(user.getUserEmail());
		logUserDTO.setUserId(user.getUserId());
		logUserDTO.setUserName(user.getUserName());
		logUserDTO.setUserRole(user.getUserRole());
		logUserDTO.setUserAIStatus(user.getUserAIStatus());

		return logUserDTO;
	}

}
