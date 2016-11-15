package com.lushang.rest.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

public class RestResponse {
	
	private HttpResponse response;
	
	public RestResponse(HttpResponse response) {
		this.response = response;
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
	
	public Integer getHttpStatus() {
		return null;
	}

}
