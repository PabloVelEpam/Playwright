package ui.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.extern.slf4j.Slf4j;
import ui.pom.utils.LogHelper;

@Slf4j
public class BaseUiTest
{

	protected static boolean waitForVisibility(Locator locator)
	{
		try
		{
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			log.debug("Locator is visible: {}", locator);
			return true;
		}
		catch (Exception e)
		{
			log.error("Timed out waiting for locator to become visible: {}", locator.toString());
			return false;
		}
	}

	public static void waitForNetworkIdle(Page page)
	{
		try
		{
			LogHelper.debug("Waiting for page to reach NETWORKIDLE state...");
			page.waitForLoadState(LoadState.NETWORKIDLE);
			LogHelper.debug("Page successfully reached NETWORKIDLE state");
		}
		catch (Exception e)
		{
			LogHelper.error("Error while waiting for NETWORKIDLE state: {}", e.getMessage());
		}
	}

	protected void clickOnElement(Locator locator)
	{
		try
		{
			locator.click();
			LogHelper.debug("Clicked on {}", locator);
		}
		catch (Exception exception)
		{
			LogHelper.error("Cannot click on {}", exception, locator);
		}
	}

	protected void fillField(Locator locator, String keys)
	{
		try
		{
			locator.fill(keys);
			LogHelper.debug("Filled {} with {}", locator, keys);
		}
		catch (Exception exception)
		{
			LogHelper.error("Cannot fill {} with {}", exception, locator, keys);
		}
	}

	protected String getElementText(Locator locator)
	{
		try
		{
			waitForVisibility(locator);
			String elementText = locator.innerText();
			LogHelper.debug("Text gotten {} from locator {}", elementText, locator);
			return elementText;
		}
		catch (Exception exception)
		{
			LogHelper.error("Cannot get text from locator {} with error message: {}", locator, exception);
		}
		return "";
	}

	protected static void validateField(String value, String fieldName)
	{
		if (value == null || value.isEmpty())
		{
			String errorMessage = fieldName + " cannot be null or empty";
			LogHelper.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

}