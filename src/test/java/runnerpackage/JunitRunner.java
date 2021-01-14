package runnerpackage;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features"},
				 glue={"stepdefinition"},
				 monochrome=true,
				 dryRun=false,
				 strict=true
				 )

public class JunitRunner {

}
