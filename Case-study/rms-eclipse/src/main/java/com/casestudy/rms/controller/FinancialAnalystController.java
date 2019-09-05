package com.casestudy.rms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystReceivedCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystTopBorrowerDTO;
import com.casestudy.rms.dto.FinancialAnalystViewDetailsDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.dto.UpdatedCreditApplicationStatus;
import com.casestudy.rms.service.CreditAppService;
import com.casestudy.rms.service.FinancialAnalystService;
import com.casestudy.rms.service.impl.EmailServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Represents Financial Analyst Controller.
 */
@RestController
@RequestMapping("/rms/financial-analyst")
@CrossOrigin(origins = { ApplicationConstant.ANGULAR_URL })
public class FinancialAnalystController {

	public static final Logger LOGGER = Logger.getLogger(FinancialAnalystController.class);

	@Autowired
	private FinancialAnalystService financialAnalystService;

	@Autowired
	private CreditAppService creditAppService;

	@Autowired
	private EmailServiceImpl emailService;

	/**
	 * View Assigned Credit Application by Lender.
	 * 
	 * @param faid - Financial Analyst ID
	 * @return List of Credit Application with Borrower Detail
	 */
	@GetMapping("view-credit-app-form")
	public ResponseEntity<List<FinancialAnalystReceivedCreditAppDTO>> viewCreditApplications(
			@RequestParam("faid") int faid) {
		LOGGER.info("FinancialAnalystController :: viewCreditApplications ");
		List<FinancialAnalystReceivedCreditAppDTO> responseList = financialAnalystService.fetchCreditApp(faid);
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	/**
	 * Get Top 10 Borrower with highest credit score.
	 * 
	 * @param faid Financial Analyst ID
	 * @return List of Borrower with credit score
	 */
	@GetMapping("top-ten-borrower")
	public ResponseEntity<List<FinancialAnalystTopBorrowerDTO>> getTopBorrowers(@RequestParam("faid") int faid) {
		LOGGER.info("FinancialAnalystController :: getTopBorrowers ");
		List<FinancialAnalystTopBorrowerDTO> topBorrowerLst = financialAnalystService.getTopBorrower(faid);
		return new ResponseEntity<>(topBorrowerLst, HttpStatus.OK);
	}

	/**
	 * Fetching Details of Credit Application and Lender Policies. So that It's
	 * status can be updated from assigned to approve/Reject.
	 * 
	 * @param faId          -financial analyst Id.
	 * @param applicationId - Application Id.
	 * @param borrowerEmail - Borrower Email.
	 * @return - Details for review the application By Financial Analyst.
	 */
	@GetMapping("view-more-details")
	public ResponseEntity<FinancialAnalystViewDetailsDTO> viewMoreDetails(@RequestParam("faId") int faId,
			@RequestParam("appId") String applicationId, @RequestParam("borrowerEmail") String borrowerEmail) {
		LOGGER.info("FinancialAnalystController :: viewMoreDetails ");
		int appId = Integer.parseInt(applicationId.substring(ApplicationConstant.APP_ID_SUBSTRING));
		FinancialAnalystViewDetailsDTO faViewDetailsDTO = financialAnalystService.fetchDetailsforFinancialAnalyst(faId,
				appId, borrowerEmail);
		return new ResponseEntity<>(faViewDetailsDTO, HttpStatus.OK);
	}

	/**
	 * Updates the status of on-hold credit application filled by Borrower.
	 * 
	 * @param updatedStatus - Updated status detail
	 * @param faId          - Financial Analyst ID
	 * @return response
	 */
	@PutMapping("update-credit-application-status")
	public ResponseEntity<ResponseModel> updateCreditApplicationStatus(
			@RequestBody UpdatedCreditApplicationStatus updatedStatus, @RequestParam("faId") int faId) {
		LOGGER.info("FinancialAnalystController :: updateCreditApplicationStatus ");
		ResponseModel response = creditAppService.updateCreditApplicationStatus(updatedStatus);
		if (updatedStatus.getApplicationStatus().equals("Approve")) {
			emailService.sendSimpleMessage(updatedStatus.getBorrowerEmail(), "RMS", "Dear user\nYour Application APP"
					+ updatedStatus.getApplicationId() + " has been approved by Financial Analyst.");
			emailService.sendSimpleMessage(updatedStatus.getLenderEmail(), "RMS", "Dear user\nCredit Application APP"
					+ updatedStatus.getApplicationId() + " assigned by you has been approved by Financial Analyst.");
		} else {
			emailService.sendSimpleMessage(updatedStatus.getBorrowerEmail(), "RMS", "Dear user\nYour Application APP"
					+ updatedStatus.getApplicationId() + " has been rejected by Financial Analyst.");
			emailService.sendSimpleMessage(updatedStatus.getLenderEmail(), "RMS", "Dear user\nCredit Application APP"
					+ updatedStatus.getApplicationId() + " assigned by you has been rejected by Financial Analyst.");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * View previous updated application updated by Financial Analyst.
	 * 
	 * @param faId - Financial Analyst ID
	 * @return List of previous updated application
	 */
	@GetMapping("view-previous-updated-application")
	public ResponseEntity<List<BorrowerAllCreditAppDTO>> viewPreviousUpdatedApplication(
			@RequestParam("faId") int faId) {
		LOGGER.info("FinancialAnalystController :: viewPreviousUpdatedApplication ");
		List<BorrowerAllCreditAppDTO> creditApp = creditAppService.viewPreviousUpdatedApplication(faId);
		return new ResponseEntity<>(creditApp, HttpStatus.OK);
	}
}
