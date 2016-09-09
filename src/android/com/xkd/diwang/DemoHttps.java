package com.xkd.diwang.intelligoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLContext;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.ssl.SSLContexts;
import cz.msebera.android.httpclient.util.EntityUtils;

import org.json.JSONObject;

public class DemoHttps {
	public static HttpClient getHttpsClient() {
		
		try {
			ServerSocket sk = new ServerSocket(8089);
			Socket sc =sk.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream())); 
			br.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return new HttpClientBuilder(connMgr, params);
		return HttpClientBuilder.create().build();
	}
	
	public static JSONObject connectPost(String url,JSONObject parameters) 
	{
		if (url == null || parameters == null) 
		{
			return null;
		}
		HttpClient httpClient = getHttpsClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject json=null;
		try {
			HttpEntity entity_content = new StringEntity(parameters.toString(),StandardCharsets.UTF_8); //设置字符集
			httpPost.setEntity(entity_content);	
			HttpResponse httpRes = httpClient.execute(httpPost);
			json = new JSONObject(EntityUtils.toString(httpRes.getEntity()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	public static JSONObject connectGet(String url,String parameters) 
	{
		//建立新的httpClient对象和httpPost对象
		HttpClient httpClient = getHttpsClient();
		
		//处理get_url
		String get_url = url +"?"+parameters;
		HttpGet httpGet = new HttpGet(get_url);
		JSONObject json = null;
		try {
			HttpResponse httpRes = httpClient.execute(httpGet);			
			json = new JSONObject(EntityUtils.toString(httpRes.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return json;
	}
}
