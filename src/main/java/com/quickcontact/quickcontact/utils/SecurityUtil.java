package com.quickcontact.quickcontact.utils;

public class SecurityUtil {
    public static final String USER = "USER";

    public static final String[] PERMITTED_ALL_PATHS = new String[] {
            "/api/rest/auth/login",
            "/api/customers/add"
    };

    public static final String[] USER_PATHS = new String[] {
            "/api/customers/all"
    };

}
