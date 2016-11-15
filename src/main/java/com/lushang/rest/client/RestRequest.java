package com.lushang.rest.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.http.HttpRequest;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.client.methods.HttpGet;

public class RestRequest {
	
	HttpRequest request;
	
	@SuppressWarnings("unchecked")
	public RestRequest(String url, Method method, List<Header> headers, String body) {
		// this.url = url;
		// this.method = method;
		// this.headers = headers;
		// this.body = body;
		
		// 找出不同 method 相對應的 class 名稱
		String className = String.format("org.apache.http.client.methods.Http%s",
			method.toString().charAt(0) + method.toString().substring(1).toLowerCase());
		
		// System.out.println(className);
		
		try {
			// 依據不同 class 名稱 new 出不圖 method class 的 instance
			Class<HttpRequest> clazz;
			clazz = (Class<HttpRequest>) Class.forName(className);
			Constructor<HttpRequest> ctor = clazz.getConstructor(String.class);
			request = ctor.newInstance(url);
			request.addHeader(headers.get(0).getKey(), headers.get(0).getValue());
			
			/*
			StringEntity input = new StringEntity(body);
			input.setContentType("application/json");
			request.setEntity(input);
			*/
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public enum Method {
	    GET, POST, PUT, DELETE
	}

}
