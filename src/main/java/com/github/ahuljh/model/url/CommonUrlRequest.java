package com.github.ahuljh.model.url;

import java.util.HashMap;
import java.util.Map;

public class CommonUrlRequest {
    private String address;
    private String requestMethod;
    private Object requestBody;
    private Map<String, Object> requestParam = new HashMap<>();
    private Map<String, String> header = new HashMap<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, Object> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Map<String, Object> requestParam) {
        this.requestParam = requestParam;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }


}
