package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;


public class WelcomePage implements BaseTest {
	private Locator yourFeedButton;
	private Page page;

	public WelcomePage(Page page) {
		this.page = page;
		this.yourFeedButton = page.locator("//button[text()='Your Feed']");
	}

	@Override
	public boolean isAtCorrectPage() {
		yourFeedButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return yourFeedButton.isVisible();
	}
}
