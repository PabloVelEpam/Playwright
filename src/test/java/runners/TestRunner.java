package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = "stepdefinitions",
		monochrome = true,
		plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html", // Reporte HTML
		"json:target/cucumber-reports/cucumber.json", // Reporte JSON
})
public class TestRunner extends AbstractTestNGCucumberTests
{

}
