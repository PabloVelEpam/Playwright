package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;


public class SignUpPage implements BaseTest {

	private Locator userField;
	private Locator emailField;
	private Locator passwordField;
	private Locator signUpBtn;
	private Locator sigUpTitle;
	private Page page;

	public SignUpPage(Page page) {
		this.page = page;
		this.sigUpTitle = page.locator("//h1[text() = 'Sign up']");
		this.userField = page.locator("//input[@name='username']");
		this.emailField = page.locator("//input[@name='email']");
		;
		this.passwordField = page.locator("//input[@name='password']");
		;
		this.signUpBtn = page.locator("//button[text()='Sign up']");
		;
	}

	@Override
	public boolean isAtCorrectPage() {
		page.waitForLoadState(LoadState.NETWORKIDLE);
		return sigUpTitle.isVisible();
	}

	public void signUp(String user, String email, String password) {
		userField.fill(user);
		emailField.fill(email);
		passwordField.fill(password);
		signUpBtn.click();
	}
}
