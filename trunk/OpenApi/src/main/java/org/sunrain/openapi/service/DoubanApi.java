package org.sunrain.openapi.service;

import java.util.List;
import org.sunrain.openapi.model.Book;
import org.sunrain.openapi.model.User;

public interface DoubanApi extends OauthApi {
	public User getCurrentUser(String accessToken) throws Exception;
	public User getUserProfileByUid(String accessToken, String uid) throws Exception;
	public List<Book> searchBooksByKeyword(String accessToken, String keyword, int startIndex, int pageSize) throws Exception;
}
