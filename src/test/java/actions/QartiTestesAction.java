package actions;

import components.QartiTestesComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Hooks;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QartiTestesAction extends Hooks {
    private final WebDriver webDriver = driver;
    QartiTestesComponent qartiTestesComponent;

    public QartiTestesAction() {
        this.qartiTestesComponent = new QartiTestesComponent(driver);
    }

    // Metodo que inclui a URL
    public void paginaInicial(String url) throws Exception {
        webDriver.get(url);
    }

    // Metodo para esperar o campo ficar visivel
    private WebElement esperarVisibilidade(WebElement elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(elemento));
    }

    // Metodo para o botao ficar clicavel
    private WebElement esperarClicavel(WebElement elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    // Metodo para digitar lento
    private void digitarLento(WebElement elemento, String texto) throws InterruptedException {
        for (char c : texto.toCharArray()) {
            elemento.sendKeys(String.valueOf(c));
            Thread.sleep(100); // espera 100 ms entre teclas
        }
    }

    public void preencherNome(String nome) throws Exception {
        WebElement campoNome = esperarVisibilidade(qartiTestesComponent.QartCampoName);
        campoNome.click();
        campoNome.clear();
        campoNome.sendKeys(nome);
    }

    public void preencherEmail(String email) throws Exception {
        WebElement campoEmail = esperarVisibilidade(qartiTestesComponent.QartCampoEmail);
        campoEmail.click();
        campoEmail.sendKeys(email);
    }

    public void preencherCelular(String celular) throws Exception {
        WebElement campoCelular = esperarVisibilidade(qartiTestesComponent.QartCampoCelular);
        campoCelular.click();
        // Digita lentamente para simular interação real do usuário
        digitarLento(campoCelular, celular);
        // Pressiona TAB para forçar a validação do campo
        campoCelular.sendKeys(Keys.TAB);
    }

    public void preencherEmpresa(String empresa) throws Exception {
        WebElement campoEpresa = esperarVisibilidade(qartiTestesComponent.QartCampoEmpresa);
        campoEpresa.click(); // clica no campo
        campoEpresa.sendKeys(empresa); // digita o Empresa
    }

    public void preencherCargo(String cargo) throws Exception {
        WebElement campoCargo = esperarVisibilidade(qartiTestesComponent.QartCampoCargo);
        campoCargo.click(); // clica no campo
        campoCargo.sendKeys(cargo); // digita o Empresa
    }

    public void preencherPerguntaDinamica() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Espera o elemento da pergunta aparecer
        WebElement pergunta = wait.until(ExpectedConditions.visibilityOf(qartiTestesComponent.QartPergunta));
        String textoPergunta = pergunta.getText().trim(); // Ex: "4 + 1 ="
        // Extrai os números e o operador
        Pattern padrao = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
        Matcher matcher = padrao.matcher(textoPergunta);

        int resultado = 0;
        if (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            String operador = matcher.group(2);
            int num2 = Integer.parseInt(matcher.group(3));

            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    resultado = num1 / num2;
                    break;
            }
        } else {
            throw new Exception("Não foi possível identificar a pergunta matemática.");
        }
        // Preenche o campo de resposta
        WebElement campoResposta = wait.until(ExpectedConditions.visibilityOf(qartiTestesComponent.QartCampoPergunta));
        campoResposta.click();
        campoResposta.clear();
        campoResposta.sendKeys(String.valueOf(resultado));

        System.out.println("✅ Pergunta: " + textoPergunta + " | Resposta: " + resultado);
    }

    public void clicarEntrarEmContato() throws Exception {
        WebElement botaoEntrarEmContato = esperarClicavel(qartiTestesComponent.QartBotaoQueroEntrarEmContato);
        botaoEntrarEmContato.click(); // clica no botão
    }

    public String obterMensagemSucesso() {
        WebElement mensagemSucesso = esperarVisibilidade(qartiTestesComponent.QartMensagemSucesso);
        return mensagemSucesso.getText();
    }
}











