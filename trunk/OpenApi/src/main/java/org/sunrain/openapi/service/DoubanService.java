package org.sunrain.openapi.service;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.Book;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.sdk.douban.DoubanConstants;
import org.sunrain.openapi.sdk.douban.DoubanFacade;
import org.sunrain.openapi.sdk.douban.DoubanUtils;
import org.sunrain.openapi.util.ConfigPropertyReader;
import org.sunrain.openapi.util.Constants;

@Service
public class DoubanService implements DoubanApi {

	public String getRedirectUrl() {
		return new StringBuilder(
				ConfigPropertyReader.getValue(DoubanConstants.AUTHORIZE_URL_KEY))
				.append("?client_id=")
				.append(ConfigPropertyReader.getValue(DoubanConstants.CLIENT_ID_KEY))
				.append("&redirect_uri=")
				.append(ConfigPropertyReader.getValue(DoubanConstants.REDIRECT_URI_KEY))
				.append("&response_type=").append(DoubanConstants.CODE_STR)
				.append("&scope=").append(ConfigPropertyReader.getValue(DoubanConstants.STATE_KEY)).toString();
	}

	@Override
	public AccessToken getAccessTokenByCode(String code) throws Exception {
		HttpResponse response = DoubanFacade.getResponseOfAccessToken(code);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if(httpStatus == 200){
			return DoubanUtils.string2AccessToken(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new AccessToken();
	}
	
	@Override
	public User getCurrentUser(String accessToken) throws Exception {
		HttpResponse response = DoubanFacade.getMe(accessToken);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if(httpStatus == 200){
			return DoubanUtils.string2User(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		if(httpStatus == 403){
			System.out.println(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		if(httpStatus == 500){
			System.out.println(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new User();
	}

	@Override
	public User getUserProfileByUid(String accessToken, String uid) throws Exception {
		HttpResponse response = DoubanFacade.getUserProfileByUid(accessToken, uid);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if(httpStatus == 200){
			return DoubanUtils.string2User(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		if(httpStatus == 403){
			System.out.println(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		if(httpStatus == 500){
			System.out.println(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new User();
	}

	@Override
	public List<Book> searchBooksByKeyword(String accessToken, String keyword, int startIndex, int pageSize) throws Exception {
		HttpResponse response = DoubanFacade.searchBooksByKeyword(accessToken, keyword, startIndex, pageSize);
		return DoubanUtils.string2BookList(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
	}
}
