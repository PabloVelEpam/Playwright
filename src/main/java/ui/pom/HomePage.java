package ui.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import ui.pom.utils.LogHelper;

@Slf4j
public class HomePage extends BaseUiTest implements BasePage
{

	private Locator signUpLink;
	private Locator conduitTitle;
	private Locator loginLink;
	private Page page;

	public HomePage(Page page)
	{
		if (page == null)
		{
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.signUpLink = page.locator("//a[(text())='Sign up']");
		this.conduitTitle = page.locator("//h1[text() = 'conduit']");
		this.loginLink = page.locator("//a[(text())='Login']");
		LogHelper.debug("HomePage locators initialized successfully");
	}

	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = conduitTitle.isVisible();
		if (isVisible)
		{
			LogHelper.debug("Conduit title is visible. User is at the Home Page");
		}
		else
		{
			LogHelper.warn("Conduit title is not visible. User might not be at the Home Page");		}
		return isVisible;
	}

	public void clickSignUp()
	{
		clickOnElement(signUpLink);
	}

	public void clickLogin()
	{
		clickOnElement(loginLink);
	}

}