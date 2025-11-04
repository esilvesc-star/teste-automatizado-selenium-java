package steps;

import actions.ValidarLoginValidoAction;
import actions.ValidarMenuLateralAction;
import browser.Chrome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;

public class ValidarMenuLateralStep extends Chrome {
    private final ValidarLoginValidoAction validarLoginValidoAction;
    private final ValidarMenuLateralAction validarMenuLateralAction;


    public ValidarMenuLateralStep() throws Exception {
        this.validarLoginValidoAction = new ValidarLoginValidoAction();
        this.validarMenuLateralAction = new ValidarMenuLateralAction();
    }

    @Given("que eu faça login com usuário {string} e senha {string}")
    public void queEuFaçaLoginComUsuárioESenha(String usuario, String senha) throws Exception {
        validarLoginValidoAction.preencherUserName(usuario);
        validarLoginValidoAction.preencherPassWord(senha);
        validarLoginValidoAction.clicarLogin();
    }

    @When("eu abro o menu lateral")
    public void euAbroOMenuLateral() throws Exception {
        validarMenuLateralAction.clicarMenuLateral();

    }

    @Then("devem ser exibidas as opções:")
    public void devemSerExibidasAsOpcoes(io.cucumber.datatable.DataTable dataTable) throws Exception {
        List<String> opcoesEsperadas = dataTable.asList();
        // Ignora o cabeçalho "Opção"
        opcoesEsperadas = opcoesEsperadas.subList(1, opcoesEsperadas.size());
        List<String> opcoesExibidas = validarMenuLateralAction.obterOpcoesMenuLateral();
        Assert.assertEquals("As opções exibidas não correspondem às esperadas!", opcoesEsperadas, opcoesExibidas);
    }
}