package tests;

import commonActions.LoginActions;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.EditorPage;
import pom.HomePage;
import pom.LoginPage;
import pom.SignUpPage;
import pom.SpecificArticlePage;
import pom.WelcomePage;
import testData.dataproviders.CsvDataProvider;
import testData.dataproviders.JsonDataProvider;
import utils.Hooks;
import utils.LogHelper;
import utils.RandomGenerator;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Tests extends Hooks {


	@Test
	public void signUpTest(){
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(),"It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
		homePage.clickSignUp();

		String user = RandomGenerator.getRandomString("user");
		String email = RandomGenerator.getRandomEmail(user);
		String password = RandomGenerator.getRandomString("pass");
		SignUpPage signUpPage = new SignUpPage(page);

		Assert.assertTrue(signUpPage.isAtCorrectPage(),"It's not at Sig Up Page");
		LogHelper.info("Sign up page correctly displayed");
		signUpPage.signUp(user,email,password);

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertTrue(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}

	@Test(dataProvider = "csvLoginUserDataProvider", dataProviderClass = CsvDataProvider.class)
	public void loginTest(String username, String password, String expectedResult){
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(),"It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(page);
		Assert.assertTrue(loginPage.isAtCorrectPage(), "It's not at Login Page");
		loginPage.signIn( username, password);
		LogHelper.info("Sign up page correctly displayed");

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertEquals(welcomePage.isAtCorrectPage(), Boolean.parseBoolean(expectedResult), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}

	@Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class)
	public void newArticleTest(String articleTitle, String articleAbout, String articleContent, List<String> articleTags)
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
