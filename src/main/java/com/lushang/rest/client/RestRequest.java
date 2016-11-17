package com.lushang.rest.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RestRequest {
	
	private HttpURLConnection conn;
	
	// 參數化的 REST request
	public RestRequest(String url, Method method, List<Header> headers, String body) {		
		
		try {
			URL serverUrl = new URL(url);
			conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod(method.toString());
			// 將所有 HTTP requests 加入 headers
			for (Header header : headers) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (method) {
			case GET:
				break;
			case POST:
				conn.setDoOutput(true);
				try {
					OutputStream os = conn.getOutputStream();
					os.write(body.getBytes());
					os.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case PUT:
				conn.setDoOutput(true);
				try {
					OutputStream os = conn.getOutputStream();
					os.write(body.getBytes());
					os.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case DELETE:
				break;
			case PATCH:
				break;
			default:
				break;
		}
	}

	public enum Method {
	    GET, POST, PUT, DELETE, PATCH
	}

	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		try {
			return conn.getInputStream();
		} catch (IOException e) {
			// 表示使用者的 URL 打錯或是 server 掛了
			System.out.println("Failed to connect " + conn.getURL());
			System.out.println("Check whether the url is correct and the web service that you are requesting is up and running");
			// e.printStackTrace();
		}
		return null;
	}

	public int getResponseCode() {
		// TODO Auto-generated method stub
		try {
			return conn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void disconnect() {
		conn.disconnect();
	}
}
