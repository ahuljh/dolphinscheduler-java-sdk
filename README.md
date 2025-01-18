# 1.项目介绍

dolphinscheduler-java-sdk是一款兼容Dolphinscheduler不同版本的更通用的java sdk
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
- JdbcClient
```java
package com.github.ahuljh.client;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.ahuljh.common.SqlTypeEnum;
import com.github.ahuljh.model.CommonSqlRequest;
import com.github.ahuljh.model.CommonSqlMapResponse;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JdbcClientTest {

    private JdbcClient jdbcClient;

    public void setUp() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dolphinscheduler_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT"); // 数据库 URL
        dataSource.setUsername("root"); // 数据库用户名
        dataSource.setPassword("123456"); // 数据库密码
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // JDBC 驱动类名

        // 可选配置
        dataSource.setInitialSize(5); // 初始连接数
        dataSource.setMinIdle(5); // 最小空闲连接数
        dataSource.setMaxActive(20); // 最大活动连接数
        dataSource.setMaxWait(60000); // 获取连接的最大等待时间，单位毫秒
        dataSource.setValidationQuery("SELECT 1"); // 用于测试连接是否有效的 SQL 查询
        dataSource.setTestOnBorrow(true); // 获取连接时是否测试连接
        dataSource.setTestWhileIdle(true); // 空闲时是否测试连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000); // 连接检测线程运行间隔
        dataSource.setMinEvictableIdleTimeMillis(300000); // 最小空闲连接保持时间

        jdbcClient = new JdbcClient(dataSource);
    }

    @Test
    public void executeSql() {
        this.setUp();
        String sql = " select * from t_ds_alert";
        CommonSqlRequest request = new CommonSqlRequest();
        request.setSqlStatement(sql);
        request.setSqlType(SqlTypeEnum.SELECT.getValue());

        CommonSqlMapResponse response = jdbcClient.executeSql(request);
        List<Map<String, Object>> resultList = response.getResultList();
        System.out.println(response.getCount());
        System.out.println(resultList);
    }
}
```

注意：在Spring项目中，可以直接根据配置文件的方式，自动注入，代码如下：
```java
    @Bean("maindb")
    @ConfigurationProperties("spring.datasource.maindb")
    public DataSource metaErpDb() {
        final DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
```
