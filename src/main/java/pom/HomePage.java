package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePage extends BaseTest
{

	private Locator signUpLink;
	private Locator conduitTitle;
	private Locator loginLink;
	private Page page;

	public HomePage(Page page)
	{
		if (page == null)
		{
			log.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.signUpLink = page.locator("//a[(text())='Sign up']");
		this.conduitTitle = page.locator("//h1[text() = 'conduit']");
		this.loginLink = page.locator("//a[(text())='Login']");
		log.debug("HomePage locators initialized successfully");
	}

	@Override
	public boolean isAtCorrectPage()
	{
		conduitTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		boolean isVisible = conduitTitle.isVisible();
		if (isVisible)
		{
			log.debug("Conduit title is visible. User is at the Home Page");
		}
		else
		{
			log.warn("Conduit title is not visible. User might not be at the Home Page");
		}
		return isVisible;
	}

	public void clickSignUp()
	{
		signUpLink.click();
		log.debug("Clicked on sign-up link");
	}

	public void clickLogin()
	{
		loginLink.click();
		log.debug("Clicked on login link");
	}

}