package utils;

import java.awt.*;

import com.microsoft.playwright.*;


public class PlaywrightFactory {
	private static Playwright playwright;
	private static Browser browser;
	private static BrowserContext browserContext;
	private static Page page;


	public static Page initBrowser() {

		if (page == null) {

			String browserType = System.getProperty("browser", "chromium").toLowerCase();

			playwright = Playwright.create();

			switch (browserType) {
				case "chromium":
					browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
					break;
				case "firefox":
					browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
					break;
				case "webkit":
					browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
					break;
				default:
					throw new IllegalArgumentException("No supported browser " + browserType);
			}

		}
		browserContext = browser.newContext();
		page = browserContext.newPage();
		return page;
	}

	public static void closeBrowser() {
		if (page != null) {
			page.close();
			page = null;
		}
		if (browserContext != null) {
			browserContext.close();
			browserContext = null;
		}
		if (browser != null) {
			browser.close();
			browser = null;
		}
		if (playwright != null) {
			playwright.close();
			playwright = null;
		}
	}
}