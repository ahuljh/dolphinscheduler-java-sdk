package com.github.ahuljh.client;

import cn.hutool.core.util.ObjectUtil;
import com.github.ahuljh.common.MapToBeanConverter;
import com.github.ahuljh.common.SqlTypeEnum;
import com.github.ahuljh.model.CommonSqlBeanResponse;
import com.github.ahuljh.model.CommonSqlRequest;
import com.github.ahuljh.model.CommonSqlMapResponse;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdbcClient implements Client{
    private QueryRunner queryRunner;

    @Override
    public Map<String, Object> getBasicInfo() {
        return Collections.emptyMap();
    }

    public JdbcClient(DataSource dataSource) {
        this.queryRunner = new QueryRunner(dataSource);
    }

    public <T> CommonSqlBeanResponse<T> executeSqlToBean(CommonSqlRequest request) {
        CommonSqlMapResponse mapResponse = this.executeSql(request);
        List<T> resultList = (List<T>) mapResponse.getResultList().stream().map(x -> MapToBeanConverter.convertMapToBean(x, request.getType())).collect(Collectors.toList());
        CommonSqlBeanResponse response = new CommonSqlBeanResponse();
        response.setBeanList(resultList);
        response.setCount(mapResponse.getCount());
        return response;
    }

    public CommonSqlMapResponse executeSql(CommonSqlRequest request) {
        if (ObjectUtil.notEqual(request.getSqlType(), SqlTypeEnum.SELECT.getValue())) throw new UnsupportedOperationException("当前仅支持select类型的查询语句");
        String sqlStatement = request.getSqlStatement();
        this.checkSqlLegality(sqlStatement);
        List<Map<String, Object>> resultList = null;
        try {
            resultList = this.queryRunner.query(sqlStatement, new MapListHandler(), request.getParamArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CommonSqlMapResponse response = new CommonSqlMapResponse();
        response.setCount(resultList.size());
        response.setResultList(resultList);
        return response;
    }

    private void checkSqlLegality(String sqlStatement) {
        if (!sqlStatement.trim().toLowerCase().startsWith("select")) new UnsupportedOperationException("当前仅支持select类型的查询语句");
    }
}
