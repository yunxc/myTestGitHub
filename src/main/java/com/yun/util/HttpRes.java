package com.yun.util;

import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.List;

public class HttpRes {

	private int statusCode;
	private HashMap<String, String> headers;
	private String httpEntity;
	private String otherContent;
	private List<Cookie> cookies;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(String httpEntity) {
		this.httpEntity = httpEntity;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
}
