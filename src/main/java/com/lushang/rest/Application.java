package com.lushang.rest;

import java.util.ArrayList;
import java.util.List;

import com.cht.iam.common.api.httprequest.Header;
import com.cht.iam.common.api.httprequest.RestRequest;
import com.cht.iam.common.api.httprequest.RestRequest.Method;

public class Application {
	public static void main (String[] args) {
		
		// 設定 main function 裡面要測試的 REST request 之 method
		Method methodForTest = Method.GET;
		
		// LuShang's RESTful server
		String url = "http://localhost:8080/country-rest-server/countries";
		List<Header> headers = new ArrayList<Header>();
		String body = "";	
		RestRequest request = null;
		
		// 使用方式 (依據不同 methodForTest 決定)
		switch (methodForTest) {
			case GET:
				headers.add(new Header("Accept", "application/json"));
				request = new RestRequest(url, Method.GET, headers, body);
				break;
			case POST:
				headers.add(new Header("Accept", "application/json"));
				headers.add(new Header("Content-Type", "application/json"));
				body = "{\"countryName\":\"Dog\",\"population\":7777}";
				request = new RestRequest(url, Method.POST, headers, body);
				break;
			case PUT:
				headers.add(new Header("Accept", "application/json"));
				headers.add(new Header("Content-Type", "application/json"));
				body = "{\"id\":1,\"countryName\":\"CHT\",\"population\":100000}";
				request = new RestRequest(url, Method.PUT, headers, body);
				break;
			case DELETE:
				url = "http://localhost:8080/country-rest-server/country/2";
				headers.add(new Header("Accept", "application/json"));
				request = new RestRequest(url, Method.DELETE, headers, body);
				break;
			case PATCH:
				break;
			default:
				break;
		}

		// 使用方式
		System.out.println("HTTP status code: " + request.getResponseCode());
		System.out.println("Output from Server:" + request.getResponseContent());

		// 使用方式
		request.disconnect();
	}
}
