package org.sunrain.openapi.service;

import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.User;

public interface TaobaoApi {
	public String getRedirectUrl();
	public AccessToken getAccessTokenByCode(String code) throws Exception;
	public User getUserByUid(String uid, String accessToken) throws Exception;
}
