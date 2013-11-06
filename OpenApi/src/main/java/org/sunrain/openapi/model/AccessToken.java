package org.sunrain.openapi.model;

import java.io.Serializable;

public class AccessToken implements Serializable {

	private static final long serialVersionUID = 6986530164134648944L;

	public AccessToken(){}
	
	public AccessToken(String accessToken, String expireIn, String uid) {
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.uid = uid;
	}

	private String accessToken;
	private String expireIn;
	private String uid;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
