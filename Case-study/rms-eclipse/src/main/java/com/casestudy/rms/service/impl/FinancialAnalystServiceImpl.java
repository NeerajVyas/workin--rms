package com.casestudy.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casestudy.rms.dao.CreditAppDAO;
import com.casestudy.rms.dao.FinancialAnalystDAO;
import com.casestudy.rms.dao.MappingCreditAppPolicyDAO;
import com.casestudy.rms.dao.PolicyDAO;
import com.casestudy.rms.dao.PolicyDetailDAO;
import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.dto.FinancialAnalystReceivedCreditAppDTO;
import com.casestudy.rms.dto.FinancialAnalystTopBorrowerDTO;
import com.casestudy.rms.dto.FinancialAnalystViewDetailsDTO;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.MappingCreditAppPolicy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.FinancialAnalystService;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Provides Services to Financial Analyst.
 */
@Service
public class FinancialAnalystServiceImpl implements FinancialAnalystService {

	public static final Logger LOGGER = Logger.getLogger(FinancialAnalystServiceImpl.class);

	@Autowired
	private CreditAppDAO creditAppDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PolicyDAO policyDAO;

	@Autowired
	private PolicyDetailDAO policyDetailDAO;

	@Autowired
	private MappingCreditAppPolicyDAO mappingCreditAppDAO;

	@Autowired
	private FinancialAnalystDAO financialAnalystDAO;

	@Override
	public List<FinancialAnalystReceivedCreditAppDTO> fetchCreditApp(int faid) {

		LOGGER.info("FinancialAnalystServiceImpl :: fetchCreditApp");

		List<CreditApplication> listCreditApp = creditAppDAO.getCreditApplication(faid,
				ApplicationConstant.STATUS_ASSIGNED);

		List<FinancialAnalystReceivedCreditAppDTO> listFAReceivedCreditAppDTO = new ArrayList<>();

		for (int i = 0; i < listCreditApp.size(); i++) {
			FinancialAnalystReceivedCreditAppDTO temp = new FinancialAnalystReceivedCreditAppDTO();
			temp.setApplicationNumber("APP" + listCreditApp.get(i).getApplicationId());
			temp.setCompanyName(listCreditApp.get(i).getCompanyName());
			temp.setCreditScore(listCreditApp.get(i).getCreditScore());
			temp.setBorrowerName(userDAO.getUserNamebyUserID(listCreditApp.get(i).getBorrowerId()));
			temp.setBorrowerEmail(userDAO.getUserEmailbyUserID(listCreditApp.get(i).getBorrowerId()));

			listFAReceivedCreditAppDTO.add(temp);
		}

		return listFAReceivedCreditAppDTO;
	}

	@Override
	public FinancialAnalystViewDetailsDTO fetchDetailsforFinancialAnalyst(int faid, int appId, String borrowerEmail) {

		LOGGER.info("FinancialAnalystServiceImpl :: fetchDetailsforFinancialAnalyst");
		int lenderId = financialAnalystDAO.fetchLenderId(faid);

		List<MappingCreditAppPolicy> mappingCreditAppPolicy = mappingCreditAppDAO.fetchPolicy(appId);

		List<PolicyDetail> policyDetail = policyDetailDAO.viewpolicyDetail(lenderId);

		FinancialAnalystViewDetailsDTO faViewDetailsDTO = new FinancialAnalystViewDetailsDTO();

		String lenderEmail = userDAO.getUserEmailbyUserID(lenderId);

		CreditApplication creditApp = creditAppDAO.getCreditApplicationByAppId(appId);
		String applicationStatus = "" + creditApp.getApplicationStatus();
		String applicationCreditScore = "" + creditApp.getCreditScore();

		List<String> policyIdList = new ArrayList<>();
		List<String> policyValueList = new ArrayList<>();
		List<String> policyNameList = new ArrayList<>();
		List<String> policyThresholdList = new ArrayList<>();
		List<String> policyWeightageList = new ArrayList<>();

		for (int i = 0; i < policyDetail.size(); i++) {
			if (policyDetail.get(i).getPolicyStatus() == 1) {
				policyThresholdList.add("" + policyDetail.get(i).getThreshold());
				policyWeightageList.add("" + policyDetail.get(i).getPolicyWeightage());
			}
		}

		for (int i = 0; i < mappingCreditAppPolicy.size(); i++) {
			policyIdList.add("" + mappingCreditAppPolicy.get(i).getPolicyId());
			policyValueList.add("" + mappingCreditAppPolicy.get(i).getPolicyValue());
			policyNameList.add("" + policyDAO.findPolicy(mappingCreditAppPolicy.get(i).getPolicyId()).getPolicyName());
		}

		faViewDetailsDTO.setLenderEmail(lenderEmail);
		faViewDetailsDTO.setBorrowerEmail(borrowerEmail);
		faViewDetailsDTO.setApplicationNumber("" + appId);
		faViewDetailsDTO.setApplicationStatus(applicationStatus);
		faViewDetailsDTO.setApplicationCreditScore(applicationCreditScore);
		faViewDetailsDTO.setLenderID("" + lenderId);
		faViewDetailsDTO.setPolicyId(policyIdList);
		faViewDetailsDTO.setPolicyValuebyBorrower(policyValueList);
		faViewDetailsDTO.setPolicyNamebyLender(policyNameList);
		faViewDetailsDTO.setPolicyThresholdbyLender(policyThresholdList);
		faViewDetailsDTO.setPolicyWeightagebyLender(policyWeightageList);

		return faViewDetailsDTO;
	}

	@Override
	public List<FinancialAnalystTopBorrowerDTO> getTopBorrower(int financialAnalystId) {
		LOGGER.info("FinancialAnalystServiceImpl :: getTopBorrower");
		List<FinancialAnalystTopBorrowerDTO> topBorrowerLst = new ArrayList<>();
		Lender lender = null;
		List<FinancialAnalyst> allFinancialAnalyst = financialAnalystDAO.viewFinancialAnalyst();
		for (int i = 0; i < allFinancialAnalyst.size(); i++) {
			if (allFinancialAnalyst.get(i).getUserId() == financialAnalystId) {
				lender = allFinancialAnalyst.get(i).getLender();
				break;
			}
		}
		List<CreditApplication> creditApplications = creditAppDAO.getTopBorrowerByLenderID(lender.getUserId());
		for (int i = 0; i < creditApplications.size(); i++) {
			FinancialAnalystTopBorrowerDTO topBorrower = new FinancialAnalystTopBorrowerDTO();
			topBorrower.setUserName(userDAO.getUserNamebyUserID(creditApplications.get(i).getBorrowerId()));
			topBorrower.setUserEmail(userDAO.getUserEmailbyUserID(creditApplications.get(i).getBorrowerId()));
			topBorrower.setCompanyName(creditApplications.get(i).getCompanyName());
			topBorrower.setCreditScore(creditApplications.get(i).getCreditScore());
			topBorrowerLst.add(topBorrower);

		}

		return topBorrowerLst;
	}

}
