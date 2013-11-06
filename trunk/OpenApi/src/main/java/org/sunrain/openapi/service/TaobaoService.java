package org.sunrain.openapi.service;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.sdk.taobao.TaobaoConstants;
import org.sunrain.openapi.sdk.taobao.TaobaoFacade;
import org.sunrain.openapi.sdk.taobao.TaobaoUtils;
import org.sunrain.openapi.util.ConfigPropertyReader;
import org.sunrain.openapi.util.Constants;

@Service
public class TaobaoService implements TaobaoApi, OauthApi {

	@Override
	public String getRedirectUrl() {
		return new StringBuilder(
				ConfigPropertyReader.getValue(TaobaoConstants.AUTHORIZE_URL_KEY))
				.append("?client_id=")
				.append(ConfigPropertyReader.getValue(TaobaoConstants.CLIENT_ID_KEY))
				.append("&redirect_uri=")
				.append(ConfigPropertyReader.getValue(TaobaoConstants.REDIRECT_URI_KEY))
				.append("&response_type=").append(TaobaoConstants.CODE_STR)
				.append("&scope=").append(ConfigPropertyReader.getValue(TaobaoConstants.STATE_KEY)).toString();
	}

	@Override
	public AccessToken getAccessTokenByCode(String code) throws Exception {
		HttpResponse response = TaobaoFacade.getResponseOfAccessToken(code);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if(httpStatus == 200){
			return TaobaoUtils.stringToAccessToken(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new AccessToken();
	}

	@Override
	public User getUserByUid(String uid, String accessToken) throws Exception {
		HttpResponse response = TaobaoFacade.showUserById(uid, accessToken);
		int httpStatus = response.getStatusLine().getStatusCode();		
		if (httpStatus == 200) {
			return TaobaoUtils.string2User(EntityUtils.toString(response.getEntity(), Constants.CHARSET_UTF8));
		}
		return new User();
	}
}
