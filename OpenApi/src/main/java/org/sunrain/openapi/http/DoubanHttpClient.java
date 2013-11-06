package org.sunrain.openapi.http;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

public class DoubanHttpClient extends HttpClient {
	
	@Override
	public Header[] access2Headers(String accessToken){
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken));
		Header []basicHeaders = new Header[headers.size()];
		headers.toArray(basicHeaders);
		return basicHeaders;
	}
}
