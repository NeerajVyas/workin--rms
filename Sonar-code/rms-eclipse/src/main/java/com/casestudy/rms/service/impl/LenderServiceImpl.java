package com.casestudy.rms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.casestudy.rms.util.RmsUtil;

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
		if (!RmsUtil.validateUser(lender)) {
			throw new UserInputInvalidException("Lender details are incorrect");
		} else if (lenderDAO.lenderExists(lender)) {
			LOGGER.error("LenderServiceImpl :: registerLender :: User already exits");
			throw new UserExistsException("User already exists");
		} else {
			lender.setUserAIStatus(ApplicationConstant.ACTIVE);
			lender.setUserRole("ROLE_LENDER");
			lenderDAO.registerLender(lender);
			int lenderId = lenderDAO.getMaxLenderId();
			List<Integer> defaultPolicyIdLst = policyDAO.getDefaultPolicyId();
			for (int i = 0; i < defaultPolicyIdLst.size(); i++) {
				policyDetailDAO.addPolicyDetail(RmsUtil.setPolicyDetail(lenderId, defaultPolicyIdLst.get(i)));
			}
			ResponseModel response = new ResponseModel();
			response.setMessage("Lender registered successfully");
			response.setStatus(HttpStatus.CREATED.value());
			return response;

		}

	}

	@Override
	public ResponseModel addFinancialAnalyst(FinancialAnalyst financialAnalyst, Lender lender) {
		LOGGER.info("LenderServiceImpl :: addFinancialAnalyst");
		if (!RmsUtil.validateUser(financialAnalyst)) {
			throw new UserInputInvalidException("Financial Analyst details are incorrect");
		} else if (financialAnalystDAO.financialAnalystExist(financialAnalyst)) {
			LOGGER.error("LenderServiceImpl :: addFinancialAnalyst :: User already exits");
			throw new UserExistsException(" User already exists");
		} else {
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
			return response;

		}
	}
	
	@Override
	public List<BorrowerViewLenderDTO> getActiveLenders() {
		LOGGER.info("LenderServiceImpl :: getActiveLenders");
		List<Lender> lenderLst = lenderDAO.getLenderWithStatus(ApplicationConstant.ACTIVE);
		return RmsUtil.activeLenderList(lenderLst);
	}

	@Override
	public List<BorrowerAllCreditAppDTO> getCreditApplication(int lenderId) {
		LOGGER.info("LenderServiceImpl :: getCreditApplication");
		List<CreditApplication> creditAppList = lenderDAO.getCreditApplication(lenderId);
		return RmsUtil.getCreditApplication(creditAppList);
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
	public Lender getLender(int lenderId) {
		LOGGER.info("LenderServiceImpl :: getLender");
		return lenderDAO.getLender(lenderId);
	}

	@Override
	public ResponseModel addPolicy(PolicyResponse policyResponse, Lender lender) {
		LOGGER.info("LenderServiceImpl :: addPolicy");
		if(!RmsUtil.validatePolicy(policyResponse)) {
			throw new PolicyInputInvalidException("Policy Details are incorrect");
		}else if (policyDAO.policyNameExist(lender.getUserId(), policyResponse.getPolicyName())) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Already Exists");
			throw new PolicyExistsException("Policy Already Exists");
		} else {
			policyDAO.addPolicy(RmsUtil.setPolicy(policyResponse,lender.getUserId()));
            int policyId = policyDAO.getMaxPolicyId();
			policyDetailDAO.addPolicyDetail(RmsUtil.setPolicyDetail(lender.getUserId(), policyResponse, policyId));
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
		List<PolicyResponse> policyResponseLst = RmsUtil.viewPolicyList(policy, policydetail);
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
	public CreditApplicationDetails viewCreditApplicationDetails(int userId, int applicationId) {
		LOGGER.info("LenderServiceImpl :: viewCreditApplicationDetails");
		CreditApplication creditApp = creditApplicationDAO.getCreditApplicationByAppId(applicationId);
		List<MappingCreditAppPolicy> mappingCreditAppPolicyLst = mappingCreditAppPolicy.fetchPolicy(applicationId);
		String userEmail = userDAO.getUserEmailbyUserID(creditApp.getBorrowerId());
		String userName = userDAO.getUserNamebyUserID(creditApp.getBorrowerId());
		String financialAnalystName= "NA";
		if(creditApp.getFinancialAnalystId() != 0) {
			financialAnalystName= userDAO.getUserNamebyUserID(creditApp.getFinancialAnalystId());
		}
		CreditApplicationDetails creditAppDetails = RmsUtil.getCreditApplicationDetails(userName, financialAnalystName, userEmail, creditApp);
		List<PolicyValues> policyValueLst = new ArrayList<>();
		
		for (int i = 0; i < mappingCreditAppPolicyLst.size(); i++) {
			String policyName =  policyDAO.findPolicy(mappingCreditAppPolicyLst.get(i).getPolicyId()).getPolicyName();
			policyValueLst.add(RmsUtil.setPolicyValues(mappingCreditAppPolicyLst.get(i), policyName));
		}
		creditAppDetails.setPolicyValues(policyValueLst);

		return creditAppDetails;

	}

}