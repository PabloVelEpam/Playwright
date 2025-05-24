package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import utils.LogHelper;

@Slf4j
public class SignUpPage extends BaseTest
{

	private Locator userField;
	private Locator emailField;
	private Locator passwordField;
	private Locator signUpBtn;
	private Locator sigUpTitle;
	private Page page;

	public SignUpPage(Page page)
	{
		if (page == null)
		{
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.sigUpTitle = page.locator("//h1[text() = 'Sign up']");
		this.userField = page.locator("//input[@name='username']");
		this.emailField = page.locator("//input[@name='email']");
		this.passwordField = page.locator("//input[@name='password']");
		this.signUpBtn = page.locator("//button[text()='Sign up']");
	}

	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = sigUpTitle.isVisible();
		if (isVisible)
		{
			LogHelper.debug("Sign up title is visible. User is at the correct page");
		}
		else
		{
			log.warn("Sign up title is not visible. User might not be at the correct page");
		}
		return isVisible;
	}

	public void signUp(String user, String email, String password)
	{
		validateField(user, "Username");
		validateField(email, "Email");
		validateField(password, "Password");

		fillField(userField, user);
		fillField(emailField, email);
		fillField(passwordField, password);
		clickOnElement(signUpBtn);
	}

}