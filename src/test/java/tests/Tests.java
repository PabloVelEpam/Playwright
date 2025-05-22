package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;
import pom.SignUpPage;
import pom.WelcomePage;
import utils.Hooks;
import utils.LogHelper;
import utils.RandomGenerator;

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

	@Test
	public void loginTest(){
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(),"It's not at Home Page");
		LogHelper.info("Home page correctly displayed");
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(page);
		Assert.assertTrue(loginPage.isAtCorrectPage(), "It's not at Login Page");
		loginPage.signIn( "PabloTestUser@gmail.com", "Password1234");
		LogHelper.info("Sign up page correctly displayed");

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertFalse(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
		LogHelper.info("Welcome page correctly displayed");
	}
}
