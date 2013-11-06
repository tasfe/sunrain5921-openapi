package org.sunrain.openapi.http;

import java.net.URLEncoder;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class HttpClient {

	protected RequestConfig config = RequestConfig.custom().setProxy(new HttpHost("cn-proxy.cn.oracle.com", 80, "http")).build();
	protected CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public abstract Header[] access2Headers(String accessToken);
	
	public HttpResponse post(String url, List<NameValuePair> requestParams)throws Exception{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(config);
		httpPost.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
		return httpclient.execute(httpPost);
	}
	
	public HttpResponse post(String accessToken, String url, List<NameValuePair> requestParams)throws Exception{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(config);
		httpPost.setHeaders(access2Headers(accessToken));
		httpPost.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
		return httpclient.execute(httpPost);
	}
	
	public HttpResponse get(String accessToken, String url, List<NameValuePair> requestParams) throws Exception {
		HttpGet httpGet = new HttpGet(generateRequestUrl(url, requestParams));
		httpGet.setConfig(config);
		httpGet.setHeaders(access2Headers(accessToken));
		return httpclient.execute(httpGet);
	}
	
	public HttpResponse get(String accessToken, String url) throws Exception{
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		httpGet.setHeaders(access2Headers(accessToken));
		return httpclient.execute(httpGet);
	}
	
	public String encodeParameters(List<NameValuePair> postParams) {
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < postParams.size(); j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				System.out.println(postParams.get(j));
				buf.append(
						URLEncoder.encode(postParams.get(j).getName(), "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(postParams.get(j).getValue(),
								"UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();
	}

	public String generateRequestUrl(String url, List<NameValuePair> postParams) {
		String encodedParams = encodeParameters(postParams);
		if (-1 == url.indexOf("?")) {
			url += "?" + encodedParams;
		} else {
			url += "&" + encodedParams;
		}
		return url;
	}
}
