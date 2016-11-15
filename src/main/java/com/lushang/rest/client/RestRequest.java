package com.lushang.rest.client;

import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;

public class RestRequest {
	
	HttpRequest request;
	
	public RestRequest(String url, Method method, List<Header> headers, String body) {
		// this.url = url;
		// this.method = method;
		// this.headers = headers;
		// this.body = body;
		
		if (method.toString() == "GET") {
			request = new HttpGet(url);
			request.addHeader(headers.get(0).getKey(), headers.get(0).getValue());
		}
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public enum Method {
	    GET, POST, PUT, DELETE
	}

}
