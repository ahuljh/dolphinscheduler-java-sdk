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
        dataSource.setUrl("jdbc:mysql://172.16.5.81:3306/dolphinscheduler_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT"); // 数据库 URL
        dataSource.setUsername("root"); // 数据库用户名
        dataSource.setPassword("Zzh!@7465671"); // 数据库密码
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