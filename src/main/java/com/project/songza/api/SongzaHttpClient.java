package com.project.songza.api;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Singleton
public class SongzaHttpClient {

    private HttpClient httpClient;

    @Inject
    public SongzaHttpClient(){
        httpClient = new DefaultHttpClient(new BasicHttpParams());
    }

    public Response get(String url, Map<String, String> headers) throws IOException, URISyntaxException {
        URI uri = new URI(url);
        return makeRequest(headers, new HttpGet(uri));
    }

    private Response makeRequest(Map<String, String> headers, HttpRequestBase method) {
        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                method.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(method);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8.name());
            return new Response(statusCode, responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Response {
        private final int statusCode;
        private final String body;


        public Response(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        public String getBody(){
            return body;
        }

        public int getStatusCode(){
            return statusCode;
        }

        public boolean isSuccess() {
            return statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES;
        }

        public <T> T getBodyAs(Type aType) {
            return JsonHelper.fromJson(body, aType);
        }

        @Override
        public String toString() {
            return String.format("{HttpResponse status:%s success:%s}", statusCode, isSuccess());
        }
    }
}
