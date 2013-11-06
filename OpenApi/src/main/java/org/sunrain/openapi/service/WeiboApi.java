package org.sunrain.openapi.service;

import java.util.List;

import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.City;
import org.sunrain.openapi.model.Country;
import org.sunrain.openapi.model.Province;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.model.Weibo;

public interface WeiboApi {
	
	public String getRedirectUrl();

	public AccessToken getAccessTokenByCode(String code) throws Exception;

	public User getUserByUid(String uid, String accessaToken) throws Exception;

	public List<Country> getCountries(String accessToken) throws Exception;

	public List<Province> getProvinces(String accessToken, String countryCode) throws Exception;

	public List<City> getCities(String accessToken, String provinceCode) throws Exception;
	
	public List<Weibo> getPublicTimeline(String accessToken, int pageSize) throws Exception;
	
	public List<Weibo> getFriendsTimeline(String accessToken, int startIndex, int pageSize) throws Exception;
	
	public Weibo publishStatus(String accessToken, String status) throws Exception;
	
	public Weibo repostStatus(String accessToken, String statusId, String status, int isComment) throws Exception;
}
