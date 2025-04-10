import org.testng.Assert;
import org.testng.annotations.Test;

import pom.*;
import utils.Hooks;
import utils.RandomGenerator;



public class Tests extends Hooks {

	@Test
	public void signUpTest(){
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(),"It's not at Home Page");
		homePage.clickSignUp();

		String user = RandomGenerator.getRandomString("user");
		String email = RandomGenerator.getRandomEmail(user);
		String password = RandomGenerator.getRandomString("pass");
		SignUpPage signUpPage = new SignUpPage(page);

		Assert.assertTrue(signUpPage.isAtCorrectPage(),"It's not at Sig Up Page");
		signUpPage.signUp(user,email,password);

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertTrue(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
	}

	@Test
	public void loginTest(){
		HomePage homePage = new HomePage(page);
		Assert.assertTrue(homePage.isAtCorrectPage(),"It's not at Home Page");
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(page);
		Assert.assertTrue(loginPage.isAtCorrectPage(), "It's not at Login Page");
		loginPage.signIn("PabloTestUser", "PabloTestUser@gmail.com", "Password1234");

		WelcomePage welcomePage = new WelcomePage(page);
		Assert.assertTrue(welcomePage.isAtCorrectPage(), "It's not at Welcome Page");
	}
}
