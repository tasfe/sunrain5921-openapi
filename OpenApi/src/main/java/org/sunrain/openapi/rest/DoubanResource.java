package org.sunrain.openapi.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.Book;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.service.DoubanApi;

@Component
@Scope("request")
@Path("/douban")
public class DoubanResource {

	@Autowired
	private DoubanApi doubanApi;

	@GET
	@Path("/oauth/redirect_url")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public String getRedirectUrl() {
		return doubanApi.getRedirectUrl();
	}

	@GET
	@Path("/oauth/callback")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public AccessToken callback(@QueryParam("code") String code)
			throws Exception {
		return doubanApi.getAccessTokenByCode(code);
	}

	@GET
	@Path("/user/~me")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public User getCurrentUser(@QueryParam("access_token") String accessToken)
			throws Exception {
		return doubanApi.getCurrentUser(accessToken);
	}

	@GET
	@Path("/user/{uid}")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public User getUserProfileByUid(
			@QueryParam("access_token") String accessToken,
			@PathParam("uid") String uid) throws Exception {
		return doubanApi.getUserProfileByUid(accessToken, uid);
	}

	@GET
	@Path("/book/search")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<Book> searchBooksByKeyword(
			@QueryParam("access_token") String accessToken,
			@QueryParam("keyword") String keyword,
			@DefaultValue("1") @QueryParam("start_index") int startIndex,
			@DefaultValue("20") @QueryParam("page_size") int pageSize) throws Exception {
		return doubanApi.searchBooksByKeyword(accessToken, keyword, startIndex, pageSize);
	}
}
