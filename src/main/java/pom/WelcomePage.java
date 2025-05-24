package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import utils.LogHelper;

@Slf4j
public class WelcomePage extends BaseTest {
	private Locator yourFeedButton;
	private Page page;

	public WelcomePage(Page page) {
		if (page == null) {
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.yourFeedButton = page.locator("//button[text()='Your Feed']");
		LogHelper.debug("Your Feed button locator initialized");
	}

	@Override
	public boolean isAtCorrectPage() {
		waitForNetworkIdle(page);

		waitForVisibility(yourFeedButton);
		boolean isVisible = yourFeedButton.isVisible();
		if (isVisible) {
			LogHelper.debug("Your Feed button is visible. User is at the correct page");
		} else {
			LogHelper.warn("Your Feed button is not visible. User might not be at the correct page");
		}
		return isVisible;
	}
}