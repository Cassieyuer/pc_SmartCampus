package com.std.smartcampus.common.exception;

/**
 * Created by zsl-pc on 2016/9/8.
 */
public class ErrorInfo<T> {
    public static final String OK = "0";
    public static  final String ERROR = "-1";

    private String code;
    private String message;
    private String url;
    private String params;
    private T data;

    public String getCode() {
        return code;
    }

    public String getParams() {
        return params;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public T getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
