# 1.项目介绍

dolphinscheduler-java-sdk是一款兼容Dolphinscheduler不同版本的java sdk
支持通过maven方式引入，使用Token/Session/JDBC等多种方式和DS的API角色/数据库实现交互

支持下列方式初始化Client对象：
- [x] java面向对象的方式
- [x] Spring创建Bean的方式


## 2. 背景和解决方案

| 痛点/需求                 | 我们的方案                | 自己维护调用方式          |
|-----------------------|----------------------|-------------------|
| java/Spring代码内部调用DS接口 | 提供统一的sdk，支持不同项目复用    | 各个项目自己单独封装方法      |
| DS的版本差异大              | 支持各类版本使用             | 需要针对不同版本单独开发/适配   |
| 统一参数信息需要每次传递  | 支持Bean/对象方式一次创建，多次使用 | 每次传递host、token等参数 | 


# 3. 使用
## 3.1 依赖加载
### （1）生成jar包
- jar包方式
- 编译构建

### （2）个人使用
直接引入

### （3）公司使用
上传私服


## 3.2 代码调用
### Java方式调用


### Bean方式调用
- TokenClient
```java
package com.github.ahuljh.client.url;

import com.alibaba.fastjson.JSONObject;
import com.github.ahuljh.common.RequestMethodEnum;
import com.github.ahuljh.model.url.CommonUrlRequest;
import com.github.ahuljh.model.url.UrlDataListResponse;
import com.github.ahuljh.model.url.UrlDataResponse;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TokenClientTest {

    private TokenClient tokenClient;

    public void setUp() {
        Properties properties = new Properties();
        properties.setProperty("host","http://127.0.0.1:12345/boulder/");
        properties.setProperty("token","token示例");
        tokenClient = new TokenClient(properties);
    }

    @Test
    public void commonUrlRequestTest() {
        this.setUp();
        CommonUrlRequest request = new CommonUrlRequest();
        request.setRequestMethod(RequestMethodEnum.GET.getValue());
        request.setAddress("projects");
        Map<String, Object> pageParam = new HashMap<>();

        pageParam.put("searchVal",null);
        pageParam.put("pageSize", 10);
        pageParam.put("pageNo",1);
        request.setRequestParam(pageParam);
        UrlDataResponse<Object> response = tokenClient.commonUrlRequest(request);
        Assert.assertTrue(response.getSuccess());
        UrlDataListResponse listResponse = JSONObject.parseObject(response.getData().toString(), UrlDataListResponse.class);
        System.out.println(listResponse.getTotalList());
        Assert.assertTrue(listResponse.getTotalList() instanceof List);
    }
}
```

- SessionClient
```java
package com.github.ahuljh.client.url;

import com.alibaba.fastjson.JSONObject;
import com.github.ahuljh.common.RequestMethodEnum;
import com.github.ahuljh.model.url.CommonUrlRequest;
import com.github.ahuljh.model.url.UrlDataListResponse;
import com.github.ahuljh.model.url.UrlDataResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SessionClientTest {

    private SessionClient sessionClient;

    public void setUp() {
        Properties properties = new Properties();
        properties.setProperty("host","http://127.0.0.1:12345/boulder/");
        properties.setProperty("userName","admin");
        properties.setProperty("password","dolphinscheduler123");
        sessionClient = new SessionClient(properties);
    }

    @Test
    public void commonUrlRequestTest() {
        this.setUp();
        CommonUrlRequest request = new CommonUrlRequest();
        request.setRequestMethod(RequestMethodEnum.GET.getValue());
        request.setAddress("projects");
        Map<String, Object> pageParam = new HashMap<>();

        pageParam.put("searchVal",null);
        pageParam.put("pageSize", 10);
        pageParam.put("pageNo",1);
        request.setRequestParam(pageParam);
        UrlDataResponse<Object> response = sessionClient.commonUrlRequest(request);
        Assert.assertTrue(response.getSuccess());
        UrlDataListResponse listResponse = JSONObject.parseObject(response.getData().toString(), UrlDataListResponse.class);
        System.out.println(listResponse.getTotalList());
        Assert.assertTrue(listResponse.getTotalList() instanceof List);
    }
}
```

