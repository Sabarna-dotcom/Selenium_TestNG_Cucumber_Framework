package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "stepDefination", monochrome=true, tags = "@Regression", plugin = {"html:reports/cucumber.html", })
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
