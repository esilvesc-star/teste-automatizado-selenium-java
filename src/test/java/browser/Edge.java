package browser;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import questions.CommonQuestions;
import utils.Helpers;

public class Edge implements IBrowser {
	public static WebDriver webdriver;
	static EdgeOptions options;
    static Map<String, Object> prefs = new HashMap<String, Object>();
//    static String path = CommonQuestions.CriarPasta(Variaveis.get().asString("pathPastaDownload"));
    static String headless = Helpers.carregueUmUrquivoDaPropriedades("headless");
    String driverManager = Helpers.carregueUmUrquivoDaPropriedades("driverManager");

    @Override
    public WebDriver getDriver() {
    	if(driverManager.equals("true")) {
	        WebDriverManager wdm = WebDriverManager.edgedriver();
		    final String driverPath = getDriverPathByOS("msedgedriver");
		    wdm.cachePath(driverPath);
	    	wdm.setup();
		    System.setProperty("webdriver.edge.driver", wdm.getDownloadedDriverPath());
    	}else {
    		final String driverPath = getDriverPathByOS("msedgedriver");
    		System.setProperty("webdriver.edge.driver", driverPath);
    	}
				
    	if (headless.equals("true")) {
	    	capabilitesEdgeHeadless();
	    }else {
	    	capabilitesEdge();
	    }
	return new EdgeDriver(options);
    }
    
    public static WebDriver capabilitesEdge() {
    	options = new EdgeOptions();
	    options.addArguments("start-maximized");
	    options.addArguments("force-device-scale-factor=1.25");
	    options.addArguments("high-dpi-support=1.25");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	    options.addArguments("--InPrivate");
	    options.addArguments("--remote-allow-origins=*");
//	    prefs.put("download.default_directory", path);
	    options.setExperimentalOption("prefs", prefs);
    	return webdriver;
    }
    
    public static WebDriver capabilitesEdgeHeadless() {
    	options = new EdgeOptions();
    	options.addArguments("--headless=old");
	    options.addArguments("start-maximized");
	    options.addArguments("force-device-scale-factor=0.70");
	    options.addArguments("high-dpi-support=0.70");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	    options.addArguments("--InPrivate");
	    options.addArguments("--remote-allow-origins=*");
//	    prefs.put("download.default_directory", path);
	    options.setExperimentalOption("prefs", prefs);
    	return webdriver;
    }
}
