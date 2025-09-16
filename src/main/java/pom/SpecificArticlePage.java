package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.LogHelper;

public class SpecificArticlePage extends BaseTest  implements BasePage
{
	private Page page;
	private Locator articleTitle;
	public SpecificArticlePage(Page page){
		if (page == null) {
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.articleTitle = page.locator("//h1");
	}
	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = waitForVisibility(articleTitle);
		if (isVisible) {
			LogHelper.debug("Article title is visible. User is at the correct page");
		} else {
			LogHelper.warn("Article title is not visible. User might not be at the correct page");
		}
		return isVisible;
	}
}
