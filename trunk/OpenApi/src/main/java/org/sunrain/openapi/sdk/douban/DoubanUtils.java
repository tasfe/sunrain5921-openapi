package org.sunrain.openapi.sdk.douban;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.Book;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.util.ConfigPropertyReader;

public class DoubanUtils {
	
	public static List<NameValuePair> generateAccessTokenRequestParams(String code) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair(DoubanConstants.CODE_STR, code));
		formparams.add(new BasicNameValuePair(DoubanConstants.CLIENT_ID_STR, ConfigPropertyReader.getValue(DoubanConstants.CLIENT_ID_KEY)));
		formparams.add(new BasicNameValuePair(DoubanConstants.CLIENT_SECRET_STR, ConfigPropertyReader.getValue(DoubanConstants.CLIENT_SECRET_KEY)));
		formparams.add(new BasicNameValuePair(DoubanConstants.GRANT_TYPE_STR, DoubanConstants.AUTHORIZATION_CODE_STR));
		formparams.add(new BasicNameValuePair(DoubanConstants.REDIRECT_URI_STR, ConfigPropertyReader.getValue(DoubanConstants.REDIRECT_URI_KEY)));
		return formparams;
	}
	
	public static AccessToken string2AccessToken(String responseEntity) {
		JSONObject json = JSONObject.fromObject(responseEntity);
		return new AccessToken(json.getString("access_token"), json.getString("expires_in"),json.getString("douban_user_id"));
	}

	/*
	 *{"alt"          : "http://www.douban.com/people/sunrain5921/", 
	 * "avatar"       : "http://img3.douban.com/icon/user_normal.jpg", 
	 * "created"      : "2010-03-01 19:01:17", 
	 * "desc"         : "", 
	 * "id"           : "36240750", 
	 * "is_suicide"   : false, 
	 * "large_avatar" : "http://img3.douban.com/icon/user_large.jpg",
	 * "name"         : "sunrain5921", 
	 * "signature"    : "", 
	 * "type"         : "user", 
	 * "uid"          : "sunrain5921" }
	 */
	
	public static User string2User(String responseEntity) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		JSONObject json = JSONObject.fromObject(responseEntity);
		return new User(null, json.getString("id"), json.getString("uid"), json.getString("name"), 0, 0, null, json.getString("desc"), json.getString("alt"), json.getString("avatar"), null, sdf.parse(json.getString("created")), null);
	}

	public static Book string2Book(String bookString) {
		System.out.println(bookString);
		return new Book();
	}

	public static List<Book> string2BookList(String booksString) {
		System.out.println(booksString);
		return new ArrayList<Book>();
	}
}
