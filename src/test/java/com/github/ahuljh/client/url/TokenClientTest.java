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
    public void commonUrlRequestGetTest() {
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