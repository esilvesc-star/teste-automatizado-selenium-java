package utils;

//import browser.Browser;
import org.openqa.selenium.WebDriver;

import browser.Browser;

import static java.util.Objects.isNull;


public class GlobalDriver {

    private static WebDriver driver = null;

    public static void set() {
        if (driver == null) {
            driver = Browser.getWebDriver();
//        	driver = Chrome.getDriver();
        }
    }

    public static WebDriver get() {
        return driver;
    }

    public static void close() {
        if(!isNull(driver)){
//            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
