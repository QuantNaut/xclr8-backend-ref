package com.xclr8.api.dto;

/**
 * Created by shenju on 21/8/17.
 */
public class StatusResponse<T> {

    private int code;

    private String status;

    private String message;

    private T data;

    public StatusResponse(int code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public StatusResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public StatusResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
            "code=" + code +
            ", status='" + status + '\'' +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
    }
}
