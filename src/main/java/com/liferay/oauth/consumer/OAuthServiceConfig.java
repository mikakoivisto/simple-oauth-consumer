package com.liferay.oauth.consumer;

public class OAuthServiceConfig {

	public OAuthServiceConfig(
		String apiKey, String apiSecret, String accessTokenEndpoint, String authorizeEndpoint,
		String requestTokenEndpoint, String callbackEndpoint, String notesEndpoint, String userEndpoint) {

		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.accessTokenEndpoint = accessTokenEndpoint;
		this.authorizeEndpoint = authorizeEndpoint;
		this.requestTokenEndpoint = requestTokenEndpoint;
		this.callbackEndpoint = callbackEndpoint;
		this.notesJsonWsEndpoint = notesEndpoint;
		this.userJsonWsEndpoint = userEndpoint;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getAccessTokenEndpoint() {
		return accessTokenEndpoint;
	}

	public void setAccessTokenEndpoint(String accessTokenEndpoint) {
		this.accessTokenEndpoint = accessTokenEndpoint;
	}

	public String getAuthorizeEndpoint() {
		return authorizeEndpoint;
	}

	public void setAuthorizeEndpoint(String authorizeEndpoint) {
		this.authorizeEndpoint = authorizeEndpoint;
	}

	public String getCallbackEndpoint() {
		return callbackEndpoint;
	}

	public void setCallbackEndpoint(String callbackEndpoint) {
		this.callbackEndpoint = callbackEndpoint;
	}

	public String getNotesJsonWsEndpoint() {
		return notesJsonWsEndpoint;
	}

	public void setNotesJsonWsEndpoint(String notesJsonWsEndpoint) {
		this.notesJsonWsEndpoint = notesJsonWsEndpoint;
	}

	public String getRequestTokenEndpoint() {
		return requestTokenEndpoint;
	}

	public void setRequestTokenEndpoint(String requestTokenEndpoint) {
		this.requestTokenEndpoint = requestTokenEndpoint;
	}

	public String getUserJsonWsEndpoint() {
		return userJsonWsEndpoint;
	}

	public void setUserJsonWsEndpoint(String userJsonWsEndpoint) {
		this.userJsonWsEndpoint = userJsonWsEndpoint;
	}

	private String apiKey;
	private String apiSecret;
	private String accessTokenEndpoint;
	private String authorizeEndpoint;
	private String callbackEndpoint;
	private String notesJsonWsEndpoint;
	private String requestTokenEndpoint;
	private String userJsonWsEndpoint;
}
