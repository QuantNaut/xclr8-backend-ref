/**
 * APIResponseForUser
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */

package com.xclr8.api.dto;

import com.xclr8.api.domain.User;

import java.util.List;
import java.util.Optional;

public class APIResponseForUserDTO {

    private String code;

    private String message;

    private String status;

    private List<User> data;

    private String token;

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List <User> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "APIResponseForUser{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", status='" + status + '\'' +
            ", data='" + data + '\'' +
            ", token='" + token + '\'' +
            "}"  + super.toString();
    }
}
