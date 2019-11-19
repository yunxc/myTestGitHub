package com.yun.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
	
	 private static final String DEFAULTCHARSET = "UTF-8";
	
	
	/**
	 * httpclient 配置
	 * @param timeout
	 * @param redirectsEnabled
	 * @return
	 */
	public static RequestConfig createConfig(int timeout, boolean redirectsEnabled) {
		return RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).setRedirectsEnabled(redirectsEnabled).build();
	}

	public static HttpRes sendGet(String url) throws ClientProtocolException, IOException {
		return sendGet(url, null, null, false);
	}
	
	public static HttpRes sendGet(String url, Map<String, String> headers)
			throws ClientProtocolException, IOException {
		return sendGet(url, headers, null, false);
	}

	public static HttpRes sendGet(String url, Map<String, String> headers, Map<String, String> params, boolean duan)
			throws ClientProtocolException, IOException {
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		url = url + (null == params ? "" : assemblyParameter(params));
		HttpGet hp = new HttpGet(url);
		hp.setConfig(createConfig(5000, false));
		if (null != headers)
			hp.setHeaders(assemblyHeader(headers));
		CloseableHttpResponse response = client.execute(hp);
		if (duan)
			hp.abort();
		HttpEntity entity = response.getEntity();
		HttpRes res = new HttpRes();
		res.setHttpEntity(EntityUtils.toString(entity, DEFAULTCHARSET));
		EntityUtils.consume(entity);
		res.setStatusCode(response.getStatusLine().getStatusCode());
		res.setHeaders(assemblyHeader(response.getAllHeaders()));
		res.setCookies(cookieStore.getCookies());
		response.close();
		client.close();
		return res;
	}

	public static HttpRes sendPost(String url, Map<String, String> headers, Map<String, String> params)
			throws ClientProtocolException, IOException {
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		HttpPost hp = new HttpPost(url);
		hp.setConfig(createConfig(5000 * 2, false));
		if (null != headers)
			hp.setHeaders(assemblyHeader(headers));
		if (params != null) {
			// 参数
			List<NameValuePair> paramList = new ArrayList<>();
			for (String key : params.keySet()) {
				paramList.add(new BasicNameValuePair(key, params.get(key)));
			}
			// 模拟表单
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
			hp.setEntity(entity);
		}
		// 执行http请求
		CloseableHttpResponse response = client.execute(hp);
		HttpEntity entity = response.getEntity();
		HttpRes res = new HttpRes();
		res.setHttpEntity(EntityUtils.toString(entity, DEFAULTCHARSET));
		EntityUtils.consume(entity);
		res.setStatusCode(response.getStatusLine().getStatusCode());
		res.setHeaders(assemblyHeader(response.getAllHeaders()));
		res.setCookies(cookieStore.getCookies());
		response.close();
		client.close();
		return res;
	}


	private static HashMap<String, String> assemblyHeader(Header[] allHeaders) {
		HashMap<String, String> headerMap = new HashMap<>();
		for (Header header : allHeaders) {
			headerMap.put(header.getName(), header.getValue());
		}
		return headerMap;
	}

	/**
	 * 拼装get请求参数
	 * 
	 * @param parameters
	 * @return
	 */
	public static String assemblyParameter(Map<String, String> parameters) {
		String para = "?";
		for (String str : parameters.keySet()) {
			para += str + "=" + parameters.get(str) + "&";
		}
		return para.substring(0, para.length() - 1);
	}

	/**
	 * 封装请求头部信息
	 * 
	 * @param headers
	 * @return
	 */
	public static Header[] assemblyHeader(Map<String, String> headers) {
		Header[] allHeader = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			allHeader[i] = new BasicHeader(str, headers.get(str));
			i++;
		}
		return allHeader;
	}
}
