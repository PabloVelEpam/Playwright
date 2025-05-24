package utils.managers;

import com.epam.reportportal.service.ReportPortal;
import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import utils.LogHelper;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class ScreenshotManager
{
	public static void captureAndAttachScreenshot(Scenario scenario, Page page) {
		if (page != null) {
			try {
				// Ruta para guardar la captura de pantalla
				String screenshotPath = "screenshots/" + scenario.getName().replaceAll(" ", "_") + ".png";
				page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
				LogHelper.info("Screenshot saved at: {}", screenshotPath);

				// Adjuntar al reporte de Cucumber
				File screenshotFile = new File(screenshotPath);
				scenario.attach(Files.readAllBytes(Paths.get(screenshotFile.getAbsolutePath())), "image/png", "Failure Screenshot");

				// Subir la captura a ReportPortal
				uploadScreenshotToReportPortal(screenshotFile, screenshotPath);
			} catch (Exception e) {
				LogHelper.error("Failed to capture and attach screenshot: {}", e.getMessage(), e);
			}
		} else {
			LogHelper.warn("Page object is null, unable to capture screenshot");
		}
	}

	/**
	 * Subir una captura de pantalla a ReportPortal.
	 */
	private static void uploadScreenshotToReportPortal(File screenshotFile, String screenshotPath) {
		try {
			ReportPortal.emitLog("Screenshot attached for failure", "ERROR", new Date(), screenshotFile);
			LogHelper.info("Screenshot successfully uploaded to ReportPortal: {}", screenshotPath);
		} catch (Exception e) {
			LogHelper.error("Error uploading screenshot to ReportPortal: {}", e.getMessage(), e);
		}
	}

}
