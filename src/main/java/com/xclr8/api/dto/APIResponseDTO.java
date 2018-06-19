package com.xclr8.api.dto;

/**
 * Created by shenju on 8/5/17.
 */
public class APIResponseDTO {
    
    private String code;

    private String message;

    private String status;

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

    @Override
    public String toString() {
        return "APIResponse{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
