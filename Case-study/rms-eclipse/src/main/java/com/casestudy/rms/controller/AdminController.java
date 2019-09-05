package com.casestudy.rms.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.service.AdminService;
import com.casestudy.rms.util.ApplicationConstant;

/** Represents Administrator Controller. */
@RestController
@RequestMapping("/rms")
@CrossOrigin(origins = { ApplicationConstant.ANGULAR_URL })
public class AdminController {

	public static final Logger LOGGER = Logger.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	/**
	 * Gets Active Lenders.
	 * 
	 * @return List of Active Lenders.
	 */
	@GetMapping("viewLenders/ActiveLenders")
	public ResponseEntity<List<AdminViewLendersDTO>> getActiveLenders() {
		LOGGER.info("AdminController :: getActiveLenders ");
		List<AdminViewLendersDTO> lst = adminService.getActiveLenders();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	/**
	 * Gets Inactive Lenders.
	 * 
	 * @return List of Inactive Lenders.
	 */
	@GetMapping("viewLenders/InactiveLenders")
	public ResponseEntity<List<AdminViewLendersDTO>> getInactiveLenders() {
		LOGGER.info("AdminController :: getInactiveLenders ");
		List<AdminViewLendersDTO> lst = adminService.getInactiveLenders();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	/**
	 * Deactivates a lender.
	 * 
	 * @param lenderId - Lender ID
	 * @return response
	 */
	@GetMapping("deactivateLender")
	public ResponseEntity<ResponseModel> deactiveLender(@RequestParam("id") String lenderId) {
		LOGGER.info("AdminController :: deactiveLender ");
		int id = Integer.parseInt(lenderId.substring(2));
		ResponseModel response = adminService.deactivateLender(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Activates a lender.
	 * 
	 * @param lenderId - Lender ID
	 * @return response
	 */
	@GetMapping("activateLender")
	public ResponseEntity<ResponseModel> activeLender(@RequestParam("id") String lenderId) {
		LOGGER.info("AdminController :: activeLender ");
		int id = Integer.parseInt(lenderId.substring(2));
		ResponseModel response = adminService.activateLender(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Return list of active borrowers.
	 * 
	 * @return List of active borrowers
	 */
	@GetMapping("viewBorrowers/ActiveBorrowers")
	public ResponseEntity<List<AdminViewBorrowersDTO>> getActiveBorrowers() {
		LOGGER.info("AdminController :: getActiveBorrowers ");
		List<AdminViewBorrowersDTO> lst = adminService.getActiveBorrowers();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	/**
	 * Return list of inactive borrowers.
	 * 
	 * @return List of inactive borrowers
	 */
	@GetMapping("viewBorrowers/InactiveBorrowers")
	public ResponseEntity<List<AdminViewBorrowersDTO>> getInactiveBorrowers() {
		LOGGER.info("AdminController :: getInactiveBorrowers ");
		List<AdminViewBorrowersDTO> lst = adminService.getInactiveBorrowers();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	/**
	 * Deactivate a borrower.
	 * 
	 * @param borrowerId - Borrower ID
	 * @return response
	 */
	@GetMapping("deactivateBorrower")
	public ResponseEntity<ResponseModel> deactiveBorrower(@RequestParam("id") String borrowerId) {
		LOGGER.info("AdminController :: deactiveBorrower ");
		int id = Integer.parseInt(borrowerId.substring(2));
		ResponseModel response = adminService.deactivateBorrower(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Activate a borrower.
	 * 
	 * @param borrowerId - Borrower ID
	 * @return response
	 */
	@GetMapping("activateBorrower")
	public ResponseEntity<ResponseModel> activeBorrower(@RequestParam("id") String borrowerId) {
		LOGGER.info("AdminController :: deactiveBorrower ");
		int id = Integer.parseInt(borrowerId.substring(2));
		ResponseModel response = adminService.activateBorrower(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
