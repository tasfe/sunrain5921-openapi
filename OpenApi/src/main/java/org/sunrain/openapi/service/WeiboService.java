package org.sunrain.openapi.service;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.City;
import org.sunrain.openapi.model.Country;
import org.sunrain.openapi.model.Province;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.model.Weibo;
import org.sunrain.openapi.sdk.weibo.WeiboConstants;
import org.sunrain.openapi.sdk.weibo.WeiboFacade;
import org.sunrain.openapi.sdk.weibo.WeiboUtils;
import org.sunrain.openapi.util.ConfigPropertyReader;
import org.sunrain.openapi.util.Constants;

@Service
public class WeiboService implements WeiboApi, OauthApi {

	public final static String PROXY_HOST = "cn-proxy.cn.oracle.com";
	public final static String PROXY_PORT = "80";
	public final static String PROXY_SET = "true";

	@Override
	public String getRedirectUrl() {
		// https://api.weibo.com/oauth2/authorize?client_id=747021730&redirect_uri=http://127.0.0.1/openapi/api/weibo/oauth/callback&response_type=response_type&state=all&scope=scope
		return new StringBuilder(
				ConfigPropertyReader.getValue(WeiboConstants.AUTHORIZE_URL_KEY))
				.append("?client_id=")
				.append(ConfigPropertyReader.getValue(WeiboConstants.CLIENT_ID_KEY))
				.append("&redirect_uri=")
				.append(ConfigPropertyReader.getValue(WeiboConstants.REDIRECT_URI_KEY))
				.append("&response_type=").append("").append("&state=")
				.append(ConfigPropertyReader.getValue(WeiboConstants.STATE_KEY))
				.append("&scope=").append("").toString();
	}

	@Override
	public AccessToken getAccessTokenByCode(String code) throws Exception {
		HttpResponse response = WeiboFacade.getResponseOfAccessToken(code);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if(httpStatus == 200){
			return WeiboUtils.stringToAccessToken(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new AccessToken();
	}

	@Override
	public User getUserByUid(String uid, String accessToken) throws Exception {
		HttpResponse response = WeiboFacade.showUserById(uid, accessToken);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if (httpStatus == 200) {
			return WeiboUtils.string2User(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new User();
	}

	@Override
	public List<Country> getCountries(String accessToken) throws Exception {
		HttpResponse response = WeiboFacade.getCountries(accessToken);
		return WeiboUtils.string2CountryList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}

	@Override
	public List<Province> getProvinces(String accessToken, String countryCode) throws Exception {
		HttpResponse response = WeiboFacade.getProvinces(accessToken, countryCode);
		return WeiboUtils.string2ProvinceList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}

	@Override
	public List<City> getCities(String accessToken, String provinceCode) throws Exception {
		HttpResponse response = WeiboFacade.getCities(accessToken, provinceCode);
		return WeiboUtils.string2CityList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}

	@Override
	public List<Weibo> getPublicTimeline(String accessToken, int pageSize) throws Exception {
		HttpResponse response = WeiboFacade.getPublicTimeline(accessToken, pageSize);
		return WeiboUtils.string2WeiboList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}

	@Override
	public List<Weibo> getFriendsTimeline(String accessToken, int startIndex, int pageSize) throws Exception {
		HttpResponse response = WeiboFacade.getFriendsTimeline(accessToken, startIndex, pageSize);
		return WeiboUtils.string2WeiboList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}
	
	@Override
	public Weibo publishStatus(String accessToken, String status) throws Exception {
		HttpResponse response = WeiboFacade.publishStatus(accessToken, status);
		return WeiboUtils.string2Weibo(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}

	@Override
	public Weibo repostStatus(String accessToken, String statusId, String status, int isComment) throws Exception {
		HttpResponse response = WeiboFacade.repostStatus(accessToken, statusId, status, isComment);
		return WeiboUtils.string2Weibo(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}
}
