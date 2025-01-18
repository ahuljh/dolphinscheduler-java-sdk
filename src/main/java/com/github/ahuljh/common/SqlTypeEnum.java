package com.github.ahuljh.common;

public enum SqlTypeEnum {
    SELECT("SELECT");
    private String value;

    SqlTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
