package tests.ui;

import ui.pom.commonActions.LoginActions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pom.EditorPage;
import ui.pom.SpecificArticlePage;
import ui.pom.WelcomePage;
import testData.dataproviders.JsonDataProvider;
import ui.pom.utils.Hooks;

import java.io.IOException;
import java.util.List;

public class ArticleHandlingUiTests extends Hooks
{

	@Test(groups = { "regression" }, dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class)
	public void newArticleUiTest(String articleTitle, String articleAbout, String articleContent, List<String> articleTags)
			throws IOException
	{
		LoginActions loginActions = new LoginActions(page);
		loginActions.login();

		WelcomePage welcomePage = new WelcomePage(page);
		welcomePage.clickNewArticle();

		EditorPage editorPage = new EditorPage(page);
		Assert.assertTrue(editorPage.isAtCorrectPage(), "It's not at Editor Page");

		editorPage.publishArticle(articleTitle, articleAbout, articleContent, articleTags);
		SpecificArticlePage specificArticlePage = new SpecificArticlePage(page);
		Assert.assertTrue(specificArticlePage.isAtCorrectPage());
	}

}
