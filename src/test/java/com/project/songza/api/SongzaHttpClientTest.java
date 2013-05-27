package com.project.songza.api;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(RobolectricTestRunner.class)
public class SongzaHttpClientTest {

    private static final String URL = "www.example.com";
    private static final Map<String,String> HEADERS = unmodifiableMap(new HashMap<String, String>());

    @Mock
    private SongzaHttpClient httpClient;

    @Before
    public void setup(){
        initMocks(this);
        httpClient = new SongzaHttpClient();
    }

    @Test
    public void shouldGetCorrectRequest() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");

        httpClient.get(URL, HEADERS);

        assertThat(((HttpUriRequest) Robolectric.getSentHttpRequest(0)).getURI(), equalTo(URI.create(URL)));
    }

    @Test
    public void shouldGetWithCorrectHttpMethod() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");

        httpClient.get(URL, HEADERS);
        HttpUriRequest sentHttpRequest = (HttpUriRequest) Robolectric.getSentHttpRequest(0);

        assertThat(sentHttpRequest.getMethod(), equalTo(HttpGet.METHOD_NAME));
    }

    @Test
    public void shouldGetWithCorrectHeaders() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("foo", "bar");
        httpClient.get(URL, headers);
        HttpRequest sentHttpRequest = Robolectric.getSentHttpRequest(0);

        assertThat(sentHttpRequest.getHeaders("foo")[0].getValue(), equalTo("bar"));
    }

    @Test
    public void shouldReturnCorrectResponse() throws Exception {
        Robolectric.addPendingHttpResponse(666, "it's all cool");

        SongzaHttpClient.Response response = httpClient.get(URL, HEADERS);

        assertThat(response.getBody(), equalTo("it's all cool"));
        assertThat(response.getStatusCode(), equalTo(666));
    }

    @Test
    public void shouldReturnSuccessOnHttpGetResponse() {
        SongzaHttpClient.Response response = new SongzaHttpClient.Response(200, "This is a success");

        assertThat(response.toString(), equalTo("{HttpResponse status:200 success:true}"));
    }

    @Test
    public void shouldReturnFailureOnHttpGetResponse() {
        SongzaHttpClient.Response response = new SongzaHttpClient.Response(500, "This is a failure");

        assertThat(response.toString(), equalTo("{HttpResponse status:500 success:false}"));
    }

    @Test
    public void shouldNotIncludeBodyOnHttpGetResponse() {
        SongzaHttpClient.Response response = new SongzaHttpClient.Response(200, "Body Text");

        assertThat(response.toString(), not(containsString("Body Text")));
    }
}
