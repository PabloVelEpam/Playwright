package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;

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
			log.error("Page object cannot be null");
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
		page.waitForLoadState(LoadState.NETWORKIDLE);
		boolean isVisible = sigUpTitle.isVisible();
		if (isVisible)
		{
			log.debug("Sign up title is visible. User is at the correct page");
		}
		else
		{
			log.warn("Sign up title is not visible. User might not be at the correct page");
		}
		return isVisible;
	}

	public void signUp(String user, String email, String password)
	{
		if (user == null || user.isEmpty())
		{
			log.error("Username cannot be null or empty");
			throw new IllegalArgumentException("Username cannot be null or empty");
		}
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
		userField.fill(user);
		log.debug("User field filled with -> {}", user);
		emailField.fill(email);
		log.debug("Email field filled with -> {}", email);
		passwordField.fill(password);
		log.debug("Password field filled with -> {}", password);
		signUpBtn.click();
		log.debug("Clicked on sign up button");
	}

}