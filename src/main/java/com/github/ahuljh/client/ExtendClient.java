package com.github.ahuljh.client;

import java.util.Collections;
import java.util.Map;

/**
 * 可以自定义引入外部依赖，详情可以查看另外的分支
 * 思考：sdk-java/sdk-python……
 */
public class ExtendClient implements Client{
    @Override
    public Map<String, Object> getBasicInfo() {
        return Collections.emptyMap();
    }
}
