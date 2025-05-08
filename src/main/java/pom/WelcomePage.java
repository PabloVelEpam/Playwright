package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WelcomePage extends BaseTest {
	private Locator yourFeedButton;
	private Page page;

	public WelcomePage(Page page) {
		if (page == null) {
			log.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.yourFeedButton = page.locator("//button[text()='Your Feed']");
		log.debug("Your Feed button locator initialized");
	}

	@Override
	public boolean isAtCorrectPage() {
		page.waitForLoadState(LoadState.NETWORKIDLE);
		waitForVisibility(yourFeedButton);
		boolean isVisible = yourFeedButton.isVisible();
		if (isVisible) {
			log.debug("Your Feed button is visible. User is at the correct page");
		} else {
			log.warn("Your Feed button is not visible. User might not be at the correct page");
		}
		return isVisible;
	}
}