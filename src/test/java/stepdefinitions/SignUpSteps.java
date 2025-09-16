package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pom.HomePage;
import pom.SignUpPage;
import pom.WelcomePage;
import utils.LogHelper;
import utils.RandomGenerator;

public class SignUpSteps {
	private Page page = utils.PlaywrightContext.getPage();
	private HomePage homePage = new HomePage(page);
	private SignUpPage signUpPage = new SignUpPage(page);


	@When("I navigate to the sign-up page")
	public void NavigateToTheSignUpPage() {
		homePage.clickSignUp();
	}

	@When("I sign up with a new user")
	public void SignUpWithANewUser() {
		String user = RandomGenerator.getRandomString("user");
		String email = RandomGenerator.getRandomEmail(user);
		String password = RandomGenerator.getRandomString("pass");

		Assert.assertTrue(signUpPage.isAtCorrectPage(), "It's not at Sign Up Page");
		LogHelper.info("Sign up page correctly displayed");
		signUpPage.signUp(user, email, password);
	}

	@Given("on the home page")
	public void onTheHomePage()
	{
		Assert.assertTrue(homePage.isAtCorrectPage(), "It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
	}

}
