package tests.ui;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.dataproviders.LoginCsvDataProvider;
import ui.pom.HomePage;
import ui.pom.LoginPage;
import ui.pom.SignUpPage;
import ui.pom.WelcomePage;
import ui.pom.utils.Hooks;
import ui.pom.utils.LogHelper;
import ui.pom.utils.RandomGenerator;

@Slf4j
public class SignInUiTests extends Hooks
{

	@Test(groups = { "regression" })
	public void signUpUiTest()
	{
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(), "It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
		homePage.clickSignUp();

		String user = RandomGenerator.getRandomString("user");
		String email = RandomGenerator.getRandomEmail(user);
		String password = RandomGenerator.getRandomString("pass");
		SignUpPage signUpPage = new SignUpPage(page);

		Assert.assertTrue(signUpPage.isAtCorrectPage(), "It's not at Sig Up Page");
		LogHelper.info("Sign up page correctly displayed");
		signUpPage.signUp(user, email, password);

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertTrue(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}

	@Test(groups = { "smoke", "regression" }, dataProvider = "csvLoginUserUiDataProvider", dataProviderClass = LoginCsvDataProvider.class)
	public void SignInUiTest(String username, String password, String expectedResult)
	{
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(), "It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(page);
		Assert.assertTrue(loginPage.isAtCorrectPage(), "It's not at Login Page");
		loginPage.signIn(username, password);
		LogHelper.info("Sign up page correctly displayed");

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertEquals(welcomePage.isAtCorrectPage(), Boolean.parseBoolean(expectedResult), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}

}
