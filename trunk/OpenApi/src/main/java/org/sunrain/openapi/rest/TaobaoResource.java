package org.sunrain.openapi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.service.TaobaoApi;

@Component
@Scope("request")
@Path("/taobao")
public class TaobaoResource {
	
	@Autowired
	private TaobaoApi taobaoApi;

	@GET
	@Path("/oauth/redirect_url")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public String getRedirectUrl() {
		return taobaoApi.getRedirectUrl();
	}

	@GET
	@Path("/oauth/callback")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public AccessToken callback(@QueryParam("code") String code) throws Exception {
		return taobaoApi.getAccessTokenByCode(code);
	}
	
	@GET
	@Path("/user/buyer/")
	public User userShow(@QueryParam("uid") String uid, @QueryParam("access_token") String accessToken) throws Exception {
		return taobaoApi.getUserByUid(uid, accessToken);
	}
}
