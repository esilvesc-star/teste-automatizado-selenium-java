package steps;

import actions.ValidarLoginInvalidosAction;
import actions.ValidarLoginValidoAction;
import browser.Chrome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class ValidarLoginValidosStep extends Chrome {

    private final ValidarLoginValidoAction validarLoginValidoAction;

    public ValidarLoginValidosStep() throws Exception {
        this.validarLoginValidoAction = new ValidarLoginValidoAction();

    }

    @Given("que eu acesse o site {string}")
    public void queEuAcesseOSite(String string) {
        System.out.println("Usuário já está na página de login (aberta pelo Hooks).");

    }

    @When("o usuário faz login com {string} e {string}")
    public void oUsuárioFazLoginComE(String usuario, String senha) throws Exception {
        validarLoginValidoAction.preencherUserName(usuario);
        validarLoginValidoAction.preencherPassWord(senha);
        validarLoginValidoAction.clicarLogin();

    }

    @Then("devo ser redirecionado para a página de produtos")
    public void devoSerRedirecionadoParaAPaginaDeProdutos() throws Exception {
        String textoHome = validarLoginValidoAction.homeLogada();
        // Validação simples de conteúdo do elemento
        assertEquals("Swag Labs", textoHome);

    }
}



