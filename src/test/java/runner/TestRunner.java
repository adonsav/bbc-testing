package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/suitesBDD/regressionSuiteBDD",
        glue = "suitesStepDefs/regressionSuiteStepDefs")
public class TestRunner extends AbstractTestNGCucumberTests {


}
