package ui.pom.utils;

import org.testng.annotations.*;

import com.microsoft.playwright.Page;

import java.io.IOException;

public class Hooks {

	public static Page page;
	PropertiesManager config;
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException
	{
		LogHelper.info("Before method");
		String baseUrl = PropertiesManager.getUrl();
		page = PlaywrightFactory.initBrowser();
		LogHelper.info("Navigating to {}", baseUrl);
		page.navigate(baseUrl);
		LogHelper.info("Navigated to {}", baseUrl);
		page.setDefaultTimeout(10000);
		PlaywrightContext.setPage(page);
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		LogHelper.info("Closing browser");
		PlaywrightFactory.closeBrowser();
		LogHelper.info("Browser closed");
		PlaywrightContext.clear();
	}
}
