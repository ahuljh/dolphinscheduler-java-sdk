package com.github.ahuljh.model;

import java.util.List;
import java.util.Map;

public class CommonSqlMapResponse {
    private Integer count;
    private List<Map<String, Object>> resultList;
    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
