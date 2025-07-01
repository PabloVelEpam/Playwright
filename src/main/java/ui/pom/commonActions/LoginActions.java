package ui.pom.commonActions;

import com.microsoft.playwright.Page;
import ui.pom.BaseUiTest;
import ui.pom.HomePage;
import ui.pom.LoginPage;
import ui.pom.WelcomePage;
import ui.pom.utils.LogHelper;
import ui.pom.utils.PropertiesManager;

import java.io.IOException;

public class LoginActions extends BaseUiTest
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

		loginPage.signIn(config.getUser(), config.getPassword());
		LogHelper.info("Sign up page correctly displayed");

		WelcomePage welcomePage = new WelcomePage(page);
		if (!welcomePage.isAtCorrectPage()) {
			LogHelper.error("It's not at Welcome Page");
			return false;
		}
		return true;
	}

}
