package cucumberOptions;

import org.testng.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/featureFiles",plugin="json:target/jsonReports/cucumber-report.json",glue = {"stepDefinitions"})
public class TestRunner extends AbstractTestNGCucumberTests {
	
	

}
