package utils;

import org.testng.annotations.*;

import com.microsoft.playwright.Page;


public class Hooks {

	protected static Page page;

	@BeforeMethod
	public void setUp() {
		page = PlaywrightFactory.initBrowser();
		page.navigate("https://conduit-realworld-example-app.fly.dev/");
		page.setDefaultTimeout(10000);

		PlaywrightContext.setPage(page);
	}


	@AfterMethod
	public void tearDown() {
		PlaywrightFactory.closeBrowser();
		PlaywrightContext.clear();
	}
}
