package steps;

import browser.Chrome;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidarFiltroProdutosStep extends Chrome {

    @When("eu selecionar o filtro {string}")
    public void euSelecionarOFiltro(String string) {

    }
    @Then("os produtos devem ser exibidos na ordem {string}")
    public void osProdutosDevemSerExibidosNaOrdem(String string) {

    }
}
