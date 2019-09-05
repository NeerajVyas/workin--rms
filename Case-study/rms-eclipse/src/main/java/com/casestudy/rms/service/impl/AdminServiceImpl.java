package com.casestudy.rms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.casestudy.rms.util.ApplicationConstant;
import com.casestudy.rms.dao.AdminDAO;
import com.casestudy.rms.dao.LenderDAO;
import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.dao.impl.FinancialAnalystDAOImpl;
import com.casestudy.rms.dto.AdminViewBorrowersDTO;
import com.casestudy.rms.dto.AdminViewLendersDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.AdminService;

/** Provides services to Administrator. */
@Service
public class AdminServiceImpl implements AdminService {

	public static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private LenderDAO lenderDAO;

	@Autowired
	private FinancialAnalystDAOImpl financialAnalystDAO;

	@Autowired
	private UserDAO userDAO;


	@Override
	public List<AdminViewLendersDTO> getActiveLenders() {
		LOGGER.info("AdminServiceImpl :: getActiveLenders ");
		List<AdminViewLendersDTO> activeLenderLst = new ArrayList<>();
		List<Lender> lenderLst = adminDAO.getLenderWithStatus(ApplicationConstant.ACTIVE);
		for (int i = 0; i < lenderLst.size(); i++) {
			AdminViewLendersDTO lender = new AdminViewLendersDTO();
			lender.setLenderId("LR" + lenderLst.get(i).getUserId());
			lender.setLenderEmail(lenderLst.get(i).getUserEmail());
			lender.setLenderName(lenderLst.get(i).getUserName());
			lender.setTenureRange(lenderLst.get(i).getTenureRange());
			lender.setLoanInterest("" + lenderLst.get(i).getLoanInterest());
			lender.setLenderAmountRange(lenderLst.get(i).getLoanAmountRange());

			lender.setNumberOfApplicationRequests(
					"" + adminDAO.creditApplicationCountToLender(lenderLst.get(i).getUserId()));
			lender.setNumberOfFinancialAnalyst(
					"" + adminDAO.financialAnalystCountToLender(lenderLst.get(i).getUserId()));
			lender.setLenderActiveInactivestate("ACTIVE");
			activeLenderLst.add(lender);

		}
		return activeLenderLst;
	}

	@Override
	public List<AdminViewLendersDTO> getInactiveLenders() {
		LOGGER.info("AdminServiceImpl :: getInactiveLenders ");
		List<AdminViewLendersDTO> deactiveLenderLst = new ArrayList<>();
		List<Lender> lenderLst = adminDAO.getLenderWithStatus(ApplicationConstant.INACTIVE);
		for (int i = 0; i < lenderLst.size(); i++) {
			AdminViewLendersDTO lender = new AdminViewLendersDTO();
			lender.setLenderId("LR" + lenderLst.get(i).getUserId());
			lender.setLenderEmail(lenderLst.get(i).getUserEmail());
			lender.setLenderName(lenderLst.get(i).getUserName());
			lender.setTenureRange(lenderLst.get(i).getTenureRange());
			lender.setLoanInterest("" + lenderLst.get(i).getLoanInterest());
			lender.setLenderAmountRange(lenderLst.get(i).getLoanAmountRange());

			lender.setNumberOfApplicationRequests(
					"" + adminDAO.creditApplicationCountToLender(lenderLst.get(i).getUserId()));
			lender.setNumberOfFinancialAnalyst(
					"" + adminDAO.financialAnalystCountToLender(lenderLst.get(i).getUserId()));
			lender.setLenderActiveInactivestate("INACTIVE");
			deactiveLenderLst.add(lender);
		}
		return deactiveLenderLst;
	}

	@Override
	public ResponseModel deactivateLender(int lenderId) {
		LOGGER.info("AdminServiceImpl :: deactivateLender ");
		lenderDAO.updateLenderStatus(lenderId, ApplicationConstant.INACTIVE);
		List<FinancialAnalyst> financialAnalystLst = financialAnalystDAO.viewFinancialAnalyst();
		for (int i = 0; i < financialAnalystLst.size(); i++) {
			if (financialAnalystLst.get(i).getLender().getUserId() == lenderId) {
				financialAnalystDAO.updateFinancialAnalystStatus(financialAnalystLst.get(i).getUserId(),
						ApplicationConstant.INACTIVE);
			}
		}
		ResponseModel response = new ResponseModel();
		response.setMessage("Lender has been deactivated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public ResponseModel activateLender(int lenderId) {
		LOGGER.info("AdminServiceImpl :: activateLender ");
		lenderDAO.updateLenderStatus(lenderId, ApplicationConstant.ACTIVE);
		List<FinancialAnalyst> financialAnalystLst = financialAnalystDAO.viewFinancialAnalyst();
		for (int i = 0; i < financialAnalystLst.size(); i++) {
			if (financialAnalystLst.get(i).getLender().getUserId() == lenderId) {
				financialAnalystDAO.updateFinancialAnalystStatus(financialAnalystLst.get(i).getUserId(),
						ApplicationConstant.ACTIVE);
			}
		}
		ResponseModel response = new ResponseModel();
		response.setMessage("Lender has been activated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public List<AdminViewBorrowersDTO> getActiveBorrowers() {

		LOGGER.info("AdminServiceImpl :: getActiveBorrowers ");
		List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
		List<User> borrowerLst = adminDAO.getBorrowerWithStatus(ApplicationConstant.ACTIVE);
		for (int i = 0; i < borrowerLst.size(); i++) {
			AdminViewBorrowersDTO borrower = new AdminViewBorrowersDTO();
			borrower.setBorrowerId("BR" + borrowerLst.get(i).getUserId());
			borrower.setBorrowerEmail(borrowerLst.get(i).getUserEmail());
			borrower.setBorrowerName(borrowerLst.get(i).getUserName());
			borrower.setBorrowerStatus("ACTIVE");
			borrower.setNoOfApplication("" + adminDAO.creditApplicationCountToBorrower(borrowerLst.get(i).getUserId()));
			activeBorrowerLst.add(borrower);

		}
		return activeBorrowerLst;
	}

	@Override
	public List<AdminViewBorrowersDTO> getInactiveBorrowers() {
		LOGGER.info("AdminServiceImpl :: getInactiveBorrowers ");
		List<AdminViewBorrowersDTO> inactiveBorrowerLst = new ArrayList<>();
		List<User> borrowerLst = adminDAO.getBorrowerWithStatus(ApplicationConstant.INACTIVE);
		for (int i = 0; i < borrowerLst.size(); i++) {
			AdminViewBorrowersDTO borrower = new AdminViewBorrowersDTO();
			borrower.setBorrowerId("BR" + borrowerLst.get(i).getUserId());
			borrower.setBorrowerEmail(borrowerLst.get(i).getUserEmail());
			borrower.setBorrowerName(borrowerLst.get(i).getUserName());
			borrower.setBorrowerStatus("INACTIVE");
			borrower.setNoOfApplication("" + adminDAO.creditApplicationCountToBorrower(borrowerLst.get(i).getUserId()));
			inactiveBorrowerLst.add(borrower);

		}
		return inactiveBorrowerLst;
	}

	@Override
	public ResponseModel deactivateBorrower(int borrowerId) {
		LOGGER.info("AdminServiceImpl :: deactivateBorrower ");
		userDAO.updateBorrowerStatus(borrowerId, ApplicationConstant.INACTIVE);
		ResponseModel response = new ResponseModel();
		response.setMessage("Borrower has been deactivated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public ResponseModel activateBorrower(int borrowerId) {
		LOGGER.info("AdminServiceImpl :: activateBorrower ");
		userDAO.updateBorrowerStatus(borrowerId, ApplicationConstant.ACTIVE);
		ResponseModel response = new ResponseModel();
		response.setMessage("Borrower has been activated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

}