package com.liferay.oauth.consumer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.context.request.RequestAttributes.*;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.liferay.oauth.consumer.OAuthServiceConfig;
import com.liferay.oauth.consumer.OAuthUtil;

@Controller
public class OAuthController {

	@RequestMapping("/authorize")
	public ModelAndView authrorize(WebRequest request) throws IOException {
		Token requestToken = (Token) request.getAttribute(OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		Token accessToken = (Token) request.getAttribute(OAUTH_ACCESS_TOKEN, SCOPE_SESSION);

		if (requestToken == null || accessToken == null) {
			OAuthService service = OAuthUtil.getOAuthService();

			requestToken = service.getRequestToken();

			request.setAttribute(OAUTH_REQUEST_TOKEN, requestToken, SCOPE_SESSION);

			return new ModelAndView("redirect:" + service.getAuthorizationUrl(requestToken));
		}

		return new ModelAndView("redirect:/protected");
	}

	@RequestMapping("/callback")
	public ModelAndView callback(@RequestParam(value="oauth_verifier", required=false) String oauthVerifier, WebRequest request) {

		Token requestToken = (Token) request.getAttribute(OAUTH_REQUEST_TOKEN, SCOPE_SESSION);

		OAuthService service = OAuthUtil.getOAuthService();

		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);

		request.setAttribute(OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);

		return new ModelAndView("redirect:/protected");
	}

	@RequestMapping("/notes")
	public ModelAndView getNotes(WebRequest request) {
		OAuthService service = OAuthUtil.getOAuthService();

		Token accessToken = (Token) request.getAttribute(OAUTH_ACCESS_TOKEN, SCOPE_SESSION);

		if (accessToken == null) {
			return new ModelAndView("redirect:/authorize");
		}

		OAuthServiceConfig config = OAuthUtil.getOAuthServiceConfig();

		OAuthRequest oauthRequest = new OAuthRequest(Verb.POST, config.getNotesJsonWsEndpoint());

		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();
	  
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();

	    JsonParser jp = new JsonParser();
	    JsonElement je = jp.parse(oauthResponse.getBody());

	    String prettyJsonString = gson.toJson(je);
	 
	    request.setAttribute("notes", prettyJsonString, SCOPE_SESSION);

	    return new ModelAndView("redirect:/protected");
	}

	@RequestMapping("/user")
	public ModelAndView getUser(WebRequest request) {
		OAuthService service = OAuthUtil.getOAuthService();

		Token accessToken = (Token) request.getAttribute(OAUTH_ACCESS_TOKEN, SCOPE_SESSION);

		if (accessToken == null) {
			return new ModelAndView("redirect:/authorize");
		}

		OAuthServiceConfig config = OAuthUtil.getOAuthServiceConfig();

		OAuthRequest oauthRequest = new OAuthRequest(
				Verb.POST, config.getUserJsonWsEndpoint());

		service.signRequest(accessToken, oauthRequest);

	    Response oauthResponse = oauthRequest.send();
	  
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();

	    JsonParser jp = new JsonParser();
	    JsonElement je = jp.parse(oauthResponse.getBody());

	    String prettyJsonString = gson.toJson(je);
	 
	    request.setAttribute("user", prettyJsonString, SCOPE_SESSION);

	    return new ModelAndView("redirect:/protected");
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();

		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/protected")
	public ModelAndView protectedResource(WebRequest request) {
		Token accessToken = (Token) request.getAttribute(OAUTH_ACCESS_TOKEN, SCOPE_SESSION);

		if (accessToken == null) {
			return new ModelAndView("redirect:/authorize");
		}

		return new ModelAndView("/index");
	}

	private static final String OAUTH_ACCESS_TOKEN = "oauthAccessToken";
	private static final String OAUTH_REQUEST_TOKEN = "oauthRequestToken";
}
