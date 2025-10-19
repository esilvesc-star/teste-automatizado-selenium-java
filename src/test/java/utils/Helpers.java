package utils;

import java.io.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helpers {

    public static String caminho;

	public static Collection<Object> existentPropertiesValuesList() throws IOException {
        String filePath = "src/test/resources/application.properties";
        FileInputStream in = new FileInputStream(filePath);
        Properties props = new Properties();
        props.load(in);
        Collection<Object> valuesList = props.values();
        System.out.println(valuesList);
        return valuesList;
    }

   public static String carregueUmUrquivoDaPropriedades(String path){
	   String resposta = null;
	   try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
	         Properties prop = new Properties();
	
	        // load a properties file
	        prop.load(input);
	        resposta = prop.getProperty(path);
	        // get the property value and print it out
	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return resposta;
    }
   
   public static void DeletarArquivo(File file) {
	  if (file.exists()) {
		for (File subfile : file.listFiles()) {
			if (subfile.isDirectory()) {
				DeletarArquivo(subfile);
		    }
            subfile.delete();
        }
	  }
   }
	public void scroll(WebDriver driver, WebElement elemento) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elemento);
	}
}
