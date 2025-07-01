package ui.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ui.pom.utils.LogHelper;

public class UserProfilePage extends BaseUiTest implements BasePage
{

	private Page page;
	private Locator articleTitle;

	public UserProfilePage(Page page)
	{
		if (page == null)
		{
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.articleTitle = page.locator("//h4");
	}

	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = waitForVisibility(articleTitle);
		if (isVisible)
		{
			LogHelper.debug("User profile page title is visible and is correct. User is at the correct page");
		}
		else
		{
			LogHelper.warn("User profile page title is not visible or is not correct. User might not be at the correct page");
		}
		return isVisible;
	}

	public boolean isCorrectUserName(String expectedUserNAme)
	{
		return getElementText(articleTitle).equals(expectedUserNAme);
	}

}
