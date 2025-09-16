package commonActions;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import pom.BaseTest;
import pom.HomePage;
import pom.LoginPage;
import pom.WelcomePage;
import utils.LogHelper;
import utils.PropertiesManager;

import java.io.IOException;

public class LoginActions extends BaseTest
{
	private Page page;
	PropertiesManager config;
	public LoginActions(Page page)
	{
		this.page = page;
	}

	public boolean login() throws IOException
	{

		HomePage homePage = new HomePage(page);
		if (!homePage.isAtCorrectPage()) {
			LogHelper.error("It's not at Home Page");
			return false;
		}
		LogHelper.info("Home page correctly displayed");
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(page);
		if (!loginPage.isAtCorrectPage()) {
			LogHelper.error("It's not at Login Page");
			return false;
		}
		config = new PropertiesManager("src/main/resources/application.properties");

		loginPage.signIn(config.getProperty("correct.user"), config.getProperty("correct.password"));
		LogHelper.info("Sign up page correctly displayed");

		WelcomePage welcomePage = new WelcomePage(page);
		if (!welcomePage.isAtCorrectPage()) {
			LogHelper.error("It's not at Welcome Page");
			return false;
		}
		return true;
	}

}
