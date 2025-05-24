package utils.managers;


import com.microsoft.playwright.Page;
import utils.LogHelper;
import utils.PlaywrightContext;
import utils.PlaywrightFactory;

public class BrowserManager {

	private static Page page;


	public static Page initializeBrowser(String baseUrl) {
		page = PlaywrightFactory.initBrowser();
		LogHelper.info("Navigating to {}", baseUrl);
		page.navigate(baseUrl);
		LogHelper.info("Navigated to {}", baseUrl);
		page.setDefaultTimeout(10000);
		return page;
	}


	public static void closeBrowser() {
		LogHelper.info("Closing browser");
		PlaywrightFactory.closeBrowser();
		LogHelper.info("Browser closed");
		PlaywrightContext.clear();
	}


	public static Page getPage() {
		return page;
	}
}
