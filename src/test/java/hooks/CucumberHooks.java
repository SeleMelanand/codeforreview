package hooks;

import org.junit.Before;

import cucumber.api.java.After;
import wrapperpackage.WrapperClass;

public class CucumberHooks extends WrapperClass {

	@Before
	public void begin() {
		startApp("chrome");
	}

	@After
	public void endtestrun() {
		closeAllBrowsers();
	}

}
