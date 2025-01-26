package com.quickcontact.quickcontact.utils;

public class SecurityUtil {
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    public static final String[] PERMITTED_ALL_PATHS = new String[] {
            "/api/rest/auth/login",
            "/api/customers/add"
    };

    public static final String[] CUSTOMER_PATHS = new String[] {
            "/api/customers/all"
    };

}
