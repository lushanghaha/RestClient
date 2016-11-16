package com.lushang.rest.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

public class RestResponse {
	
	private HttpResponse response;
	private int httpStatusCode;
	
	public RestResponse(HttpResponse response) {
		this.response = response;
		this.httpStatusCode = response.getStatusLine().getStatusCode();
	}
	
	public InputStream getEntityInputStream() {
		
		try {
			return response.getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int httpStatusCode() {
		return httpStatusCode;
	}
}
