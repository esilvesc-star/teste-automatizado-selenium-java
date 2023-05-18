package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import utils.GlobalDriver;
import utils.Reports;

public class Hooks extends GlobalDriver{
	
	@AfterStep
	public void afterStep(){
		
	}
	
	@BeforeStep
	public void beforeStep(){

	}
	
	@Before
	public void beforeEveryScenario() throws Exception {
		Reports.takeScreenShotPasta();
		GlobalDriver.get();
		GlobalDriver.maximize();
	}

	@After
	public void afterEveryScenario() throws Exception {
        GlobalDriver.close();
    }	
		
}