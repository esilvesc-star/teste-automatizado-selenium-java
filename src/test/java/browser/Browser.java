package browser;

import utils.Helpers;

import org.openqa.selenium.WebDriver;

public class Browser {
    public static WebDriver getWebDriver(){
        return getDriver(getBrowser());
    }

    private static IBrowser getBrowser(){
        String browser = Helpers.carregueUmUrquivoDaPropriedades("browser");

        if(browser.equalsIgnoreCase("chrome")) return new Chrome();
        else if(browser.equalsIgnoreCase("firefox")) return new Firefox();
        else if(browser.equalsIgnoreCase("edge")) return new Edge();
//        else if(browser.equalsIgnoreCase("ie")) return new InternetExplorer();
            //TODO
            //else if(browser.equalsIgnoreCase("opera")){...
        else throw new RuntimeException("Informe um browser válido");
    }

    private static WebDriver getDriver(IBrowser browser){
        return browser.getDriver();
    }
}
