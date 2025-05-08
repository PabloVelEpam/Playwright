package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTest
{

	protected abstract boolean isAtCorrectPage();

	protected static void waitForVisibility(Locator locator)
	{
		try
		{
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			log.debug("Locator is visible: {}", locator.toString());
		}
		catch (Exception e)
		{
			log.error("Timed out waiting for locator to become visible: {}", locator.toString());
			throw e;
		}
	}

}