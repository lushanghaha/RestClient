package com.lushang.rest.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

    private CloseableHttpClient httpClient;

    public RestClient() {
        httpClient = HttpClients.createDefault();
    }

    public RestResponse execute(RestRequest request) {
        try {
            HttpResponse response = httpClient.execute((HttpUriRequest) request.getRequest());
            return new RestResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // 表示使用者的 URL 打錯或是 server 掛了
            System.out
                    .println("Failed to connect " + request.getRequest().getRequestLine().getUri());
            System.out.println(
                    "Check whether the url is correct and the web service that you are requesting is up and running");
        }
        return null;
    }

    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
