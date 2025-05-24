package utils;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.managers.BrowserManager;
import utils.managers.ScreenshotManager;

public class CucumberHooks
{
	private static final String BASE_URL = "https://conduit-realworld-example-app.fly.dev/";
	private Page page;

	@Before
	public void setUp() {
		this.page = BrowserManager.initializeBrowser(BASE_URL);
		PlaywrightContext.setPage(this.page); // Establecer el contexto
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			ScreenshotManager.captureAndAttachScreenshot(scenario, page);
		}
		BrowserManager.closeBrowser();
	}

}