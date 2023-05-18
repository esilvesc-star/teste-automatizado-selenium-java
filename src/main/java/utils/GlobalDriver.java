package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GlobalDriver {
	public static WebDriver webdriver;
	public static ChromeOptions opts;
	Helpers helpers;
	
    public static WebDriver get() throws Exception {
    	capabilites();	
    	System.setProperty(Helpers.carregueUmUrquivoDaPropriedades("webdriver.chrome.driver"),
    			Helpers.carregueUmUrquivoDaPropriedades("path.webdriver.chrome"));
		return webdriver = new ChromeDriver(opts);
    }
    
    public static WebDriver capabilites() throws Exception {
    	opts = new ChromeOptions();
	    opts.addArguments("incognito", "--ignore-ssl-errors=yes", "--ignore-certificate-errors");
	    opts.addArguments("force-device-scale-factor=1.25");
	    opts.addArguments("high-dpi-support=1.25");
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
