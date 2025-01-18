package com.github.ahuljh.model;

import java.util.List;
import java.util.Map;

public class CommonSqlBeanResponse<T> {
    private Integer count;
    private List<T> beanList;

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
