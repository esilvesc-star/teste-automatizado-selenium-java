package browser;

import org.openqa.selenium.WebDriver;

import utils.Helpers;

public interface IBrowser {
    default String getDriverPathByOS(String driverName){
    	String driverPath = null; 
        String osName = "windows";
        String driverManager = Helpers.carregueUmUrquivoDaPropriedades("driverManager");
        if(driverManager.equals("true")) {
        	driverPath = Helpers.carregueUmUrquivoDaPropriedades("driverPathManager");
        }else {
        	driverPath = Helpers.carregueUmUrquivoDaPropriedades("driverPath");
        }
        
        if(osName.contains("windows")) {
            return driverPath + driverName + ".exe";
        }
        else return driverPath + driverName; //Linux
        //TODO
        //else if(osName.contains("macos")){...
    }

    WebDriver getDriver();
}
