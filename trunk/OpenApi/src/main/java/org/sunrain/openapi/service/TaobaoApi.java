package org.sunrain.openapi.service;

import org.sunrain.openapi.model.User;

public interface TaobaoApi {
	public User getUserByUid(String uid, String accessToken) throws Exception;
}
