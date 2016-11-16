package com.lushang.rest.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

public class RestResponse {
	
	private HttpResponse response;
	private int httpStatusCode;
	
	public RestResponse(HttpResponse response) {
		this.response = response;
		httpStatusCode = response.getStatusLine().getStatusCode();
	}
	
	public InputStream getEntityInputStream() {
		
		try {
			return response.getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
}
