package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;


public class LoginPage implements BaseTest {

	private Locator emailField;
	private Locator passwordField;
	private Locator loginButton;
	private Locator sigInTitle;
	private Page page;

	public LoginPage(Page page) {
		this.page = page;
		this.sigInTitle = page.locator("//h1[text() = 'Sign in']");
		this.emailField = page.locator("//input[@name='email']");
		this.passwordField = page.locator("//input[@name='password']");
		this.loginButton = page.locator("//button[text()='Login']");
	}

	@Override
	public boolean isAtCorrectPage() {
		page.waitForLoadState(LoadState.NETWORKIDLE);
		return sigInTitle.isVisible();
	}

	public void signIn(String user, String email, String password) {
		emailField.fill(email);
		passwordField.fill(password);
		loginButton.click();
	}
}
