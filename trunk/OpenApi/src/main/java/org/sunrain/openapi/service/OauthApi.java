package org.sunrain.openapi.service;

import org.sunrain.openapi.model.AccessToken;

public interface OauthApi {
	public String getRedirectUrl();
	public AccessToken getAccessTokenByCode(String code) throws Exception;
}
