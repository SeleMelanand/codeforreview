package runnerpackage;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/features" }, 
				 glue = {"stepdefinition", "orderapphooks" }, 
				 monochrome = true, 
				 dryRun = true, 
				 plugin = { "pretty",
						 	"html:target/parallelresults.html" })

/*
 * Parallel Execution is done using TestNg for a feature file having 2
 * Scenarios. both the scenarios in a feature file will run in 2 threads.
 */

//Note: To run in parallel on scenarios levele make sure in wrapper class driver variable should not be static

public class ParallelExecutionTestNgScenarioLevel extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
