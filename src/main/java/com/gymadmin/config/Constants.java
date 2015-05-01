package com.gymadmin.config;

/**
 * Application constants.
 */
public final class Constants {

    private Constants() {  }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_FAST = "fast";
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SYSTEM_ACCOUNT = "system";
    
    public static final String CUSTOMERS_IMAGE_PATH = "/assets/images/customers";
    
    public static final String PENDING_FILE_SESS_VAR = "pendingFile";
    
    public static final Integer PAYMENT_STATE_PENDING = 1;
    public static final Integer PAYMENT_STATE_ABOUT_TO_OVERDUE = 2;
    public static final Integer PAYMENT_STATE_PAID = 3;
    public static final Integer PAYMENT_STATE_OVERDUE = 4;
    public static final Integer PAYMENT_STATE_CANCELLED = 5;
    
    public static final String SYSPARAM_DUE_DATE_MONTH_DAY = "dueDateMonthDay";

}
