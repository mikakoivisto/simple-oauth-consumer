package com.liferay.oauth.scribe.provider;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class LiferayApi extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return "http://localhost:8080/c/portal/oauth/access_token";
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format("http://localhost:8080/c/portal/oauth/authorize?oauth_token=%s", requestToken.getToken());
	}

	@Override
	public String getRequestTokenEndpoint() {
		return "http://localhost:8080/c/portal/oauth/request_token";
	}

}
