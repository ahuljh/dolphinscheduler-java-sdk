package com.github.ahuljh.client.url;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.github.ahuljh.model.url.UrlDataResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SessionClient extends AbstractUrlDefaultClient {
    private String userName;
    private String password;
    private String session;

    protected SessionClient(Properties properties) {
        super(properties);
        this.userName = this.properties.getProperty("userName");
        this.password = this.properties.getProperty("password");
        this.session = this.login();
    }

    public String login() {
        String url = this.host  + "login";
        Map<String,Object> body = new HashMap<>();
        body.put("userName", this.userName);
        body.put("userPassword", this.password);

        UrlDataResponse<Object> response = JSONObject.parseObject(HttpRequest.post(url).form(body).execute().body(), UrlDataResponse.class);
        if (!response.getSuccess()) ;
        Map<String, String> result = JSONObject.parseObject(response.getData().toString(), new HashMap<String, String>().getClass());
        String sessionId = result.get("sessionId");
        if (ObjectUtil.isNull(sessionId)) login();
        this.header.put("sessionId", sessionId);
        return sessionId;
    }
}
