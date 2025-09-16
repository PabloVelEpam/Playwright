package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.HomePage;
import pom.WelcomePage;
import utils.LogHelper;

public class CommonSteps
{
	private Page page = utils.PlaywrightContext.getPage();
	private HomePage homePage = new HomePage(page);
	private WelcomePage welcomePage = new WelcomePage(page);
	@Given("I am on the home page")
	public void AmOnTheHomePage() {
		Assert.assertTrue(homePage.isAtCorrectPage(), "It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
	}

	@Then("I should see the welcome page")
	public void ShouldSeeTheWelcomePage() {
		Assert.assertTrue(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}
}
