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

		// fake initializations
		String url = "http://localhost:8080/country-rest-server/countries";
		List<Header> headers = new ArrayList<Header>();
		String body = "";
		headers.add(new Header("accept", "application/json"));
		
		// 預期使用方式
		RestClient client = new RestClient();
		RestRequest request = new RestRequest(url, Method.GET, headers, body);
		RestResponse response = client.execute(request);
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntityInputStream())));
		
		// 輸出結果
		String output;
		System.out.println("Output from Server .... \n");
		try {
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 預期使用方式
		client.close();
	}

}
