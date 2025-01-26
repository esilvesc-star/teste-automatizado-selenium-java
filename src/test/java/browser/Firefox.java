package browser;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.CapabilityType;
import utils.Helpers;

public class Firefox implements IBrowser {

       	public static WebDriver webdriver;
    	static FirefoxOptions options;
        static Map<String, Object> prefs = new HashMap<String, Object>();
//        static String path = CommonQuestions.CriarPasta(Variaveis.get().asString("pathPastaDownload"));
        static String headless = Helpers.carregueUmUrquivoDaPropriedades("headless");
        String driverManager = Helpers.carregueUmUrquivoDaPropriedades("driverManager");

        @Override
        public WebDriver getDriver() {
//        	if(driverManager.equals("true")) {
//    	        WebDriverManager wdm = WebDriverManager.edgedriver();
//    		    final String driverPath = getDriverPathByOS("msedgedriver");
//    		    wdm.cachePath(driverPath);
//    	    	wdm.setup();
//    		    System.setProperty("webdriver.edge.driver", wdm.getDownloadedDriverPath());
//        	}else {
        	
    		
        	if (headless.equals("true")) {
        		capabilitesFirefoxHeadless();
    	    }else {
    	    	capabilitesFirefox();
    	    }
        	
    		final String driverPath = getDriverPathByOS("geckodriver");
    		System.setProperty("webdriver.firefox.driver", driverPath);
//        	}

        	
    	return new FirefoxDriver(options);
        }
        
        public static WebDriver capabilitesFirefox() {
        	options = new FirefoxOptions();
        	options.addArguments("-private", "--web-security=no","--ssl-protocol=any", "--ignore-ssl-errors=yes");
        	options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
    	//    profile.setPreference("browser.download.folderList", 2);
    	//    profile.setPreference("browser.download.dir", path);
    	    return new FirefoxDriver(options);
        
        }
        
        public static WebDriver capabilitesFirefoxHeadless() {
        	options = new FirefoxOptions();
        	options.addArguments("--headless=old");
        	options.addArguments("start-maximized");
        	options.addArguments("force-device-scale-factor=0.70");
        	options.addArguments("high-dpi-support=0.70");
        	options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        	options.addArguments("--InPrivate");
        	options.addArguments("--remote-allow-origins=*");
//    	    prefs.put("download.default_directory", path);
//        	options.setExperimentalOption("prefs", prefs);
        	return webdriver;
        }
    }
