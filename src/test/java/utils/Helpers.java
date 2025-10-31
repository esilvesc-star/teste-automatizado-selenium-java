package utils;

import java.io.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static steps.Hooks.driver;

public class Helpers {

    // Serve para buscar valores configurados em um arquivo de propriedades (application.properties)
    private static final String PROPERTIES_PATH = "src/test/resources/application.properties";

    public static String getProperty(String key) {
        try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de propriedades: " + key, e);
        }
    }

    public static String getBaseUrl() {
        return getProperty("urlBase");
    }


    // Metodo para esperar o campo ficar visivel
    public static WebElement esperarVisibilidade(WebDriver driver, WebElement elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(elemento));
    }

    // Metodo para o botao ficar clicavel
    public static WebElement esperarClicavel(WebDriver driver, WebElement elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    // Metodo para digitar lento
    public static void digitarLento(WebElement elemento, String texto) throws InterruptedException {
        for (char c : texto.toCharArray()) {
            elemento.sendKeys(String.valueOf(c));
            Thread.sleep(100); // espera 100 ms entre teclas
        }
    }

    public static String carregueUmArquivoDaPropriedades(String path) {
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

}
