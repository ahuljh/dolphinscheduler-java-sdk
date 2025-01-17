package com.github.ahuljh.client.url;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.github.ahuljh.client.Client;
import com.github.ahuljh.common.RequestMethodEnum;
import com.github.ahuljh.model.url.CommonUrlRequest;
import com.github.ahuljh.model.url.UrlDataResponse;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractUrlDefaultClient implements Client {
    protected Properties properties;
    protected String host;
    protected Map<String, String> header;

    protected AbstractUrlDefaultClient(Properties properties) {
        this.properties = properties;
        this.host = this.properties.getProperty("host");
        this.header = new ConcurrentHashMap<>();
    }

    @Override
    public Map<String, Object> getBasicInfo() {
        return Collections.emptyMap();
    }

    protected UrlDataResponse<Object> commonUrlRequest(CommonUrlRequest request) {
        UrlDataResponse<Object> response = new UrlDataResponse<>();
        String url = this.host + request.getAddress();
        Map<String, String> header = request.getHeader();
        header.putAll(this.header);
        System.out.println(header);

        Map<String, Object> requestBody = (Map<String, Object>)request.getRequestBody();
        Map<String, Object> requestParam = request.getRequestParam();
        String body = new String();
        if (ObjectUtil.equal(request.getRequestMethod(), RequestMethodEnum.GET.getValue())) {
            body = HttpRequest.of(url).headerMap(header, true).form(requestParam).execute().body();
        } else {

        }
        response = JSONObject.parseObject(body, UrlDataResponse.class);
        return response;
    }
}
