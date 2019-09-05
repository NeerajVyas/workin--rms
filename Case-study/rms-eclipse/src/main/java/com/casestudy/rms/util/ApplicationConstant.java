package com.casestudy.rms.util;

/** Contains Application Constants. */
public final class ApplicationConstant {

    public static final int NOT_APPROVED = 0;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 2;
    public static final int ADMIN_ID = 0;
    public static final int POLICY_ACTIVE = 1;
    public static final int POLICY_INACTIVE = 0;
    public static final int DEFAULT_POLICY_WEIGHTAGE = 25;
    public static final String DEFAULT_POLICY_THRESHOLD = "" + 0;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;
    public static final int STATUS_APPROVED = 3;
    public static final int STATUS_REJECT = 2;
    public static final int STATUS_ASSIGNED = 1;
    public static final int STATUS_HOLD = 0;
    public static final int INITIAL_CREDIT_SCORE = 0;
    public static final int APP_REJECT_LOWER_LIMIT = 0;
    public static final int APP_REJECT_UPPER_LIMIT = 30;
    public static final int APP_HOLD_UPPER_LIMIT = 60;
    public static final int APP_HOLD_LOWER_LIMIT = 31;
    public static final int TOP_CREDIT_SCORE_TABLE_LENGTH = 10;
    public static final int APP_ID_SUBSTRING=3;
    public static final int DATE_SUBSTRING=10;
    public static final String PROBLEM_OCCURED_MSG="Problem Occured, Try after some time";
    public static final String NO_RESULT="No Data Found";
    public static final String ANGULAR_URL="http://172.29.57.59:4200";
    public static final int CREDIT_APP_REJECT = 2;
    public static final int CREDIT_APP_HOLD = 0;
    public static final int CREDIT_APP_APPROVED = 3;
   

    /** Private Constructor. */
    private ApplicationConstant() {

    }

}
