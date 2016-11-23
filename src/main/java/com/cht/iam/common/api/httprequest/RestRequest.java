package com.cht.iam.common.api.httprequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RestRequest {
	private HttpURLConnection connection;
	
	/**
	 * url：server URL
	 * method：HTTP request method
	 * headers：HTTP request headers
	 * body：HTTP request body
	 */
	public RestRequest(String url, Method method, List<Header> headers, String body) {
		try {
			// 根據 url 建立一個 URL 物件 serverUrl
			URL serverUrl = new URL(url);
			// 根據 serverUrl 建立一個 HttpURLConnection 物件 connection (建立一個 HTTP request)
			connection = (HttpURLConnection) serverUrl.openConnection();
			// 根據 method 設定 connection 的 method (設定 HTTP request method)
			connection.setRequestMethod(method.toString());
			// 根據 headers 設定 conn 的 headers (設定 HTTP request headers)
			for (Header header : headers) {
				connection.setRequestProperty(header.getKey(), header.getValue());
			}
		} catch (MalformedURLException e) {
			// URL 格式錯誤
			// System.out.println("Invalid URL");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 針對需要 body 的 HTTP request 加入 body，並傳送 (write) 給 server
		switch (method) {
			case GET:
				break;
			case POST:
				// 讓 connection 具有 output 的功能，因為 POST 需要先行傳送 data 給 server
				connection.setDoOutput(true);
				try {
					// 取得 destination 為 serverUrl 的 OutputStream，隨時可以傳送 data 給 server
					// 在此實際上與 server 建立一個完整的 HTTP 連線，但還沒有任何 data 的來往
					OutputStream outputStream = connection.getOutputStream();
					// 把 HTTP POST request 的 body 傳送 (write) 到 server (outputStream 的 destination)
					outputStream.write(body.getBytes());
					// flush() 執行後，在記憶體 buffer 區中等待被 write 的 data 會立馬被 write
					outputStream.flush();
					// 關閉 outputStream，可以釋放與 outputStream 有關的網路資源；但有些 protocol 會另外定義 close 這個行為
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case PUT:
				connection.setDoOutput(true);
				try {
					OutputStream outputStream = connection.getOutputStream();
					outputStream.write(body.getBytes());
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case DELETE:
				break;
			case PATCH:
				connection.setDoOutput(true);
				try {
					OutputStream outputStream = connection.getOutputStream();
					outputStream.write(body.getBytes());
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
	}

	public enum Method {
	    GET, POST, PUT, DELETE, PATCH
	}	

	public int getResponseCode() {
		try {
			return connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void disconnect() {
		// connection 一旦被關閉，就不能再被使用了
		connection.disconnect();
	}

	public String getResponseContent() {
		BufferedReader bufferReader = null;
		StringBuilder builder = new StringBuilder();
		String string = "";
		try {
			// getInputStream(): 取得 source 為 serverUrl 的 InputStream，隨時可以從該 server 取得 (read) data
			// 在此實際上與 server 建立一個完整的 HTTP 連線，但還沒有任何 data 的來往
			bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {
			// URL 錯誤 server 掛了，或其他問題
			// System.out.println("Failed to connect " + conn.getURL());
			// System.out.println("Check whether the url is correct and the web service that you are requesting is up and running");
			e.printStackTrace();
		}
		try {
			while ((string = bufferReader.readLine()) != null) {
			    builder.append(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
