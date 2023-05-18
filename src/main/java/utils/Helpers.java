package utils;

import java.io.*;
import java.util.Collection;
import java.util.Properties;

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

   public static String carregueUmUrquivoDaPropriedades(String path) throws IOException {
	   String resposta = null;
	   try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
	         Properties prop = new Properties();
	
	        // load a properties file
	        prop.load(input);
	        resposta = prop.getProperty(path);
	        // get the property value and print it out
	        System.out.println(resposta);
	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return resposta;
    }
}
