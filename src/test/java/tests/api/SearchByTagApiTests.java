package tests.api;

import api.models.responses.ArticlesResponse;
import api.models.responses.LoginResponse;
import api.services.ArticlesService;
import api.services.BaseService;
import api.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchByTagApiTests extends BaseService
{

	@Test(groups = { "regression" })
	public void searchArticleByTagApiTests()
	{
		LoginService loginService = new LoginService();
		LoginResponse loginResponse = loginService.regularLogin();

		Assert.assertEquals(loginResponse.getStatusCode(), 200);

		String tagToSearch = "implementation";

		ArticlesService articlesService = new ArticlesService();
		ArticlesResponse articlesResponse = articlesService.searchByTag(tagToSearch);

		Assert.assertEquals(articlesResponse.getStatusCode(), 200);
		Assert.assertTrue(articlesResponse.getArticles().stream().allMatch(article -> article.getTagList().contains(tagToSearch)));
	}

}
