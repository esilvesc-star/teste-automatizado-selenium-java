package steps;

import static java.util.Objects.isNull;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import browser.Chrome;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utils.AllureGenerator;
import utils.GlobalDriver;
import utils.Helpers;
import utils.Reports;

public class Hooks{
    public static WebDriver driver;
    static String comAllure = Helpers.carregueUmUrquivoDaPropriedades("comAllure");
    static ArrayList<String> stringListaParaPrintAposTodosCenarios = new ArrayList<>();
    static String pathResults = "./target/allure-results/";
    static String pathReport = "src/test/resources/test-results/";
    private static Reports reports;


	@AfterStep
	public void afterStep(Scenario scenario) throws Exception{
      if(!isNull(GlobalDriver.get())){
//      	  Reports.takeScreenShotPasta();
    	  reports.captureEvidence(driver, scenario.getName());
      }
	}
	
	@BeforeStep
	public void beforeStep(){

	}
	
	@Before
    public static void setup(Scenario scenario) {
		reports = new Reports(driver, comAllure);

        GlobalDriver.set();
        System.out.println("\n\n\nTESTE CENARIO " + scenario.getSourceTagNames().toString() + " \n\n\n\n");
        driver = GlobalDriver.get();
	}

	@After
	public void finish(Scenario scenario) throws Exception {
//        Chrome.close();
    	List<String> tagNames = (List<String>) scenario.getSourceTagNames();
//        String tag = scenario.getSourceTagNames().toString().substring(3, 19).replaceAll("[^ a-zA-Z0-9]+","");
//        System.out.println(tag);
//	    String screenshotName = scenario.getName().replaceAll("","");

        reports.generatePdfReport();

        
        if (scenario.getStatus().name().contains("FAILED")) {
            stringListaParaPrintAposTodosCenarios.add(String.join(" ", tagNames));
        }

	      GlobalDriver.close();
    }    
	
    @BeforeAll
    public static void setupAll() {
    	AllureGenerator.deletarArquivos(new File(pathResults));
    	AllureGenerator.deletarArquivos(new File(pathReport));
    }
  
	@AfterAll
    public static void finishAll() throws IOException, InterruptedException {
		if(comAllure.equals("true")){
	    	String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
	    	String novoNomeArquivoAllure = time + "-index.html";
	    	AllureGenerator.gerarAllureReportHTML(pathResults,pathReport);
		   	AllureGenerator.RenomearArquivo(pathReport,novoNomeArquivoAllure);
    	}
		System.out.println("**** lista FAILED apos todos cenarios ****");
		System.out.println(stringListaParaPrintAposTodosCenarios);
	   	GlobalDriver.close();
    }
		
	public static void openBrowser(String url) {
        if(!isNull(driver)){
//        	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        	driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
            driver.navigate().to(url);
        }
        else {
            throw new RuntimeException("Driver não criado");
        }
    }
}