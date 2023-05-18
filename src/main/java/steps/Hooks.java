package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.GlobalDriver;

public class Hooks extends GlobalDriver{

	@Before
	public void beforeEveryScenario() throws Exception {
		GlobalDriver.get();
		GlobalDriver.maximize();
	}

	@After
	public void afterEveryScenario() throws Exception {
		GlobalDriver.close();
	}
}