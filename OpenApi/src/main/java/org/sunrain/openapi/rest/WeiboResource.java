package org.sunrain.openapi.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.City;
import org.sunrain.openapi.model.Country;
import org.sunrain.openapi.model.Province;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.model.Weibo;
import org.sunrain.openapi.service.WeiboApi;

@Component
@Scope("request")
@Path("/weibo")
public class WeiboResource {

	@Autowired
	private WeiboApi weiboApi;

	@GET
	@Path("/oauth/redirect_url")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public String getRedirectUrl() {
		return weiboApi.getRedirectUrl();
	}

	@GET
	@Path("/oauth/callback")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public AccessToken callback(@QueryParam("code") String code)
			throws Exception {
		return weiboApi.getAccessTokenByCode(code);
	}

	@GET
	@Path("/users/show")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public User userShow(@QueryParam("uid") String uid, @QueryParam("access_token") String accessToken) throws Exception {
		return weiboApi.getUserByUid(uid, accessToken);
	}

	@GET
	@Path("/common/countries")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<Country> getCountries(
			@QueryParam("access_token") String accessToken) throws Exception {
		return weiboApi.getCountries(accessToken);
	}

	@GET
	@Path("/common/provinces")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<Province> getProvinces(
			@QueryParam("access_token") String accessToken,
			@QueryParam("country_code") String countryCode) throws Exception {
		return weiboApi.getProvinces(accessToken, countryCode);
	}

	@GET
	@Path("/common/cities")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<City> getCities(@QueryParam("access_token") String accessToken,
			@QueryParam("province_code") String provinceCode) throws Exception {
		return weiboApi.getCities(accessToken, provinceCode);
	}

	@GET
	@Path("/statuses/public_timeline")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<Weibo> getStatusesPublicTimeline(
			@QueryParam("access_token") String accessToken,
			@DefaultValue("20") @QueryParam("page_size") int pageSize)
			throws Exception {
		return weiboApi.getPublicTimeline(accessToken, pageSize);
	}

	@GET
	@Path("/statuses/friends_timeline")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public List<Weibo> getStatusesFriendsTimeline(
			@QueryParam("access_token") String accessToken,
			@DefaultValue("1") @QueryParam("start_index") int startIndex,
			@DefaultValue("20") @QueryParam("page_size") int pageSize)
			throws Exception {
		return weiboApi.getFriendsTimeline(accessToken, startIndex, pageSize);
	}

	@POST
	@Path("/statuses/update")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public Weibo publishStatus(@FormParam("access_token") String accessToken,
			@FormParam("status") String status) throws Exception {
		return weiboApi.publishStatus(accessToken, status);
	}

	@POST
	@Path("/statuses/repost")
	@Produces({ "application/json;qs=1", "application/xml;qs=.5" })
	public Weibo repostStatus(@FormParam("access_token") String accessToken,
			@FormParam("status_id") String statusId,
			@FormParam("status") String status,
			@FormParam("is_comment") int isComment) throws Exception {
		return weiboApi.repostStatus(accessToken, statusId, status, isComment);
	}
}
