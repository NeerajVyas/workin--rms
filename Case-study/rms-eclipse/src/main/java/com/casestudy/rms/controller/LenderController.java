package com.casestudy.rms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dto.BorrowerAllCreditAppDTO;
import com.casestudy.rms.dto.BorrowerViewLenderDTO;
import com.casestudy.rms.dto.CreditApplicationDetails;
import com.casestudy.rms.dto.FinancialAnalystResponse;
import com.casestudy.rms.dto.PolicyResponse;
import com.casestudy.rms.dto.ResponseModel;
import com.casestudy.rms.model.CreditApplication;
import com.casestudy.rms.model.FinancialAnalyst;
import com.casestudy.rms.model.Lender;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.service.LenderService;
import com.casestudy.rms.service.PolicyService;
import com.casestudy.rms.service.impl.EmailServiceImpl;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * Represents Lender Controller.
 */
@RestController
@RequestMapping("/rms/lender")
@CrossOrigin(origins = {ApplicationConstant.ANGULAR_URL})
public class LenderController {

	public static final Logger LOGGER = Logger.getLogger(LenderController.class);

	@Autowired
	private LenderService lenderService;
	
	@Autowired
	private EmailServiceImpl emailService;
	

    @Autowired
    private PolicyService policyService;

	/**
	 * Register a Lender.
	 * 
	 * @param lender - Lender
	 * @return Status
	 */
	@PostMapping(value = "/registerlender", consumes = "application/JSON")
	public ResponseEntity<ResponseModel> registerLender(@RequestBody Lender lender) {
		LOGGER.info("LenderController :: registerLender ");
		ResponseModel response = lenderService.registerLender(lender);
		emailService.sendSimpleMessage(lender.getUserEmail(),"RMS","Dear "+lender.getUserName()+",\nYou have been registered successfully.");
		return new ResponseEntity<>(response,HttpStatus.CREATED);

	}

	/**
	 * Add Financial Analysts.
	 * 
	 * @param financialAnalyst - Financial Analyst
	 * @param id               - Lender ID
	 * @return Status
	 */
	@PostMapping(value = "/addFinancialAnalyst", consumes = "application/JSON")
	public ResponseEntity<ResponseModel> addFinancialAnalyst(@RequestBody FinancialAnalyst financialAnalyst,@RequestParam("id") int id) {
	    LOGGER.info("LenderController :: addFinancialAnalyst ");
	    String password =  financialAnalyst.getUserPassword();
	   	Lender lender = lenderService.getLender(id);
	   	ResponseModel response = lenderService.addFinancialAnalyst(financialAnalyst, lender);
	   	emailService.sendSimpleMessage(financialAnalyst.getUserEmail(),"RMS","Dear "+financialAnalyst.getUserName()+",\nYou have been added as Financial Analyst by "+lender.getUserName()+"\nYour Login credentials are:\nEmail ID : "+financialAnalyst.getUserEmail()+"\nPassword : "+password);
	   	return new ResponseEntity<>(response,HttpStatus.CREATED);

	}
	
	 /** Add new policy.
     * 
     * @param policyResponse
     *            - DTO contains policy detail
     * @param id
     *            - Lender ID
     * @return Status */
    @PostMapping(value = "/addPolicy", consumes = "application/JSON")
    public ResponseEntity<ResponseModel> addPolicy(@RequestBody PolicyResponse policyResponse, @RequestParam("id") int id) {
        LOGGER.info("LenderController :: addPolicy ");
        Lender lender = lenderService.getLender(id);
        ResponseModel response = lenderService.addPolicy(policyResponse, lender);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
       
         
    }

    /** View default policies detail and policies detail corresponding lender.
     * 
     * @param id
     *            - Lender ID
     * @return Status */
    @GetMapping("/viewPolicy")
    public ResponseEntity<List<PolicyResponse>> viewPolicy(@RequestParam("id") int id) {
        LOGGER.info("LenderController :: viewPolicy ");
        List<PolicyResponse> policy = lenderService.viewPolicy(id);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    /** Update policies detail corresponding to lender.
     * 
     * @param policyResponses
     *            - Array of different policies detail
     * @param id
     *            - Lender ID
     * @return Status */
    @PostMapping(value = "/updatePolicyDetail", consumes = "application/JSON")
    public ResponseEntity<ResponseModel> updatePolicyDetails(@RequestBody PolicyResponse[] policyResponses, @RequestParam("id") int id) {
        LOGGER.info("LenderController :: updatePolicyDetails ");
        Lender lender = lenderService.getLender(id);
        ResponseModel response = lenderService.updatePolicyDetail(policyResponses, lender);
        return new ResponseEntity<>(response,HttpStatus.OK);
      
    }
    
    /** Returns list all financial analysts with their pending requests of respective lender.
     * 
     * @param id
     *            Lender ID
     * @return response entity */
    @GetMapping("/viewFinancialAnalyst")
    public ResponseEntity<List<FinancialAnalystResponse>> viewFinancialAnalyst(@RequestParam("id") int id) {
        LOGGER.info("LenderController :: viewFinancialAnalyst ");
        List<FinancialAnalystResponse> financialAnalystLst = lenderService.viewFinancialAnalyst(id);
        return new ResponseEntity<>(financialAnalystLst, HttpStatus.OK);
    }

    /**
     * Gives list of Active Lenders.
     * @return List of Active Lenders
     */
    @GetMapping("/ActiveLenders")
    public ResponseEntity<List<BorrowerViewLenderDTO>> getActiveLenders(){
        LOGGER.info("LenderController :: getActiveLenders ");
        List<BorrowerViewLenderDTO> lst = lenderService.getActiveLenders();
        return new ResponseEntity<>(lst,HttpStatus.OK);
        
    }
    
    /**
     * View Active Policies corresponding to Lender.
     * @param lender - lender
     * @return List of Policy
     */
    @PostMapping("/policies")
    public ResponseEntity<List<Policy>> getPolicies(@RequestBody Lender lender){
        LOGGER.info("LenderController :: getPolicies ");
        List<Policy> lst = policyService.getPolicies(lender.getUserId());
        return new ResponseEntity<>(lst,HttpStatus.OK);
        
    }
    
    
    /** Returns details of credit application requests of respective lender.
     * 
     * @param id
     *            int
     * @return response entity */
    @GetMapping("/getCreditApplication")
    public ResponseEntity<List<BorrowerAllCreditAppDTO>> getCreditApplication(@RequestParam("id") int id) {
        LOGGER.info("LenderController :: getCreditApplication ");
        List<BorrowerAllCreditAppDTO> creditApplication = lenderService.getCreditApplication(id);
        return new ResponseEntity<>(creditApplication, HttpStatus.OK);
    }
    
    /** Assigns particular credit application to selected financial analyst.
     * 
     * @param creditApp
     *            CreditApplication
     * @param id
     *           Lender ID
     * @return response entity */
    @PostMapping(value = "/assignCA", consumes = "application/JSON")
    public ResponseEntity<ResponseModel> assignCreditApplication(@RequestBody CreditApplication creditApp, @RequestParam("id") int id) {
        LOGGER.info("LenderController :: assignCreditApplication ");
        ResponseModel response = lenderService.assignCreditApplication(creditApp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * View Credit Application details.
     * @param lenderId - Lender ID
     * @param appId - Application ID
     * @return Details of Credit Application
     */
    @GetMapping(value="/viewDetails")
    public ResponseEntity<CreditApplicationDetails> viewDetails(@RequestParam("id") int lenderId, @RequestParam("appId") String appId){
    	LOGGER.info("LenderController :: viewDetails ");
    	int applicationId = Integer.parseInt(appId.substring(ApplicationConstant.APP_ID_SUBSTRING));
    	CreditApplicationDetails creditAppDetails = lenderService.viewCreditApplicationDetails(lenderId, applicationId);
    	return new ResponseEntity<>(creditAppDetails, HttpStatus.OK);
    }

}


