package com.lushang.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.lushang.rest.client.RestClient;
import com.lushang.rest.client.RestRequest;
import com.lushang.rest.client.RestRequest.Method;
import com.lushang.rest.client.RestResponse;

public class Application {
	public static void main (String[] args) {

		// 設定 main function 裡面要測試的 REST request 之 method
		Method methodForTest = Method.GET;
		
		// LuShang's RESTful server
		String url = "http://localhost:8080/country-rest-server/countries";
		List<Header> headers = new ArrayList<Header>();
		String body = "";	
		RestClient client = new RestClient();
		RestRequest request = null;
		RestResponse response = null;
		
		// 使用方式 (依據不同 methodForTest 決定)
		switch (methodForTest) {
			case GET:
				headers.add(new BasicHeader("accept", "application/json"));
				request = new RestRequest(url, Method.GET, headers, body);
				break;
			case POST:
				headers.add(new BasicHeader("accept", "application/json"));
				headers.add(new BasicHeader("content-type", "application/json"));
				body = "{\"countryName\":\"DoCCgCc\",\"population\":77770}";
				request = new RestRequest(url, Method.POST, headers, body);
				break;
			case PUT:
				headers.add(new BasicHeader("accept", "application/json"));
				headers.add(new BasicHeader("content-type", "application/json"));
				body = "{\"id\":1,\"countryName\":\"CHT\",\"population\":100000}";
				request = new RestRequest(url, Method.PUT, headers, body);
				break;
			case DELETE:
				url = "http://localhost:8080/country-rest-server/country/2";
				headers.add(new BasicHeader("accept", "application/json"));
				request = new RestRequest(url, Method.DELETE, headers, body);
				break;
			default:
				break;
		}
		
		// 使用方式
		response = client.execute(request);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntityInputStream())));
			// 輸出結果
			String output;
			System.out.println("HTTP status code: " + response.getHttpStatusCode());
			System.out.println("Output from Server:");
			try {
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NullPointerException e){
			// System.out.println("No response from the server");
			// e.printStackTrace();
		}
		
		// 使用方式
		client.close();
	}
}
