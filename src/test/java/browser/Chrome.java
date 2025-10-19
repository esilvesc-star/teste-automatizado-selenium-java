package browser;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Helpers;

public class Chrome {
	public static WebDriver webdriver;
	public static ChromeOptions opts;
    static Map<String, Object> prefs = new HashMap<String, Object>();
	
    public WebDriver getDriver() {
    	capabilites();
	        WebDriverManager wdm = WebDriverManager.chromedriver();
		    final String driverPath = "src/test/resources/driversManager/" + "chromedriver" + ".exe";
		    
		    wdm.cachePath(driverPath);
	    	wdm.setup();
		    System.setProperty("webdriver.chrome.driver", wdm.getDownloadedDriverPath());
	    try {
	        return new ChromeDriver(opts);
	    }
	    catch (Exception e){ 
	    	System.out.println("Erro no Driver");
	        System.out.println(e.toString());
	    }
			return webdriver = new ChromeDriver(opts);
	    }
    
    public static WebDriver capabilites(){
    	opts = new ChromeOptions();
    	opts.addArguments("start-maximized");
    	opts.addArguments("--remote-allow-origins=*");
    	opts.addArguments("incognito", "--ignore-ssl-errors=yes", "--ignore-certificate-errors");
    	opts.addArguments("--disable-search-engine-choice-screen");
    	opts.addArguments("--no-sandbox");
    	opts.addArguments("--disable-dev-shm-usage");
    	opts.addArguments("--verbose");
	    opts.addArguments("force-device-scale-factor=0.75");
	    opts.addArguments("high-dpi-support=0.75");
        opts.setExperimentalOption("prefs", prefs);
		return webdriver;
    }
    
    public static void maximize() {
    	webdriver.manage().window().maximize(); 
	}
    
    public static void close() throws Exception {
        if (webdriver != null) {
        	webdriver.close();
        	webdriver.quit();
        	webdriver = null;
        }
    }
}
