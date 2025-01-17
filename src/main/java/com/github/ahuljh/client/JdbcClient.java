package com.github.ahuljh.client;

import java.util.Collections;
import java.util.Map;

public class JdbcClient implements Client{
    @Override
    public Map<String, Object> getBasicInfo() {
        return Collections.emptyMap();
    }

    // 思路：传递datasource，构建client

}
