package com.casestudy.rms.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.BorrowerViewLenderDTO;
import com.casestudy.rms.dto.CreditApplicationDetails;
import com.casestudy.rms.dto.CreditPolicyMappingDTO;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.PolicyValues;
import com.casestudy.rms.exception.PolicyInputInvalidException;
import com.casestudy.rms.exception.UserInputInvalidException;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.model.User;

public class RmsUtil {
	
	public static final Logger LOGGER = Logger.getLogger(RmsUtil.class);
	 
	private RmsUtil() {}
	public static boolean validateUser(User user) {
		
		if (user.getUserName().isEmpty()) {
			LOGGER.error("UserValidate :: validateBorrower :: Name cannot be Empty");
			throw new UserInputInvalidException("Name cannot be empty");
		} else if (!user.getUserEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			LOGGER.error("UserValidate :: validateBorrower :: Email is not valid");
			throw new UserInputInvalidException("Email cannot be empty or Email is invalid");
		}
		return true; 
		
	}
	
	public static PolicyDetail setPolicyDetail(int lenderId,int policyId) {
		PolicyDetail policyDetail = new PolicyDetail();
		policyDetail.setPolicyId(policyId);
		policyDetail.setPolicyStatus(ApplicationConstant.POLICY_INACTIVE);
		policyDetail.setPolicyWeightage(ApplicationConstant.DEFAULT_POLICY_WEIGHTAGE);
		policyDetail.setThreshold(ApplicationConstant.DEFAULT_POLICY_THRESHOLD);
		policyDetail.setLenderId(lenderId);
		
		return policyDetail;
	}
	
	public static boolean validatePolicy(PolicyResponse policyResponse) {
		
		if (policyResponse.getPolicyName().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Name cannot be Empty");
			throw new PolicyInputInvalidException("Policy Name cannot be empty");
		} else if (policyResponse.getThreshold().isEmpty()) {
			LOGGER.error("LenderServiceImpl :: addPolicy :: Policy Threshold value cannot be Empty");
			throw new PolicyInputInvalidException("Policy Threshold value cannot be empty");
		}
		return true; 
		
	}

	public static Policy setPolicy(PolicyResponse policyResponse, int lenderId) {
		Policy policy = new Policy();
		policy.setPolicyName(policyResponse.getPolicyName());
		policy.setAddedBy(lenderId);
		return policy;
	}
	
	public static PolicyDetail setPolicyDetail(int lenderId,PolicyResponse policyResponse,int policyId) {
		PolicyDetail policyDetail = new PolicyDetail();
		policyDetail.setLenderId(lenderId);
		policyDetail.setPolicyWeightage(policyResponse.getPolicyWeightage());
		policyDetail.setThreshold(policyResponse.getThreshold());
		policyDetail.setPolicyStatus(ApplicationConstant.POLICY_ACTIVE);
		policyDetail.setPolicyId(policyId);
		
		return policyDetail;
	}
	
	public static List<PolicyResponse> viewPolicyList(List<Policy> policy,List<PolicyDetail> policydetail){
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
		return policyResponseLst;
		
	}
	
	
	public static List<BorrowerViewLenderDTO> activeLenderList(List<Lender> lenderLst){
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
	
	public static List<BorrowerAllCreditAppDTO> getCreditApplication(List<CreditApplication> creditAppList){
		List<BorrowerAllCreditAppDTO> borrowerCreditListDTO = new ArrayList<>();
		for (int i = 0; i < creditAppList.size(); i++) {
			BorrowerAllCreditAppDTO b1 = new BorrowerAllCreditAppDTO();
			b1.setApplicationId("APP" + creditAppList.get(i).getApplicationId());
			b1.setCompanyName(creditAppList.get(i).getCompanyName());
			b1.setCreditScore("" + creditAppList.get(i).getCreditScore());
			String date = "" + creditAppList.get(i).getCreationDate();
			b1.setCreationDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));
			b1.setApplicationStatus(
					ApplicationStatus.getApplicationStatus(creditAppList.get(i).getApplicationStatus()));
			borrowerCreditListDTO.add(b1);
		}
		return borrowerCreditListDTO;
	}
	
	public static CreditApplicationDetails	getCreditApplicationDetails(String userName, String financialAnalystName, String userEmail, CreditApplication creditApp ) {
		CreditApplicationDetails creditAppDetails = new CreditApplicationDetails();
		creditAppDetails.setApplicationId("APP" + creditApp.getApplicationId());
		creditAppDetails.setBorrowerEmail(userEmail);
		creditAppDetails.setBorrowerName(userName);
		creditAppDetails.setCompanyName(creditApp.getCompanyName());
		creditAppDetails.setCreditScore("" + creditApp.getCreditScore());
		String date = "" + creditApp.getModificationDate();
		creditAppDetails.setDate(date.substring(0, ApplicationConstant.DATE_SUBSTRING));
		creditAppDetails.setFinancialAnalyst(financialAnalystName);
		creditAppDetails.setApplicationStatus(ApplicationStatus.getApplicationStatus(creditApp.getApplicationStatus()));
	
		return creditAppDetails;
	}
	
	public static PolicyValues setPolicyValues(MappingCreditAppPolicy mappingCreditAppPolicy, String policyName) {
		PolicyValues policyValue = new PolicyValues();
		policyValue.setPolicyId(mappingCreditAppPolicy.getPolicyId());
		policyValue.setPolicyName(policyName);
		policyValue.setPolicyValue(mappingCreditAppPolicy.getPolicyValue());
		return policyValue;
	}
	
	public static int checkCreditApplicationStatus(int creditScore) {
		int status;
		if (creditScore >= ApplicationConstant.APP_REJECT_LOWER_LIMIT
				&& creditScore <= ApplicationConstant.APP_REJECT_UPPER_LIMIT) {
			status = ApplicationConstant.STATUS_REJECT;
		} else if (creditScore >= ApplicationConstant.APP_HOLD_LOWER_LIMIT
				&& creditScore <= ApplicationConstant.APP_HOLD_UPPER_LIMIT) {
			status = ApplicationConstant.STATUS_HOLD;
		} else {
			status = ApplicationConstant.STATUS_APPROVED;
		}

		return status;
	}
	
	public static CreditApplication setCreditApplicationDetail(CreditPolicyMappingDTO creditApplication, int borrowerId) {
		CreditApplication creditApp = new CreditApplication();
		creditApp.setCreditScore(ApplicationConstant.INITIAL_CREDIT_SCORE);
		creditApp.setApplicationStatus(ApplicationConstant.STATUS_HOLD);
		creditApp.setCompanyName(creditApplication.getCompanyName());
		creditApp.setBorrowerId(borrowerId);
		creditApp.setLenderId(creditApplication.getLenderId());
		creditApp.setFinancialAnalystId(0);
		return creditApp;
	}
	
	
}
