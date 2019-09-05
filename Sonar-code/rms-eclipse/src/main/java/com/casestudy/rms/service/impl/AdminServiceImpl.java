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
	public List<AdminViewLendersDTO> getLendersWithStatus(int status) {
		LOGGER.info("AdminServiceImpl :: getLendersWithStatus ");
		List<AdminViewLendersDTO> finalLenderLst = new ArrayList<>();
		List<Lender> lenderLst = adminDAO.getLenderWithStatus(status);
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
			if(status==ApplicationConstant.INACTIVE) {
			lender.setLenderActiveInactivestate("INACTIVE");
			}else {
			lender.setLenderActiveInactivestate("ACTIVE");	
			}
			finalLenderLst.add(lender);
		}
		return finalLenderLst;
	}
	
	@Override
	public ResponseModel updateLenderStatus(int lenderId, int status) {
		LOGGER.info("AdminServiceImpl :: deactivateLender ");
		lenderDAO.updateLenderStatus(lenderId,status);
		List<FinancialAnalyst> financialAnalystLst = financialAnalystDAO.viewFinancialAnalyst();
		for (int i = 0; i < financialAnalystLst.size(); i++) {
			if (financialAnalystLst.get(i).getLender().getUserId() == lenderId) {
				financialAnalystDAO.updateFinancialAnalystStatus(financialAnalystLst.get(i).getUserId(),
						status);
			}
		}
		ResponseModel response = new ResponseModel();
		response.setMessage("Lender A/I status has been updated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}
	
	@Override
	public List<AdminViewBorrowersDTO> getBorrowersWithStatus(int status) {
		LOGGER.info("AdminServiceImpl :: getActiveBorrowers ");
		List<AdminViewBorrowersDTO> activeBorrowerLst = new ArrayList<>();
		List<User> borrowerLst = adminDAO.getBorrowerWithStatus(status);
		for (int i = 0; i < borrowerLst.size(); i++) {
			AdminViewBorrowersDTO borrower = new AdminViewBorrowersDTO();
			borrower.setBorrowerId("BR" + borrowerLst.get(i).getUserId());
			borrower.setBorrowerEmail(borrowerLst.get(i).getUserEmail());
			borrower.setBorrowerName(borrowerLst.get(i).getUserName());
			if(status==ApplicationConstant.INACTIVE) {
				borrower.setBorrowerStatus("INACTIVE");
				}else {
					borrower.setBorrowerStatus("ACTIVE");
				}
			borrower.setNoOfApplication("" + adminDAO.creditApplicationCountToBorrower(borrowerLst.get(i).getUserId()));
			activeBorrowerLst.add(borrower);

		}
		return activeBorrowerLst;
	}

	@Override
	public ResponseModel updateBorrowerStatus(int borrowerId, int status) {
		LOGGER.info("AdminServiceImpl :: activateBorrower ");
		userDAO.updateBorrowerStatus(borrowerId,status);
		ResponseModel response = new ResponseModel();
		response.setMessage("Borrower A/I status has been updated");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	

	

	

}