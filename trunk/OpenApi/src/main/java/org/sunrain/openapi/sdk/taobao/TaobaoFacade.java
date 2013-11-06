package org.sunrain.openapi.sdk.taobao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.sunrain.openapi.http.TaobaoHttpClient;
import org.sunrain.openapi.util.ConfigPropertyReader;
import org.sunrain.openapi.util.Constants;

public class TaobaoFacade {
	public static HttpResponse getResponseOfAccessToken(String code) throws Exception {
		return new TaobaoHttpClient().post(ConfigPropertyReader.getValue(TaobaoConstants.ACCESSTOKEN_URL_KEY), TaobaoUtils.generateAccessTokenRequestParams(code));
	}

	public static HttpResponse showUserById(String uid, String accessToken) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair(TaobaoConstants.METHOD, "taobao.user.buyer.get"));
		requestParams.add(new BasicNameValuePair("fields", uid));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.TIMESTAMP, sdf.format(new Date())));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.VERSION, "2.0"));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.FORMAT, Constants.FORMAT_JSON));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.SIGN_METHOD, Constants.SIGN_METHOD_HMAC));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.SESSION, accessToken));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.APP_KEY, ConfigPropertyReader.getValue(TaobaoConstants.CLIENT_ID_KEY)));
		requestParams.add(new BasicNameValuePair(TaobaoConstants.SIGN, TaobaoUtils.signTopRequestNew(requestParams, ConfigPropertyReader.getValue(TaobaoConstants.CLIENT_SECRET_KEY), true)));
		return new TaobaoHttpClient().get(accessToken, ConfigPropertyReader.getValue(TaobaoConstants.BASE_URL_KEY), requestParams);
	}
}
