package runnerpackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/features" }, 
				glue = {"stepdefinition","orderapphooks" }, 
				monochrome = true, dryRun = false, 
				tags="@EndtoEnd",
				plugin = { "pretty",
							"html:target/runTimeReports/htmlformat.html" ,
							"json:target/cucumber.json"})

// "html:target/html/sequentialtestresulthtmlformat.html" ,

// Sequential execution of feature files , with each feature file having
// multiple scenarios
public class SequentialExecution {

}
