package com.casestudy.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.PolicyDAO;
import com.casestudy.rms.dao.PolicyDetailDAO;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyDetail;
import com.casestudy.rms.service.PolicyService;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Provides services to Policy.
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	public static final Logger LOGGER = Logger.getLogger(PolicyServiceImpl.class);

	@Autowired
	private PolicyDetailDAO policyDetailDAO;

	@Autowired
	private PolicyDAO policyDAO;

	@Override
	public List<Policy> getPolicies(int userID) {

		LOGGER.info("PolicyServiceImpl :: getPolicies");
		List<Policy> allPolicyLst = policyDAO.getPolicy(userID);
		List<Policy> activePolicyList = new ArrayList<>();
		for (int i = 0; i < allPolicyLst.size(); i++) {
			PolicyDetail policyDetail = policyDetailDAO.findPolicyByID(allPolicyLst.get(i).getPolicyId(), userID);
			if (policyDetail.getPolicyStatus() == ApplicationConstant.POLICY_ACTIVE) {
				activePolicyList.add(allPolicyLst.get(i));
			}
		}

		return activePolicyList;
	}

}
