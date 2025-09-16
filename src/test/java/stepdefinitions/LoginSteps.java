package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.HomePage;
import pom.LoginPage;
import pom.WelcomePage;
import utils.LogHelper;

public class LoginSteps {

	private Page page = utils.PlaywrightContext.getPage();
	private HomePage homePage = new HomePage(page);
	private WelcomePage welcomePage = new WelcomePage(page);
	private LoginPage loginPage = new LoginPage(page);


	@When("I navigate to the Login Page")
	public void navigateToLoginPage() {
		homePage.clickLogin();
		Assert.assertTrue(loginPage.isAtCorrectPage(), "It's not at Welcome Page");
	}

	@When("I sign in with the username {string} and password {string}")
	public void signInWithUsernameAndPassword(String username, String password) {
		loginPage.signIn(username, password);
		LogHelper.info("Login action performed with provided credentials");
	}


	@Then("the Welcome Page should be correctly displayed")
	public void welcomePageShouldBeCorrectlyDisplayed() {
		LogHelper.info("Welcome Page is correctly displayed");
	}
}
