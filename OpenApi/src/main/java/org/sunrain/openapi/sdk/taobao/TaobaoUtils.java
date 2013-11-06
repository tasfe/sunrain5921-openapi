package org.sunrain.openapi.sdk.taobao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.model.AccessToken;
import org.sunrain.openapi.model.User;
import org.sunrain.openapi.util.ConfigPropertyReader;
import org.sunrain.openapi.util.Constants;
import org.sunrain.openapi.util.StringUtils;

public class TaobaoUtils {
	
	public static List<NameValuePair> generateAccessTokenRequestParams(String code) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair(TaobaoConstants.CODE_STR, code));
		formparams.add(new BasicNameValuePair(TaobaoConstants.CLIENT_ID_STR, ConfigPropertyReader.getValue(TaobaoConstants.CLIENT_ID_KEY)));
		formparams.add(new BasicNameValuePair(TaobaoConstants.CLIENT_SECRET_STR, ConfigPropertyReader.getValue(TaobaoConstants.CLIENT_SECRET_KEY)));
		formparams.add(new BasicNameValuePair(TaobaoConstants.GRANT_TYPE_STR, TaobaoConstants.AUTHORIZATION_CODE_STR));
		formparams.add(new BasicNameValuePair(TaobaoConstants.REDIRECT_URI_STR, ConfigPropertyReader.getValue(TaobaoConstants.REDIRECT_URI_KEY)));
		return formparams;
	}
	
	public static String signTopRequestNew(List<NameValuePair> requestParameters, String secret, boolean isHmac) throws IOException {
		Map<String, String> params = new HashMap<String,String>();
		for(NameValuePair pair : requestParameters){
			params.put(pair.getName(), pair.getValue());
		}
		return signTopRequestNew(params, secret, isHmac);
	}
	
	private static String signTopRequestNew(Map<String, String> params, String secret, boolean isHmac) throws IOException {
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if (!isHmac) {
			query.append(secret);
		}
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				query.append(key).append(value);
			}
		}
		// 第三步：使用MD5/HMAC加密
		byte[] bytes;
		if (isHmac) {
			bytes = encryptHMAC(query.toString(), secret);
		} else {
			query.append(secret);
			bytes = encryptMD5(query.toString());
		}
		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}
	
	private static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg=getStringFromException(gse);
			throw new IOException(msg);
		}
		return bytes;
	}

	private static String getStringFromException(Throwable e) {
		String result = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(bos);
		e.printStackTrace(ps);
		try {
			result = bos.toString(Constants.CHARSET_UTF8);
		} catch (IOException ioe) {
		}
		return result;
	}

	private static byte[] encryptMD5(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg);
		}
		return bytes;
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	/*
	 * {
  "w2_expires_in": 1800,
  "taobao_user_id": "356438231",
  "taobao_user_nick": "sunrain5921",
  "w1_expires_in": 86400,
  "re_expires_in": 2592000,
  "r2_expires_in": 86400,
  "expires_in": 86400,
  "token_type": "Bearer",
  "refresh_token": "62007256d7f7e28ae166aacbe72f178face94172c370fcd356438231",
  "access_token": "6201925d6785c4d81b71a9eb261f58a04ZZac1894eff04f356438231",
  "r1_expires_in": 86400
}
	 */
	public static AccessToken stringToAccessToken(String taobaoResponse) {
		JSONObject json = JSONObject.fromObject(taobaoResponse);
		return new AccessToken(json.getString("access_token"),
				json.getString("w2_expires_in"), 
				json.getString("taobao_user_id"));
	}

	public static User string2User(String string) {
		System.out.println(string);
		JSONObject json = JSONObject.fromObject(string);
		return new User(null, null, json.getString("nick"), json.getString("nick"), 0,
				0, null, null, null,
				null, null, null, null);

	}
}
