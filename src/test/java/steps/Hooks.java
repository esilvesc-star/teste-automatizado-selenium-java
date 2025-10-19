package steps;

import static java.util.Objects.isNull;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

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
    // Instância principal do WebDriver (compartilhada entre os testes)
    public static WebDriver driver;
    // Lê do arquivo de propriedades se o Allure deve ser utilizado ("true" ou "false")
    static String comAllure = Helpers.carregueUmUrquivoDaPropriedades("comAllure");
    // Lista que armazena as tags dos cenários que falharam
    static ArrayList<String> stringListaParaPrintAposTodosCenarios = new ArrayList<>();
    // Caminhos para os diretórios de relatórios
    static String pathResults = "./target/allure-results/";
    static String pathReport = "src/test/resources/test-results/";
    // Instância do gerador de relatórios
    private static Reports reports;

    // -----------------------------------
    // Executa APÓS cada passo
    // -----------------------------------
	@AfterStep
	public void afterStep(Scenario scenario) throws Exception{
        // Se o driver estiver ativo, captura uma evidência (screenshot)
      if(!isNull(GlobalDriver.get())){
    	  reports.captureEvidence(driver, scenario.getName());
      }
	}
    // -----------------------------------
    // Executa ANTES de cada passo (Given/When/Then)
    // -----------------------------------
	@BeforeStep
	public void beforeStep(){
        // Metodo vazio — pode ser usado futuramente para logs ou preparação
	}

    // -----------------------------------
    // Executa ANTES de cada cenário
    // -----------------------------------
	@Before
    public static void setup(Scenario scenario) {
        // Inicializa o gerenciador de relatórios
		reports = new Reports(driver, comAllure);
        // Cria e configura o WebDriver global
        GlobalDriver.set();
        // Exibe no console as tags do cenário que será executado
        System.out.println("\n\n\nTESTE CENARIO " + scenario.getSourceTagNames().toString() + " \n\n\n\n");
        // Atribui a instância do WebDriver à variável local
        driver = GlobalDriver.get();
	}
    // -----------------------------------
    // Executa APÓS cada cenário
    // -----------------------------------
	@After
	public void finish(Scenario scenario) throws Exception {
        // Obtém as tags do cenário executado
    	List<String> tagNames = (List<String>) scenario.getSourceTagNames();
        // Gera o relatório PDF individual do cenário
        reports.generatePdfReport();
        // Se o cenário falhou, adiciona suas tags à lista de falhas
        if (scenario.getStatus().name().contains("FAILED")) {
            stringListaParaPrintAposTodosCenarios.add(String.join(" ", tagNames));
        }
        // Fecha o navegador
	      GlobalDriver.close();
    }
    // ===========================================================
    // MÉTODOS EXECUTADOS DURANTE O CICLO DE VIDA DOS TESTES
    // ===========================================================

    // -----------------------------------
    // Executa ANTES de todos os testes
    // -----------------------------------

    @BeforeAll
    public static void setupAll() {
        // Limpa os diretórios de relatórios anteriores
    	AllureGenerator.deletarArquivos(new File(pathResults));
    	AllureGenerator.deletarArquivos(new File(pathReport));
    }

    // -----------------------------------
    // Executa APÓS todos os testes
    // -----------------------------------
	@AfterAll
    public static void finishAll() throws IOException, InterruptedException {
        // Se o Allure estiver habilitado, gera o relatório HTML final
		if(comAllure.equals("true")){
            // Cria um nome único para o arquivo baseado na data/hora
	    	String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
	    	String novoNomeArquivoAllure = time + "-index.html";
            // Gera e renomeia o relatório Allure
	    	AllureGenerator.gerarAllureReportHTML(pathResults,pathReport);
		   	AllureGenerator.RenomearArquivo(pathReport,novoNomeArquivoAllure);
    	}
        // Exibe no console todos os cenários que falharam
		System.out.println("**** lista FAILED apos todos cenarios ****");
		System.out.println(stringListaParaPrintAposTodosCenarios);
        // Fecha o driver, se ainda estiver aberto
	   	GlobalDriver.close();
    }
    // ===========================================================
    // MÉTODOS AUXILIARES
    // ===========================================================

    // Metodo utilitário para abrir uma URL no navegador
	public static void openBrowser(String url) {
        // Navega até a URL informada
        if(!isNull(driver)){
//        	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        	driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
            driver.navigate().to(url);
        }
        else {
            // Caso o driver ainda não tenha sido inicializado
            throw new RuntimeException("Driver não criado");
        }
    }
}