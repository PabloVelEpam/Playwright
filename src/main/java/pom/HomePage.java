package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;


public class HomePage implements BaseTest {
	private Locator signUpLink;
	private Locator conduitTitle;
	private Locator loginLink;
	private Page page;

	public HomePage(Page page) {
		this.page = page;
		this.signUpLink = page.locator("//a[(text())='Sign up']");
		this.conduitTitle = page.locator("//h1[text() = 'conduit']");
		this.loginLink = page.locator("//a[(text())='Login']");
	}

	@Override
	public boolean isAtCorrectPage() {
		page.waitForLoadState(LoadState.NETWORKIDLE);
		return conduitTitle.isVisible();
	}

	public void clickSignUp() {
		signUpLink.click();
	}

	public void clickLogin() {
		loginLink.click();
	}
}
