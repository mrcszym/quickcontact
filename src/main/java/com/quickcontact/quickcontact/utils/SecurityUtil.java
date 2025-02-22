package com.quickcontact.quickcontact.utils;

public class SecurityUtil {
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    public static final String[] PERMITTED_ALL_PATHS = new String[] {
            "/api/rest/auth/**",
            "/api/customers/add",
            "/api/stickers/**",
    };

    public static final String[] CUSTOMER_PATHS = new String[] {
            "/api/customers/**",
            "/api/messages/**"
    };

}
