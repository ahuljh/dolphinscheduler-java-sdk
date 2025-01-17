package com.github.ahuljh.common;

public enum RequestMethodEnum {
    GET("GET"),POST("POST"),DELETE("DELETE"),PUT("PUT");
    private String value;

    RequestMethodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
