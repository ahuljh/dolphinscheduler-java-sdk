package com.github.ahuljh.client.url;

import java.util.Properties;

public class TokenClient extends AbstractUrlDefaultClient {
    private String token;

    protected TokenClient(Properties properties) {
        super(properties);
        this.token = this.properties.getProperty("token");
        this.header.put("token", token);
    }
}
