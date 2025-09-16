package utils;

import org.testng.annotations.*;

import com.microsoft.playwright.Page;

import java.io.IOException;

public class Hooks {

	protected static Page page;
	PropertiesManager config;
	@BeforeMethod
	public void setUp() throws IOException
	{
		config = new PropertiesManager("src/main/resources/application.properties");

		String baseUrl = config.getProperty("url");
		page = PlaywrightFactory.initBrowser();
		LogHelper.info("Navigating to {}", baseUrl);
		page.navigate(baseUrl);
		LogHelper.info("Navigated to {}", baseUrl);
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
