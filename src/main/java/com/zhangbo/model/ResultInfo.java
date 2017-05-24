package com.zhangbo.model;


public class ResultInfo {
    private Integer httpCode;
    private String message;
    private Boolean success;

    public Integer getHttpCode() {
        return httpCode;
    }

    public ResultInfo setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ResultInfo setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
