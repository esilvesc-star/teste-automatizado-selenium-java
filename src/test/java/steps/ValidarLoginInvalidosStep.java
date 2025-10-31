package steps;

import actions.ValidarLoginInvalidosAction;
import browser.Chrome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ValidarLoginInvalidosStep extends Chrome {

    private final ValidarLoginInvalidosAction validarLoginInvalidosAction;

    public ValidarLoginInvalidosStep() throws Exception {
        this.validarLoginInvalidosAction = new ValidarLoginInvalidosAction();
    }


    @Given("que o usuário está na página de login")
    public void queOUsuarioEstaNaPaginaDeLogin() throws Exception {
        System.out.println("Usuário já está na página de login (aberta pelo Hooks).");

    }

    @When("o usuário tenta logar com {string} e {string}")
    public void oUsuarioTentaLogarCom(String usuario, String senha) throws Exception {
        validarLoginInvalidosAction.preencherUserName(usuario);
        validarLoginInvalidosAction.preencherPassWord(senha);
        validarLoginInvalidosAction.clicarLogin();
    }

    @Then("o sistema deve exibir a mensagem {string}")
    public void oSistemaDeveExibirAMensagem(String mensagemEsperada) {
        String mensagemObtida = validarLoginInvalidosAction.obterMensagemErro();
        Assert.assertEquals(
                "Mensagem exibida incorretamente!",
                mensagemEsperada.trim(),
                mensagemObtida
        );
    }
}

