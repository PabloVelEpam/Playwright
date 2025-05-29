package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import utils.LogHelper;

@Slf4j
public class LoginPage extends BaseTest implements BasePage
{

	private Locator emailField;
	private Locator passwordField;
	private Locator loginButton;
	private Locator sigInTitle;
	private Page page;

	public LoginPage(Page page)
	{
		if (page == null)
		{
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.sigInTitle = page.locator("//h1[text() = 'Sign in']");
		this.emailField = page.locator("//input[@name='email']");
		this.passwordField = page.locator("//input[@name='password']");
		this.loginButton = page.locator("//button[text()='Login']");
	}

	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = sigInTitle.isVisible();
		if (isVisible)
		{
			LogHelper.debug("Sign in title is visible. User is at the correct page");		}
		else
		{
			LogHelper.warn("Sign in title is not visible. User might not be at the correct page");		}
		return isVisible;
	}

	public void signIn(String email, String password)
	{
		validateField(email, "Email");
		validateField(password, "Password");

		fillField(emailField, email);
		fillField(passwordField, password);
		clickOnElement(loginButton);
	}

}