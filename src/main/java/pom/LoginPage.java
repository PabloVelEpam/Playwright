package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPage extends BaseTest
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
			log.error("Page object cannot be null");
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
		page.waitForLoadState(LoadState.NETWORKIDLE);
		boolean isVisible = sigInTitle.isVisible();
		if (isVisible)
		{
			log.debug("Sign in title is visible. User is at the correct page");
		}
		else
		{
			log.warn("Sign in title is not visible. User might not be at the correct page");
		}
		return isVisible;
	}

	public void signIn(String email, String password)
	{
		if (email == null || email.isEmpty())
		{
			log.error("Email cannot be null or empty");
			throw new IllegalArgumentException("Email cannot be null or empty");
		}
		if (password == null || password.isEmpty())
		{
			log.error("Password cannot be null or empty");
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		emailField.fill(email);
		log.debug("Email field filled with -> {}", email);
		passwordField.fill(password);
		log.debug("Password field filled with -> {}", password);
		loginButton.click();
		log.debug("Clicked on login button");
	}

}