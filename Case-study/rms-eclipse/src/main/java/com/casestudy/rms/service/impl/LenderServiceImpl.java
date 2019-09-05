package com.casestudy.rms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.casestudy.rms.dao.CreditAppDAO;
import com.casestudy.rms.dao.FinancialAnalystDAO;
import com.casestudy.rms.dao.LenderDAO;
import com.casestudy.rms.dao.MappingCreditAppPolicyDAO;
import com.casestudy.rms.dao.PolicyDAO;
import com.casestudy.rms.dao.PolicyDetailDAO;
import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.BorrowerViewLenderDTO;
import com.casestudy.rms.dto.CreditApplicationDetails;
import com.casestudy.rms.dto.FinancialAnalystResponse;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.PolicyValues;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.exception.PolicyExistsException;
import com.casestudy.rms.exception.PolicyInputInvalidException;
import com.casestudy.rms.exception.UserExistsException;
import com.casestudy.rms.exception.UserInputInvalidException;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.LenderService;
import com.casestudy.rms.util.ApplicationConstant;
import com.casestudy.rms.util.ApplicationStatus;

/** Provides services to Lender. */
@Service
public class LenderServiceImpl implements LenderService {

	public static final Logger LOGGER = Logger.getLogger(LenderServiceImpl.class);

	@Autowired
	private LenderDAO lenderDAO;

	@Autowired
	private FinancialAnalystDAO financialAnalystDAO;

	@Autowired
	private PolicyDAO policyDAO;

	@Autowired
	private PolicyDetailDAO policyDetailDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CreditAppDAO creditApplicationDAO;

	@Autowired
	private MappingCreditAppPolicyDAO mappingCreditAppPolicy;

	@Override
	public ResponseModel registerLender(Lender lender) {
		LOGGER.info("LenderServiceImpl :: registerLender");
		if (lender.getUserName() == null || lender.getUserName().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: registerLender :: Name cannot be Empty");
			throw new UserInputInvalidException("Name cannot be empty");
		} else if (lender.getUserEmail() == null || lender.getUserEmail().isEmpty()
				|| !lender.getUserEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			LOGGER.error("LenderServiceImpl :: registerLender :: Email is not valid");
			throw new UserInputInvalidException("Email cannot be empty or Email is invalid");
		} else if (lender.getUserPassword().length() < ApplicationConstant.MIN_PASSWORD_LENGTH
				|| lender.getUserPassword().length() > ApplicationConstant.MAX_PASSWORD_LENGTH
				|| lender.getUserPassword() == null || lender.getUserPassword().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: registerLender :: Password is not valid");
			throw new UserInputInvalidException("Password length must be atleast 8");
		} else if (lenderDAO.lenderExists(lender)) {
			LOGGER.error("LenderServiceImpl :: registerLender :: User already exits");
			throw new UserExistsException("User already exists");
		} else {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			lender.setUserPassword(bcrypt.encode(lender.getUserPassword()));
			lender.setUserAIStatus(ApplicationConstant.ACTIVE);
			lender.setUserRole("ROLE_LENDER");
			lenderDAO.registerLender(lender);

			List<Integer> defaultPolicyIdLst = policyDAO.getDefaultPolicyId();
			for (int i = 0; i < defaultPolicyIdLst.size(); i++) {
				PolicyDetail policyDetail = new PolicyDetail();
				policyDetail.setPolicyId(defaultPolicyIdLst.get(i));
				policyDetail.setPolicyStatus(ApplicationConstant.POLICY_INACTIVE);
				policyDetail.setPolicyWeightage(ApplicationConstant.DEFAULT_POLICY_WEIGHTAGE);
				policyDetail.setThreshold(ApplicationConstant.DEFAULT_POLICY_THRESHOLD);
				policyDetail.setLenderId(lenderDAO.getMaxLenderId());
				policyDetailDAO.addPolicyDetail(policyDetail);
			}

			ResponseModel response = new ResponseModel();
			response.setMessage("Lender registered successfully");
			response.setStatus(HttpStatus.CREATED.value());
			response.setTimestamp(LocalDateTime.now());
			return response;

		}

	}

	@Override
	public ResponseModel addFinancialAnalyst(FinancialAnalyst financialAnalyst, Lender lender) {
		LOGGER.info("LenderServiceImpl :: addFinancialAnalyst");

		if (financialAnalyst.getUserName() == null || financialAnalyst.getUserName().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addFinancialAnalyst :: Name cannot be Empty");
			throw new UserInputInvalidException("Name cannot be empty");
		} else if (financialAnalyst.getUserEmail() == null || financialAnalyst.getUserEmail().isEmpty()
				|| !financialAnalyst.getUserEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			LOGGER.error("LenderServiceImpl :: addFinancialAnalyst :: Email is not valid");
			throw new UserInputInvalidException("Email cannot be empty or Email is invalid");
		} else if (financialAnalyst.getUserPassword().length() < ApplicationConstant.MIN_PASSWORD_LENGTH
				|| financialAnalyst.getUserPassword().length() > ApplicationConstant.MAX_PASSWORD_LENGTH
				|| financialAnalyst.getUserPassword() == null || financialAnalyst.getUserPassword().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addFinancialAnalyst :: Password is not valid");
			throw new UserInputInvalidException("Password length must be atleast 8");
		} else if (financialAnalystDAO.financialAnalystExist(financialAnalyst)) {
			LOGGER.error("LenderServiceImpl :: addFinancialAnalyst :: User already exits");
			throw new UserExistsException("User already exists");
		} else {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			financialAnalyst.setUserPassword(bcrypt.encode(financialAnalyst.getUserPassword()));
			List<FinancialAnalyst> financialAnalystLst = lender.getFinancialAnalysts();
			financialAnalystLst.add(financialAnalyst);
			lender.setFinancialAnalysts(financialAnalystLst);

			financialAnalyst.setUserAIStatus(ApplicationConstant.ACTIVE);
			financialAnalyst.setUserRole("ROLE_FINANCIAL_ANALYST");
			financialAnalyst.setLender(lender);
			lenderDAO.addFinancialAnalyst(financialAnalyst);

			ResponseModel response = new ResponseModel();
			response.setMessage("Financial Analyst added successfully");
			response.setStatus(HttpStatus.CREATED.value());
			response.setTimestamp(LocalDateTime.now());
			return response;

		}
	}

	@Override
	public Lender getLender(int lenderId) {
		LOGGER.info("LenderServiceImpl :: getLender");
		return lenderDAO.getLender(lenderId);
	}

	@Override
	public ResponseModel addPolicy(PolicyResponse policyResponse, Lender lender) {
		LOGGER.info("LenderServiceImpl :: addPolicy");
		if (policyResponse.getPolicyName() == null || policyResponse.getPolicyName().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Name cannot be Empty");
			throw new PolicyInputInvalidException("Policy Name cannot be empty");
		} else if (policyResponse.getThreshold() == null || policyResponse.getPolicyName().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Threshold value cannot be Empty");
			throw new PolicyInputInvalidException("Policy Threshold value cannot be empty");
		} else if (policyDAO.policyNameExist(lender.getUserId(), policyResponse.getPolicyName())) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Already Exists");
			throw new PolicyExistsException("Policy Already Exists");
		} else {
			Policy policy = new Policy();
			policy.setPolicyName(policyResponse.getPolicyName());
			policy.setAddedBy(lender.getUserId());
			policyDAO.addPolicy(policy);

			PolicyDetail policyDetail = new PolicyDetail();
			policyDetail.setLenderId(lender.getUserId());
			policyDetail.setPolicyWeightage(policyResponse.getPolicyWeightage());
			policyDetail.setThreshold(policyResponse.getThreshold());
			policyDetail.setPolicyStatus(ApplicationConstant.POLICY_ACTIVE);
			policyDetail.setPolicyId(policyDAO.getMaxPolicyId());
			policyDetailDAO.addPolicyDetail(policyDetail);

			ResponseModel response = new ResponseModel();
			response.setMessage("Policy added successfully");
			response.setStatus(HttpStatus.CREATED.value());
			response.setTimestamp(LocalDateTime.now());
			return response;

		}
	}

	@Override
	public ResponseModel updatePolicyDetail(PolicyResponse[] policyResponses, Lender lender) {
		LOGGER.info("LenderServiceImpl :: updatePolicyDetail");
		for (int i = 0; i < policyResponses.length; i++) {

			PolicyDetail policyDetail = policyDetailDAO.findPolicyDetail(policyResponses[i].getPolicyId(), lender);
			policyDetail.setPolicyStatus(policyResponses[i].getPolicyStatus());
			policyDetail.setPolicyWeightage(policyResponses[i].getPolicyWeightage());
			policyDetail.setThreshold(policyResponses[i].getThreshold());
			policyDetailDAO.updatePolicyDetail(policyDetail);

		}

		ResponseModel response = new ResponseModel();
		response.setMessage("Policy updated successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public List<PolicyResponse> viewPolicy(int lenderId) {
		LOGGER.info("LenderServiceImpl :: viewPolicy");
		List<Policy> policy = policyDAO.viewPolicy(lenderId);
		List<PolicyDetail> policydetail = policyDetailDAO.viewpolicyDetail(lenderId);
		List<PolicyResponse> policyResponseLst = new ArrayList<>();

		for (int i = 0; i < policy.size(); i++) {

			for (int j = 0; j < policydetail.size(); j++) {

				if ((policy.get(i).getPolicyId() == policydetail.get(j).getPolicyId())) {

					PolicyResponse policyResponse = new PolicyResponse();
					policyResponse.setPolicyId(policy.get(i).getPolicyId());
					policyResponse.setThreshold(policydetail.get(j).getThreshold());
					policyResponse.setPolicyName(policy.get(i).getPolicyName());
					policyResponse.setPolicyWeightage(policydetail.get(j).getPolicyWeightage());
					policyResponse.setPolicyStatus(policydetail.get(j).getPolicyStatus());
					policyResponseLst.add(policyResponse);

				}
			}

		}
		Collections.sort(policyResponseLst);
		return policyResponseLst;
	}

	@Override
	public List<FinancialAnalystResponse> viewFinancialAnalyst(int lenderId) {
		LOGGER.info("LenderServiceImpl :: viewFinancialAnalyst");
		List<FinancialAnalyst> financialAnalysts = financialAnalystDAO.viewFinancialAnalyst();
		List<FinancialAnalystResponse> list = new ArrayList<>();

		for (int i = 0; i < financialAnalysts.size(); i++) {
			if (financialAnalysts.get(i).getLender().getUserId() == lenderId) {
				FinancialAnalystResponse financialanalystresponse = new FinancialAnalystResponse();
				financialanalystresponse.setFinancialAnalystId("FA" + financialAnalysts.get(i).getUserId());
				financialanalystresponse.setFinancialAnalystName(financialAnalysts.get(i).getUserName());
				financialanalystresponse.setFinancialAnalystEmail(financialAnalysts.get(i).getUserEmail());
				financialanalystresponse
						.setPendingRequests(lenderDAO.getPendingRequests(financialAnalysts.get(i).getUserId()));
				list.add(financialanalystresponse);

			}

		}
		Collections.sort(list);
		return list;

	}

	@Override
	public List<BorrowerViewLenderDTO> getActiveLenders() {
		LOGGER.info("LenderServiceImpl :: getActiveLenders");
		List<Lender> lenderLst = lenderDAO.getLenderWithStatus(ApplicationConstant.ACTIVE);
		List<BorrowerViewLenderDTO> bLenderLst = new ArrayList<>();
		for (int i = 0; i < lenderLst.size(); i++) {
			BorrowerViewLenderDTO lender = new BorrowerViewLenderDTO();
			lender.setUserId(lenderLst.get(i).getUserId());
			lender.setUserEmail(lenderLst.get(i).getUserEmail());
			lender.setUserName(lenderLst.get(i).getUserName());
			lender.setLenderDescription(lenderLst.get(i).getLenderDescription());
			lender.setLoanAmountRange(lenderLst.get(i).getLoanAmountRange());
			lender.setLoanInterest(lenderLst.get(i).getLoanInterest());
			lender.setTenureRange(lenderLst.get(i).getTenureRange());
			bLenderLst.add(lender);
		}
		return bLenderLst;
	}

	@Override
	public List<BorrowerAllCreditAppDTO> getCreditApplication(int lenderId) {
		LOGGER.info("LenderServiceImpl :: getCreditApplication");
		List<BorrowerAllCreditAppDTO> borrowerCreditListDTO = new ArrayList<>();
		List<CreditApplication> creditAppList = lenderDAO.getCreditApplication(lenderId);

		for (int i = 0; i < creditAppList.size(); i++) {
			BorrowerAllCreditAppDTO b1 = new BorrowerAllCreditAppDTO();

			b1.setApplicationId("APP" + creditAppList.get(i).getApplicationId());
			b1.setCompanyName(creditAppList.get(i).getCompanyName());
			b1.setCreditScore("" + creditAppList.get(i).getCreditScore());

			String date = "" + creditAppList.get(i).getCreationDate();
			b1.setCreationDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));

			b1.setApplicationStatus(ApplicationStatus.getApplicationStatus(creditAppList.get(i).getApplicationStatus()));
			borrowerCreditListDTO.add(b1);

		}

		return borrowerCreditListDTO;
	}

	@Override
	public ResponseModel assignCreditApplication(CreditApplication creditApp) {
		LOGGER.info("LenderServiceImpl :: assignCreditApplication");
		creditApplicationDAO.assignCreditApplication(creditApp);
		ResponseModel response = new ResponseModel();
		response.setMessage("Assigned Credit Application to Financial Analyst successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	@Override
	public CreditApplicationDetails viewCreditApplicationDetails(int userId, int applicationId) {
		LOGGER.info("LenderServiceImpl :: viewCreditApplicationDetails");
		CreditApplication creditApp = creditApplicationDAO.getCreditApplicationByAppId(applicationId);
		CreditApplicationDetails creditAppDetails = new CreditApplicationDetails();
		creditAppDetails.setApplicationId("APP" + creditApp.getApplicationId());
		creditAppDetails.setBorrowerEmail(userDAO.getUserEmailbyUserID(creditApp.getBorrowerId()));
		creditAppDetails.setBorrowerName(userDAO.getUserNamebyUserID(creditApp.getBorrowerId()));
		creditAppDetails.setCompanyName(creditApp.getCompanyName());
		creditAppDetails.setCreditScore("" + creditApp.getCreditScore());
		String date = "" + creditApp.getModificationDate();
		creditAppDetails.setDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));
		if (creditApp.getFinancialAnalystId() == 0) {
			creditAppDetails.setFinancialAnalyst("NA");
		} else {
			creditAppDetails.setFinancialAnalyst(userDAO.getUserNamebyUserID(creditApp.getFinancialAnalystId()));
		}

		if (creditApp.getApplicationStatus() == ApplicationConstant.STATUS_HOLD) {
			creditAppDetails.setApplicationStatus("ON HOLD");
		} else if (creditApp.getApplicationStatus() == ApplicationConstant.STATUS_ASSIGNED) {
			creditAppDetails.setApplicationStatus("ASSIGNED");
		} else if (creditApp.getApplicationStatus() == ApplicationConstant.STATUS_REJECT) {
			creditAppDetails.setApplicationStatus("REJECT");
		} else if (creditApp.getApplicationStatus() == ApplicationConstant.STATUS_APPROVED) {
			creditAppDetails.setApplicationStatus("APPROVED");
		}
		List<PolicyValues> policyValueLst = new ArrayList<>();
		List<MappingCreditAppPolicy> mappingCreditAppPolicyLst = mappingCreditAppPolicy.fetchPolicy(applicationId);
		for (int i = 0; i < mappingCreditAppPolicyLst.size(); i++) {
			PolicyValues policyValue = new PolicyValues();
			policyValue.setPolicyId(mappingCreditAppPolicyLst.get(i).getPolicyId());
			policyValue.setPolicyName(
					policyDAO.findPolicy(mappingCreditAppPolicyLst.get(i).getPolicyId()).getPolicyName());
			policyValue.setPolicyValue(mappingCreditAppPolicyLst.get(i).getPolicyValue());
			policyValueLst.add(policyValue);
		}
		creditAppDetails.setPolicyValues(policyValueLst);
		
		return creditAppDetails;
		
	}

}