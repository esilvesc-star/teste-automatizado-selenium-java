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

public class Hooks {
    // Instância principal do WebDriver (compartilhada entre os testes)
    public static WebDriver driver;

    // Lê do arquivo de propriedades se o Allure deve ser utilizado ("true" ou "false")
    static String comAllure = Helpers.carregueUmArquivoDaPropriedades("comAllure");

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
    public void afterStep(Scenario scenario) throws Exception {
        if (!isNull(GlobalDriver.get())) {
            reports.captureEvidence(driver, scenario.getName());
        }
    }

    // -----------------------------------
    // Executa ANTES de cada passo (Given/When/Then)
    // -----------------------------------
    @BeforeStep
    public void beforeStep() {
        // Pode ser usado futuramente para logs ou preparação
    }

    // -----------------------------------
    // Executa ANTES de cada cenário
    // -----------------------------------
    @Before
    public void setup(Scenario scenario) {
        // Inicializa o gerenciador de relatórios
        reports = new Reports(driver, comAllure);

        // Cria e configura o WebDriver global
        GlobalDriver.set();
        driver = GlobalDriver.get();

        // 🔹 Lê a URL base do arquivo application.properties
        String urlBase = Helpers.carregueUmArquivoDaPropriedades("urlBase");

        // 🔹 Navega até a URL base (ex: https://www.saucedemo.com/)
        if (urlBase != null && !urlBase.isEmpty()) {
            driver.navigate().to(urlBase);
            System.out.println("URL carregada: " + urlBase);
        } else {
            throw new RuntimeException("A URL base não foi configurada no arquivo application.properties!");
        }

        // Exibe as tags do cenário no console
        System.out.println("\n\n--- INICIANDO CENÁRIO --- " + scenario.getSourceTagNames() + "\n\n");
    }

    // -----------------------------------
    // Executa APÓS cada cenário
    // -----------------------------------
    @After
    public void finish(Scenario scenario) throws Exception {
        List<String> tagNames = new ArrayList<>(scenario.getSourceTagNames());
        reports.generatePdfReport();

        if (scenario.getStatus().name().contains("FAILED")) {
            stringListaParaPrintAposTodosCenarios.add(String.join(" ", tagNames));
        }

        GlobalDriver.close();
    }

    // -----------------------------------
    // Executa ANTES de todos os testes
    // -----------------------------------
    @BeforeAll
    public static void setupAll() {
        AllureGenerator.deletarArquivos(new File(pathResults));
        AllureGenerator.deletarArquivos(new File(pathReport));
    }

    // -----------------------------------
    // Executa APÓS todos os testes
    // -----------------------------------
    @AfterAll
    public static void finishAll() throws IOException, InterruptedException {
        if (comAllure.equals("true")) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
            String novoNomeArquivoAllure = time + "-index.html";
            AllureGenerator.gerarAllureReportHTML(pathResults, pathReport);
            AllureGenerator.RenomearArquivo(pathReport, novoNomeArquivoAllure);
        }

        System.out.println("**** Cenários que falharam ****");
        System.out.println(stringListaParaPrintAposTodosCenarios);
        GlobalDriver.close();
    }

    // -----------------------------------
    // Método utilitário opcional
    // -----------------------------------
    public static void openBrowser(String url) {
        if (!isNull(driver)) {
            driver.navigate().to(url);
        } else {
            throw new RuntimeException("Driver não criado");
        }
    }
}