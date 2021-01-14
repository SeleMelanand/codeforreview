package runnerpackage;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features"},
				 glue={"stepdefinition","hooks"},
				 monochrome=true,
				 dryRun=true,
				 strict=true
				 )

public class JunitRunner {

}
