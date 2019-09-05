package com.casestudy.rms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.casestudy.rms.dao.FinancialAnalystDAO;
import com.casestudy.rms.dao.LenderDAO;
import com.casestudy.rms.dao.PolicyDetailDAO;
import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.dao.impl.CreditAppDAOImpl;
import com.casestudy.rms.dao.impl.MappingCreditAppPolicyDAOImpl;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.dto.UpdatedCreditApplicationStatus;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.CreditAppService;
import com.casestudy.rms.util.ApplicationConstant;
import com.casestudy.rms.util.ApplicationStatus;
import com.casestudy.rms.util.CreditScore;
import com.casestudy.rms.util.RmsUtil;

/**
 * Provides service to Credit Application.
 */
@Service
public class CreditAppServiceImpl implements CreditAppService {

	public static final Logger LOGGER = Logger.getLogger(CreditAppServiceImpl.class);

	@Autowired
	private CreditAppDAOImpl creditAppDAO;

	@Autowired
	private PolicyDetailDAO policyDetailDAO;

	@Autowired
	private MappingCreditAppPolicyDAOImpl mappingCreditAppPolicyDAO;

	@Autowired
	private FinancialAnalystDAO financialAnalystDAO;

	@Autowired
	private LenderDAO lenderDAO;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<BorrowerAllCreditAppDTO> getCreditAppRecords(int borrowerId) {
		LOGGER.info("CreditAppServiceImpl :: getCreditAppRecords");
		List<BorrowerAllCreditAppDTO> borrowerCreditListDTO = new ArrayList<>();
		List<CreditApplication> creditAppList = creditAppDAO.getCreditApplicationByBorrowerId(borrowerId);

		for (int i = 0; i < creditAppList.size(); i++) {
			BorrowerAllCreditAppDTO b1 = new BorrowerAllCreditAppDTO();
			b1.setApplicationId("APP" + creditAppList.get(i).getApplicationId());
			b1.setCompanyName(creditAppList.get(i).getCompanyName());
			b1.setCreditScore("" + creditAppList.get(i).getCreditScore());
			b1.setLenderName(userDAO.getUserNamebyUserID(creditAppList.get(i).getLenderId()));
			String date = "" + creditAppList.get(i).getCreationDate();
			b1.setCreationDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));
		    b1.setApplicationStatus(ApplicationStatus.getApplicationStatus(creditAppList.get(i).getApplicationStatus()));		
			borrowerCreditListDTO.add(b1);

		}

		return borrowerCreditListDTO;
	}

	@Override
	public ResponseModel updateCreditApplicationStatus(UpdatedCreditApplicationStatus creditApp) {
		LOGGER.info("CreditAppServiceImpl :: updateCreditApplicationStatus");
		int status;
		if (creditApp.getApplicationStatus().equals("Approve")) {
			status = ApplicationConstant.STATUS_APPROVED;
		} else {
			status = ApplicationConstant.STATUS_REJECT;
		}
		creditAppDAO.updateCreditApplication(creditApp.getApplicationId(), status);
		ResponseModel response = new ResponseModel();
		response.setMessage("Credit Application Updated Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public List<BorrowerAllCreditAppDTO> viewPreviousUpdatedApplication(int faId) {
		LOGGER.info("CreditAppServiceImpl :: viewPreviousUpdatedApplication");
		List<CreditApplication> creditAppLst = creditAppDAO.viewPreviousUpdatedApplications(faId);
		List<BorrowerAllCreditAppDTO> previousCreditAppLst = new ArrayList<>();
		for (int i = 0; i < creditAppLst.size(); i++) {
			BorrowerAllCreditAppDTO creditApp = new BorrowerAllCreditAppDTO();
			creditApp.setApplicationId("APP" + creditAppLst.get(i).getApplicationId());
			creditApp.setCreditScore("" + creditAppLst.get(i).getCreditScore());
			creditApp.setApplicationStatus(ApplicationStatus.getApplicationStatus(creditAppLst.get(i).getApplicationStatus()));
			creditApp.setCompanyName(creditAppLst.get(i).getCompanyName());
			String date = "" + creditAppLst.get(i).getCreationDate();
			creditApp.setCreationDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));
			previousCreditAppLst.add(creditApp);
		}
		return previousCreditAppLst;
	}

	@Scheduled(cron = "0 0 0 * * ?")
	@Override
	public void informFinancialAnalyst() {
		LOGGER.info("CreditAppServiceImpl :: informFinancialAnalyst");
		List<FinancialAnalyst> financialAnalysts = financialAnalystDAO.viewFinancialAnalyst();
		for (int i = 0; i < financialAnalysts.size(); i++) {
			if (lenderDAO.getPendingRequests(financialAnalysts.get(i).getUserId()) > 0) {
				emailService.sendSimpleMessage(financialAnalysts.get(i).getUserEmail(), "RMS",
						"Dear User\nYou have pending requests\nPlease review pending Credit Applications");
			}
		}

	}
	
	@Override
	public ResponseModel submitCreditAppForm(CreditPolicyMappingDTO creditApplication, int borrowerId) {
		LOGGER.info("CreditAppServiceImpl :: submitCreditAppForm");
		int policyCount = 1;
		int creditScore = ApplicationConstant.INITIAL_CREDIT_SCORE;
		creditAppDAO.submitCreditAppForm(RmsUtil.setCreditApplicationDetail(creditApplication, borrowerId));
		int maxId = creditAppDAO.getMaxApplicationIdId();
		List<Map<String, String>> pv = creditApplication.getPolicyValues();

		for (int i = 0; i < pv.size(); i++) {	
			policyCount++;
			MappingCreditAppPolicy mappingCreditAppPolicy = new MappingCreditAppPolicy();
			mappingCreditAppPolicy
					.setPolicyId(Integer.parseInt(creditApplication.getPolicyValues().get(i).get("policyId")));
			mappingCreditAppPolicy.setPolicyValue(creditApplication.getPolicyValues().get(i).get("policyValue"));
			mappingCreditAppPolicy.setApplicationId(maxId);
			mappingCreditAppPolicyDAO.submitFormValue(mappingCreditAppPolicy);
			PolicyDetail policyDetail = policyDetailDAO.findPolicyByID(
					Integer.parseInt(creditApplication.getPolicyValues().get(i).get("policyId")),
					creditApplication.getLenderId());
			creditScore = CreditScore.calculateCreditScore(creditScore, Integer.parseInt(policyDetail.getThreshold()),
					policyDetail.getPolicyWeightage(),
					Long.parseLong(creditApplication.getPolicyValues().get(i).get("policyValue")));
		}
		
			creditScore = creditScore / (policyCount - 1);
			creditAppDAO.updateCreditScore(maxId, creditScore,RmsUtil.checkCreditApplicationStatus(creditScore));

		ResponseModel response = new ResponseModel();
		response.setMessage("Credit Application submitted successfully");
		response.setStatus(HttpStatus.CREATED.value());
		response.setTimestamp(LocalDateTime.now());
		return response;

	}

}
