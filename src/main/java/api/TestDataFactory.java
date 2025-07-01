package api;

import api.models.payloads.ArticlePayload;
import api.models.requests.LoginRequest;
import api.models.requests.PublishArticleRequest;
import api.models.payloads.UserPayload;
import ui.pom.utils.PropertiesManager;
import ui.pom.utils.RandomGenerator;

import java.util.List;

public class TestDataFactory
{
	public static LoginRequest createDefaultLoginRequest()
	{
		UserPayload userPayload = new UserPayload(PropertiesManager.getUser(),PropertiesManager.getPassword());
		return LoginRequest.builder().user(userPayload).build();
	}

	public static LoginRequest createCustomUser(String user, String password)
	{
		UserPayload userPayload = new UserPayload(user, password);
		return LoginRequest.builder().user(userPayload).build();
	}

	public static PublishArticleRequest createArticle(String title, String description, String body, List<String> tagList){
		String randomString = RandomGenerator.getRandomString("Random-");
		ArticlePayload article = ArticlePayload.builder().title(title+randomString).description(description).body(body).tagList(tagList).build();
		return new PublishArticleRequest(article);
	}

}
