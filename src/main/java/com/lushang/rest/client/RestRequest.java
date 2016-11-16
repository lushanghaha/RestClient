package com.lushang.rest.client;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;


public class RestRequest {

	// HTTP requests
	private HttpGet getRequest;
	private HttpPost postRequest;
	private HttpPut putRequest;
	private HttpDelete deleteRequest;
	private HttpPatch patchRequest;
	
	// 此 request 的 method
	private Method method;
	
	// 參數化的 REST request
	public RestRequest(String url, Method method, List<Header> headers, String body) {		
		
		// 提供給含有 HttpEntity 的 HTTP requests HttpEntity，如 PUT, POST, PATCH 等
		StringEntity input = null;
		
		switch (method) {
			case GET:
				this.method = Method.GET;
				getRequest = new HttpGet(url);
				break;
			case POST:
				this.method = Method.POST;
				postRequest = new HttpPost(url);
				try {
					input = new StringEntity(body);
					postRequest.setEntity(input);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			case PUT:
				this.method = Method.PUT;
				putRequest = new HttpPut(url);
				try {
					input = new StringEntity(body);
					putRequest.setEntity(input);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			case DELETE:
				this.method = Method.DELETE;
				deleteRequest = new HttpDelete(url);
				break;
			case PATCH:
				break;
			default:
				break;
		}
		
		// 將所有 HTTP requests 加入 headers
		for (Header header : headers) {
			this.getRequest().addHeader(header.getName(), header.getValue());
		}
	}
	
	public HttpRequest getRequest() {
		switch (this.method) {
			case GET:
				return getRequest;
			case POST:
				return postRequest;
			case PUT:
				return putRequest;
			case DELETE:
				return deleteRequest;
			case PATCH:
				return patchRequest;
			default:
				return null;
		}
	}

	public enum Method {
	    GET, POST, PUT, DELETE, PATCH
	}
}
