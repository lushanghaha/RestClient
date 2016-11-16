package com.lushang.rest.client;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
	
	// for HTTP requests contain HttpEntity
	private StringEntity input;
	
	private Method method;
	
	// 呼叫每個 private member 的時候都先用 this.
	public RestRequest(String url, Method method, List<Header> headers, String body) {		

		switch (method) {
			case GET:
				this.method = Method.GET;
				this.getRequest = new HttpGet(url);
				break;
			case POST:
				this.method = Method.POST;
				this.postRequest = new HttpPost(url);
				try {
					this.input = new StringEntity(body);
					this.input.setContentType("application/json");
					this.postRequest.setEntity(this.input);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case PUT:
				this.method = Method.PUT;
				this.putRequest = new HttpPut(url);
				try {
					this.input = new StringEntity(body);
					this.input.setContentType("application/json");
					this.putRequest.setEntity(this.input);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case DELETE:
				this.method = Method.DELETE;
				this.deleteRequest = new HttpDelete(url);
				break;
			case PATCH:
				break;
			default:
				break;
		}
		
		// 大家一起 addHeader
		for (Header header : headers) {
			this.getRequest().addHeader(header.getKey(), header.getValue());
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
