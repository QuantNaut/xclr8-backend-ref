/**
 * ParameterizedErrorVM
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.web.rest.errors;

import java.io.Serializable;

/**
 * View Model for sending a parameterized error message.
 */
public class ParameterizedErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String message;
    private final String[] params;

    public ParameterizedErrorVM(String message, String... params) {
        this.message = message;
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public String[] getParams() {
        return params;
    }

}
