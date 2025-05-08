package listener;

import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.reportportal.utils.MimeTypeDetector;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Listeners(ReportPortalTestNGListener.class)
public class CustomListener implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result)
	{
		log.info("Test started: {}", result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		log.info("Test passed: {}", result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		log.error("Test failed: {}", result.getName());

		try
		{
			Page page = utils.PlaywrightContext.getPage();
			if (page != null)
			{
				String screenshotPath = "screenshots/" + result.getName() + ".png";
				page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
				log.info("Screenshot captured for failed test: {}", screenshotPath);

				attachScreenshotToReportPortal(screenshotPath);
			}
			else
			{
				log.warn("Page object is null, unable to capture screenshot for failed test: {}", result.getName());
			}
		}
		catch (Exception e)
		{
			log.error("Error while capturing or uploading screenshot for test failure: {}", e.getMessage(), e);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		log.warn("Test skipped: {}", result.getName());
	}

	private void attachScreenshotToReportPortal(String screenshotPath)
	{
		File screenshotFile = new File(screenshotPath);

		try
		{
			// Subir el screenshot usando emitLog
			ReportPortal.emitLog("Screenshot attached for failure", "ERROR", new Date(), screenshotFile);

			log.info("Screenshot successfully uploaded to ReportPortal: {}", screenshotPath);
		}
		catch (Exception e)
		{
			log.error("Error uploading screenshot to ReportPortal: {}", e.getMessage(), e);
		}
	}

}

