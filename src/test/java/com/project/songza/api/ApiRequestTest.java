package com.project.songza.api;

import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ApiRequestTest {

    private static final String URL = "http://www.yahoo.com";

    @Test
    public void shouldCreateGetRequest() {
        ApiRequest request = ApiRequest.createGetRequest(URL);

        assertThat(request.url(), is(URL));
        assertThat(request.headers(), hasEntry(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString()));
    }

    @Test
    public void shouldAllowAddingHeaderValues() {
        ApiRequest request = ApiRequest.createGetRequest(URL);
        request.addHeader("headerName1", "headerValue1");

        Map<String,String> headers = request.headers();
        assertThat(headers.size(), is(2));
        assertThat(headers, hasEntry("headerName1", "headerValue1"));
    }

    @Test
    public void shouldIncludeHeaderValuesInToString() {
        ApiRequest request = ApiRequest.createGetRequest(URL);

        assertThat(request.url(), containsString(URL));
        assertThat(request.headers().toString(), containsString(HttpHeaders.CONTENT_TYPE + "=" + MediaType.JSON_UTF_8));
    }
}
