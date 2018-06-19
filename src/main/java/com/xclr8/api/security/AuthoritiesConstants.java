/**
 * AuthoritiesConstant
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String PATIENT = "ROLE_PATIENT";

    public static final String DOCTOR = "ROLE_DOCTOR";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String RELATIVE = "ROLE_RELATIVE";

    private AuthoritiesConstants() {
    }
}
