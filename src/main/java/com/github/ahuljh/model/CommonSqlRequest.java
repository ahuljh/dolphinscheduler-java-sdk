package com.github.ahuljh.model;

import javax.sql.DataSource;

public class CommonSqlRequest<T> {
    private String sqlType;
    private String sqlStatement; // sql语句
    private Object[] paramArray;
    private Class<T> type;


    public Object[] getParamArray() {
        return paramArray;
    }

    public void setParamArray(Object[] paramArray) {
        this.paramArray = paramArray;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }


}
