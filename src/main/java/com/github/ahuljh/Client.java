package com.github.ahuljh;

import java.util.Map;

public interface Client {
    /**
     * 查看DS集群的基本信息
     * @return
     */
    Map<String, Object> getBasicInfo();

}
