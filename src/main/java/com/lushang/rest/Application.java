package com.lushang.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.lushang.rest.client.Header;
import com.lushang.rest.client.RestClient;
import com.lushang.rest.client.RestRequest;
import com.lushang.rest.client.RestRequest.Method;
import com.lushang.rest.client.RestResponse;

public class Application {
	public static void main (String[] args) {

		// Select test method
		Method methodForTest = Method.POST;
		
		// LuShang's RESTful server
		String url = "http://localhost:8080/country-rest-server/countries";
		List<Header> headers = new ArrayList<Header>();
		String body = "";	
		RestClient client = new RestClient();
		RestRequest request = null;
		RestResponse response = null;
		BufferedReader br = null;
		
		// 使用方式 (測試)
		switch (methodForTest) {
			case GET:
				headers.add(new Header("accept", "application/json"));
				request = new RestRequest(url, Method.GET, headers, body);
				break;
			case POST:
				headers.add(new Header("accept", "application/json"));
				body = "{\"countryName\":\"Dogc\",\"population\":77770}";
				request = new RestRequest(url, Method.POST, headers, body);
				break;
			case PUT:
				headers.add(new Header("accept", "application/json"));
				body = "{\"id\":1,\"countryName\":\"CHT\",\"population\":100000}";
				request = new RestRequest(url, Method.PUT, headers, body);
				break;
			case DELETE:
				url = "http://localhost:8080/country-rest-server/country/2";
				headers.add(new Header("accept", "application/json"));
				request = new RestRequest(url, Method.DELETE, headers, body);
				break;
			default:
				break;
		}
		
		// 使用方式
		response = client.execute(request);
		br = new BufferedReader(new InputStreamReader((response.getEntityInputStream())));

		// 輸出結果
		String output;
		System.out.println("HTTP status code: " + response.getHttpStatusCode());
		System.out.println("Output from Server:");
		try {
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 使用方式
		client.close();
	}
}
