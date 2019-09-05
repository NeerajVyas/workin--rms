/* export types
named    import  {Links} from './../links.module';
default.  import  Links from './../links.module';
*/

export default class Links{

public static base ='http://172.29.57.59:8080/rms/'; 
public static baseAuth ='http://172.29.57.59:8080/authenticate'; 

public static FA_Credit_HOLD = Links.base+'financial-analyst/view-credit-app-form?faid=';
public static ACITVE_LENDER_URL =Links.base+"lender/ActiveLenders";
public static REGISTER_LENDER_URL=Links.base+"lender/registerlender";
public static SUBMIT_CREDITAPP_URL = Links.base+"submitcreditappform?id=";
public static LENDERHOME_GET_POLICIES_URL = Links.base + "lender/policies";
public static LENDER_ADD_FA_URL=Links.base+'lender/addFinancialAnalyst?id=';
public static LENDER_VIEW_POLICY_URL=Links.base+'lender/viewPolicy?id=';
public static LENDER_VIEW_FA_URL=Links.base+'lender/viewFinancialAnalyst?id=';
public static LENDER_ADD_POLICY_URL=Links.base+'lender/addPolicy?id=';
public static LENDER_UPDATE_POLICY_URL=Links.base+'lender/updatePolicyDetail?id=';
public static LENDER_VIEW_CREDIT_APPLICATION_URL=Links.base+'lender/getCreditApplication?id=';
public static LENDER_ASSIGN_CREDIT_APP=Links.base+'lender/assignCA?id=';
public static LENDER_VIEW_MORE_DETAILS_URL=Links.base+'lender/viewDetails?id=';
//&appId=

public static FA_TOP_BORROWERS_URL=Links.base+'financial-analyst/top-ten-borrower?faid=';
public static FA_VIEW_MORE_DETAILS_URL = Links.base+'financial-analyst/view-more-details?faId=';
public static CREDIT_APPLICATYION_RECORDS=Links.base+'creditScore?id=';
public static FA_PREVIOUS_CREDIT_URL=Links.base+'financial-analyst/view-previous-updated-application?faId=';

public static ADMIN_ACTIVE_LENDERS_URL=Links.base+'viewLenders/ActiveLenders';
public static ADMIN_INACTIVE_LENDERS_URL=Links.base+'viewLenders/InactiveLenders';
public static ADMIN_DEACTIVATE_LENDERS_URL=Links.base+'deactivateLender?id=';
public static ADMIN_ACTIVATE_LENDERS_URL=Links.base+'activateLender?id=';

public static FORGOT_PASS_EMAIL=Links.base+'forgotPassword/user?emailId=';
public static FORGOT_PASS_OTP_VERIFY=Links.base+'forgotPassword/checkOTP';
public static FORGOT_PASS_SET_NEW_PASS=Links.base+'forgotPassword/createnewpassword';




public static ADMIN_ACTIVE_BORROWERS_URL=Links.base+'viewBorrowers/ActiveBorrowers';
public static ADMIN_INACTIVE_BORROWERS_URL=Links.base+'viewBorrowers/InactiveBorrowers';
public static ADMIN_DEACTIVATE_BORROWERS_URL=Links.base+'deactivateBorrower?id=';
public static ADMIN_ACTIVATE_BORROWERS_URL=Links.base+'activateBorrower?id='; 


}