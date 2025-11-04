package steps;

import actions.ValidarFiltroProdutosAction;
import browser.Chrome;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidarFiltroProdutosStep extends Chrome {
    private final ValidarFiltroProdutosAction validarFiltroProdutosAction;

    public ValidarFiltroProdutosStep() throws  Exception{
        this.validarFiltroProdutosAction = new ValidarFiltroProdutosAction();
    }

    @When("eu selecionar o filtro {string}")
    public void euSelecionarOFiltro(String string) throws Exception{
        validarFiltroProdutosAction.clicarFiltroNomeAtoZ();

    }
    @Then("os produtos devem ser exibidos na ordem {string}")
    public void osProdutosDevemSerExibidosNaOrdem(String string) {

    }
}
