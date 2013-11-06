package org.sunrain.openapi.sdk.weibo;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.http.WeiboHttpClient;
import org.sunrain.openapi.util.ConfigPropertyReader;

public class WeiboFacade {
	
	public static HttpResponse getResponseOfAccessToken(String code) throws Exception {
		return new WeiboHttpClient().post(ConfigPropertyReader.getValue(WeiboConstants.ACCESSTOKEN_URL_KEY), WeiboUtils.generateAccessTokenRequestParams(code));
	}
	
	public static HttpResponse showUserById(String uid, String accessToken) throws Exception {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("uid", uid));
		return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "users/show.json", requestParams);
	}
	
	public static HttpResponse getCountries(String accessToken) throws Exception{
		return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "common/get_country.json");
    }
    
    public static HttpResponse getProvinces(String accessToken, String countryCode) throws Exception{
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("country", countryCode));
    	return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "common/get_province.json", requestParams);
    }
    
    public static HttpResponse getCities(String accessToken, String provinceCode) throws Exception{
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("province", provinceCode));
    	return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "common/get_city.json", requestParams);
    }
    
    public static HttpResponse getPublicTimeline(String accessToken, int pageSize) throws Exception {
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("count", pageSize + ""));
    	return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "statuses/public_timeline.json", requestParams);
    }
    
    public static HttpResponse getFriendsTimeline(String accessToken, int startIndex, int pageSize) throws Exception {
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("count", pageSize + ""));
		requestParams.add(new BasicNameValuePair("page", startIndex + ""));
    	return new WeiboHttpClient().get(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "statuses/friends_timeline.json", requestParams);
    }
    
    public static HttpResponse publishStatus(String accessToken, String status) throws Exception {
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("status", status));
    	return new WeiboHttpClient().post(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "statuses/update.json", requestParams);
    }
    
    /*
    public static HttpResponse uploadstatus(String accessToken, String status, ) throws Exception {
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("status", status));
    	return new WeiboHttpClient().post(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "statuses/upload.json", requestParams);
    }
    */
    
    public static HttpResponse repostStatus(String accessToken, String statusId, String status, int isComment) throws Exception{
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("id", statusId));
		requestParams.add(new BasicNameValuePair("status", status));
		requestParams.add(new BasicNameValuePair("is_comment", isComment + ""));
		return new WeiboHttpClient().post(accessToken, ConfigPropertyReader.getValue(WeiboConstants.BASE_URL_KEY) + "statuses/repost.json", requestParams);
    }
}