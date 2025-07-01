package api.services;

import api.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import ui.pom.utils.PropertiesManager;

@Slf4j
public class BaseService
{

	protected static String loginToken;

	protected RequestSpecification getRequestSpecification()
	{
		return RestAssured.given().baseUri(PropertiesManager.getApiBaseUrl()).contentType(ContentType.JSON)
				.header("sec-ch-ua-platform", "\"Windows\"")
				.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")
				.header("accept", "application/json, text/plain, */*")
				.header("sec-ch-ua", "\"Chromium\";v=\"133\", \"Not(A:Brand\";v=\"99\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("origin", "https://conduit-realworld-example-app.fly.dev")
				.header("sec-fetch-site", "same-origin")
				.header("sec-fetch-mode", "cors")
				.header("sec-fetch-dest", "empty")
				.header("referer", "https://conduit-realworld-example-app.fly.dev/")
				.header("accept-language", "en-US,en;q=0.9");
	}

	public void setLoginToken(String loginToken)
	{
		this.loginToken = "Token " + loginToken;
	}

	public String getLoginToken()
	{
		return loginToken;
	}

}
