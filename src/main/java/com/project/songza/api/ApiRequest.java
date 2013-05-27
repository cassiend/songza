package com.project.songza.api;

import com.google.common.net.HttpHeaders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ApiRequest {
    private static final String JSON_MEDIA_TYPE = "application/json; charset=utf-8";
    private final String url;
    private final Map<String, String> headers = newHashMap();

    private ApiRequest(String url) {
        this.url = url;
        addHeader(HttpHeaders.CONTENT_TYPE, JSON_MEDIA_TYPE);
    }

    public String url() {
        return url;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public static ApiRequest createGetRequest(String url) {
        return new ApiRequest(url);
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
}
