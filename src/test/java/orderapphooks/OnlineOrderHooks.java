package orderapphooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import wrapperpackage.WrapperClass;

public class OnlineOrderHooks extends WrapperClass{
	
	//@Before
	public void beforeStartExecution() {
		System.out.println("Execution started!!!!!");
		startApp(prop.getProperty("browser"));
		type(locateElement("id", "email"), userName);
		type(locateElement("id", "passwd"), password);
		click(locateElement("id", "SubmitLogin"));
	}

	//@After
	public void afterExecutionCompleted() {
		System.out.println("Execution Completed.......");
		browserClose("//a[@title='Log me out']");

	}

}
