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
		
		String input = null;
		OutputStream os = null;
		URL serverUrl = null;
	
		switch (method) {
			case GET:
				try {
					serverUrl = new URL(url);
					conn = (HttpURLConnection) serverUrl.openConnection();
					conn.setRequestMethod(method.toString());
					// 將所有 HTTP requests 加入 headers
					for (Header header : headers) {
						conn.setRequestProperty(header.getKey(), header.getValue());
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case POST:
				try {
					serverUrl = new URL(url);
					conn = (HttpURLConnection) serverUrl.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod(method.toString());
					// 將所有 HTTP requests 加入 headers
					for (Header header : headers) {
						conn.setRequestProperty(header.getKey(), header.getValue());
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				input = body;
				try {
					os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case PUT:
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
