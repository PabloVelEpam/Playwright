package tests.api;

import api.models.responses.LoginResponse;
import api.models.responses.PublishArticleResponse;
import api.services.ArticlesService;
import api.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.dataproviders.JsonDataProvider;

import java.util.List;

public class ArticleHandlingApiTests
{

	@Test(groups = { "regression" }, dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class)
	public void articleHandlingApiTest(String title, String description, String body, List<String> tagList)
	{
		LoginService loginService = new LoginService();
		LoginResponse loginResponse = loginService.regularLogin();

		Assert.assertEquals(loginResponse.getStatusCode(), 200);

		ArticlesService articlesService = new ArticlesService();
		PublishArticleResponse publishArticleResponse = articlesService.publishArticle(title, description, body, tagList);

		Assert.assertEquals(publishArticleResponse.getStatusCode(), 201);
		Assert.assertEquals(publishArticleResponse.getArticle().getDescription(), description);
		Assert.assertEquals(publishArticleResponse.getArticle().getBody(), body);
		Assert.assertEquals(publishArticleResponse.getArticle().getTagList(), tagList);
	}

}
