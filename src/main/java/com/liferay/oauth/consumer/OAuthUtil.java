package com.liferay.oauth.consumer;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

import com.liferay.oauth.scribe.provider.LiferayApi;

public class OAuthUtil {

	public static OAuthService getOAuthService() {
		OAuthService service = new ServiceBuilder()
			.provider(LiferayApi.class)
			.apiKey(oauthServiceConfig.getApiKey())
			.apiSecret(oauthServiceConfig.getApiSecret())
			.callback(oauthServiceConfig.getCallbackEndpoint())
			.build();

		return service;
	}

	public static OAuthServiceConfig getOAuthServiceConfig() {
		return oauthServiceConfig;
	}

	public static void setOAuthServiceConfig(OAuthServiceConfig oauthServiceConfig) {
		OAuthUtil.oauthServiceConfig = oauthServiceConfig;
	}

	private static OAuthServiceConfig oauthServiceConfig;
}
