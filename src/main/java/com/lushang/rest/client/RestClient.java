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
		this.httpClient = HttpClients.createDefault();
	}

	public RestResponse execute(RestRequest request) {
		try {
			// HttpResponse response;
			HttpResponse response = httpClient.execute((HttpUriRequest) request.getRequest());
			return new RestResponse(response);
			
			// 如果 Server 端回傳的 HTTP status 不是 200，就噴 exception
			/*if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}*/	
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
