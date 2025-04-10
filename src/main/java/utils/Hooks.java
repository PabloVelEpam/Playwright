package utils;

import org.testng.annotations.*;

import com.microsoft.playwright.Page;


public class Hooks {

	protected Page page;

	@BeforeMethod
	public void setUp() {
		page = PlaywrightFactory.initBrowser();
		page.navigate("https://conduit-realworld-example-app.fly.dev/");
		page.setDefaultTimeout(10000);
	}


	@AfterMethod
	public void tearDown() {
		PlaywrightFactory.closeBrowser();
	}
}
