package org.sunrain.openapi.sdk.weibo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.City;
import org.sunrain.openapi.model.Country;
import org.sunrain.openapi.model.Province;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.model.Weibo;
import org.sunrain.openapi.util.ConfigPropertyReader;

public final class WeiboUtils {
	
	private static Map<String,SimpleDateFormat> formatMap = new HashMap<String,SimpleDateFormat>();
	
	public static List<NameValuePair> generateAccessTokenRequestParams(String code) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair(WeiboConstants.CODE_STR, code));
		formparams.add(new BasicNameValuePair(WeiboConstants.CLIENT_ID_STR, ConfigPropertyReader.getValue(WeiboConstants.CLIENT_ID_KEY)));
		formparams.add(new BasicNameValuePair(WeiboConstants.CLIENT_SECRET_STR, ConfigPropertyReader.getValue(WeiboConstants.CLIENT_SECRET_KEY)));
		formparams.add(new BasicNameValuePair(WeiboConstants.GRANT_TYPE_STR, WeiboConstants.AUTHORIZATION_CODE_STR));
		formparams.add(new BasicNameValuePair(WeiboConstants.REDIRECT_URI_STR, ConfigPropertyReader.getValue(WeiboConstants.REDIRECT_URI_KEY)));
		return formparams;
	}
	
	public static AccessToken stringToAccessToken(String weiboResponse) {
		JSONObject json = JSONObject.fromObject(weiboResponse);
		return new AccessToken(json.getString("access_token"),
				json.getString("expires_in"), 
				json.getString("uid"));
	}
	
	public static List<NameValuePair> generateShowUserByUidRequestParams(String uid){
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("uid", uid));
		return formparams;
	}

	public static User string2User(String string) throws Exception {
		/*
		 * {"id":1309438044,
		 * "idstr":"1309438044",
		 * "class":1,
		 * "screen_name":"Sunrain-晴日雨",
		 * "name":"Sunrain-晴日雨",
		 * "province":"11",
		 * "city":"1000",
		 * "location":"北京",
		 * "description":"",
		 * "url":"",
		 * "profile_image_url":"http://tp1.sinaimg.cn/1309438044/50/5673611730/1",
		 * "profile_url":"u/1309438044",
		 * "domain":"",
		 * "weihao":"",
		 * "gender":"m",
		 * "followers_count":106,
		 * "friends_count":412,
		 * "statuses_count":216,
		 * "favourites_count":2,
		 * "created_at":"Fri Dec 17 12:03:23 +0800 2010",
		 * "following":false,
		 * "allow_all_act_msg":false,
		 * "geo_enabled":true,
		 * "verified":false,
		 * "verified_type":-1,
		 * "remark":"",
		 * "status":{"created_at":"Sun Oct 27 00:59:04 +0800 2013","id":3637817517742030,"mid":"3637817517742030","idstr":"3637817517742030","text":"与其与自己讨厌的人在一起伪善的寒暄，还不如过好两个人的小日子，这是近半年来最深刻的领悟！ 我在:http://t.cn/zRqntdH","source":"<a href=\"http://app.weibo.com/t/feed/c66T5g\" rel=\"nofollow\">Android客户端</a>",
		 * "favorited":false,
		 * "truncated":false,
		 * "in_reply_to_status_id":"",
		 * "in_reply_to_user_id":"",
		 * "in_reply_to_screen_name":"",
		 * "pic_urls":[],
		 * "geo":null,
		 * "annotations":[{"client_mblogid":"d7638400-1742-4ed2-a9a2-6d8f7562c693"}],"reposts_count":0,"comments_count":0,"attitudes_count":0,"mlevel":0,"visible":{"type":0,"list_id":0}},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tp1.sinaimg.cn/1309438044/180/5673611730/1","avatar_hd":"http://tp1.sinaimg.cn/1309438044/180/5673611730/1","verified_reason":"","follow_me":false,"online_status":0,"bi_followers_count":47,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0}
		 */
		JSONObject json = JSONObject.fromObject(string);
		return new User(json.getString("id"), null, json.getString("screen_name"), json.getString("name"), json.getInt("province"),
				json.getInt("city"), json.getString("location"), json.getString("description"), json.getString("url"),
				json.getString("profile_image_url"), json.getString("gender"), parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy"), json.getString("gender"));
	}
	
	public static List<Country> string2CountryList(String responseEntity) throws Exception {
		List<Country> countries = new ArrayList<Country>();
		String[] countryArray = responseEntity.replaceAll("[\\[\\]\\{\\}\\\\\"]", "").split(",");
		for (int i = 0; i < countryArray.length; i++) {
			String[] strArray = countryArray[i].split(":");
			countries.add(new Country(strArray[0], strArray[1]));
		}
		return countries;
	}
	
	public static List<Province> string2ProvinceList(String responseEntity) throws Exception {
		List<Province> provinces = new ArrayList<Province>();
		String[] provinceArray = responseEntity.replaceAll("[\\[\\]\\{\\}\\\\\"]", "").split(",");
		for (int i = 0; i < provinceArray.length; i++) {
			String[] strArray = provinceArray[i].split(":");
			provinces.add(new Province(strArray[0], strArray[1]));
		}
		return provinces;
	}
	
	public static List<City> string2CityList(String responseEntity) throws Exception {
		List<City> cities = new ArrayList<City>();
		String[] cityArray = responseEntity.replaceAll("[\\[\\]\\{\\}\\\\\"]", "").split(",");
		for (int i = 0; i < cityArray.length; i++) {
			String[] strArray = cityArray[i].split(":");
			cities.add(new City(strArray[0], strArray[1]));
		}
		return cities;
	}
	
	public static List<Weibo> string2WeiboList(String responseEntity) throws Exception {
		return new ArrayList<Weibo>();
	}
	
	public static Weibo string2Weibo(String str) {
		System.out.println(str);
		return new Weibo();
	}
	
	private static Date parseDate(String str, String format) throws Exception {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			formatMap.put(format, sdf);
		}
		synchronized (sdf) {
			return sdf.parse(str);
		}
	}
}