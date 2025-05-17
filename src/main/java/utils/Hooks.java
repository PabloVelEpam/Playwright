package utils;

import org.testng.annotations.*;

import com.microsoft.playwright.Page;


public class Hooks {

	protected static Page page;

	@BeforeMethod
	public void setUp() {
		final String BaseUrl = "https://conduit-realworld-example-app.fly.dev/";
		page = PlaywrightFactory.initBrowser();
		LogHelper.info("Navigating to {}", BaseUrl);
		page.navigate(BaseUrl);
		LogHelper.info("Navigated to {}", BaseUrl);
		page.setDefaultTimeout(10000);
		PlaywrightContext.setPage(page);
	}


	@AfterMethod
	public void tearDown() {
		LogHelper.info("Closing browser");
		PlaywrightFactory.closeBrowser();
		LogHelper.info("Browser closed");
		PlaywrightContext.clear();
	}
}
