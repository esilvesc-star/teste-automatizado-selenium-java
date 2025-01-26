package browser;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Helpers;

public class Chrome implements IBrowser{
	public static WebDriver webdriver;
	public static ChromeOptions opts;
    static Map<String, Object> prefs = new HashMap<String, Object>();
    static String headless = Helpers.carregueUmUrquivoDaPropriedades("headless");
    String driverManager = Helpers.carregueUmUrquivoDaPropriedades("driverManager");
	
    @Override
    public WebDriver getDriver() {
    	if (headless.equals("false")) {
	        capabilites();
        }
        else if (headless.equals("true")) {
        	capabilitesChromeHeadless();
        }
    	
    	if(driverManager.equals("true")) {
	        WebDriverManager wdm = WebDriverManager.chromedriver();
		    final String driverPath = getDriverPathByOS("chromedriver");
		    wdm.cachePath(driverPath);
	    	wdm.setup();
		    System.setProperty("webdriver.chrome.driver", wdm.getDownloadedDriverPath());
    	}else {
	        final String driverPath = getDriverPathByOS("chromedriver");
	        System.setProperty("webdriver.chrome.driver", driverPath);
    	}
    	
    try {
        return new ChromeDriver(opts);
    }
    catch (Exception e){ 
    	System.out.println("Erro no Driver");
        System.out.println(e.toString());
    }
    
//    	System.setProperty(Helpers.carregueUmUrquivoDaPropriedades("webdriver.chrome.driver"),
//    			Helpers.carregueUmUrquivoDaPropriedades("path.webdriver.chrome"));
//    	
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
	    opts.addArguments("force-device-scale-factor=1.25");
	    opts.addArguments("high-dpi-support=1.25");
        opts.setExperimentalOption("prefs", prefs);
		return webdriver;
    }
    
    public static WebDriver capabilitesChromeHeadless() {
    	opts = new ChromeOptions();
    	opts.addArguments("--remote-allow-origins=*");
    	opts.addArguments("--headless=old");
    	opts.addArguments("--disable-gpu"); // Desativa a aceleração de GPU
    	opts.addArguments("--no-sandbox"); // Evita problemas em ambientes restritos
    	opts.addArguments("--disable-dev-shm-usage"); // Soluciona problemas de recursos
    	opts.addArguments("--window-size=1920x1080"); // Define um tamanho de janela
    	opts.addArguments("--remote-debugging-port=9222"); // Ativa a depuração remota
    	opts.addArguments("--start-maximized"); // Tenta maximizar a janela no início
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
