package org.sunrain.openapi.sdk.douban;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.http.DoubanHttpClient;
import org.sunrain.openapi.util.ConfigPropertyReader;

public class DoubanFacade {
	
	public static HttpResponse getResponseOfAccessToken(String code) throws Exception {
		return new DoubanHttpClient().post(ConfigPropertyReader.getValue(DoubanConstants.ACCESSTOKEN_URL_KEY), DoubanUtils.generateAccessTokenRequestParams(code));
	}
	
	public static HttpResponse getMe(String accessToken) throws Exception {
		return new DoubanHttpClient().get(accessToken, ConfigPropertyReader.getValue(DoubanConstants.BASE_URL_KEY) + "user" +URLEncoder.encode("/~me", "utf-8"));
	}

	public static HttpResponse getUserProfileByUid(String accessToken, String uid) throws Exception {
		return new DoubanHttpClient().get(accessToken, ConfigPropertyReader.getValue(DoubanConstants.BASE_URL_KEY) + "user/" + uid);
	}
	
	public static HttpResponse searchBooksByKeyword(String accessToken, String keyword, int startIndex, int pageSize) throws Exception {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("q", keyword));
		requestParams.add(new BasicNameValuePair("start", startIndex + ""));
		requestParams.add(new BasicNameValuePair("count", pageSize + ""));
		return new DoubanHttpClient().get(accessToken, ConfigPropertyReader.getValue(DoubanConstants.BASE_URL_KEY) + "book/search", requestParams);
	}
	
	public static HttpResponse searchBooksByTag(String accessToken, String tag, int startIndex, int pageSize) throws Exception {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("tag", tag));
		requestParams.add(new BasicNameValuePair("start", startIndex + ""));
		requestParams.add(new BasicNameValuePair("count", pageSize + ""));
		return new DoubanHttpClient().get(accessToken, ConfigPropertyReader.getValue(DoubanConstants.BASE_URL_KEY) + "book/search", requestParams);
	}
}
