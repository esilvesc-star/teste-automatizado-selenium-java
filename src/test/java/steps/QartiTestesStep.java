package steps;

import actions.QartiTestesAction;
import browser.Chrome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class QartiTestesStep extends Chrome {
    private QartiTestesAction qartiTestesAction;


    public QartiTestesStep() throws Exception {
        qartiTestesAction = new QartiTestesAction();
    }

    @Given("que o usuário acessa a página {string}")
    public void queOUsuárioAcessaAPágina(String url) throws Exception {
        qartiTestesAction.paginaInicial(url);
    }

    @When("ele preenche os campos do formulário:")
    public void elePreencheOsCamposDoFormulário(io.cucumber.datatable.DataTable dataTable) throws Exception {
        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);
        String nome = dados.get(0).get("nome");
        qartiTestesAction.preencherNome(nome);

        String email = dados.get(0).get("email");
        qartiTestesAction.preencherEmail(email);

        String celular = dados.get(0).get("celular");
        qartiTestesAction.preencherCelular(celular);

        String empresa = dados.get(0).get("empresa");
        qartiTestesAction.preencherEmpresa(empresa);

        String cargo = dados.get(0).get("cargo");
        qartiTestesAction.preencherCargo(cargo);

        qartiTestesAction.preencherPerguntaDinamica();
    }

    @When("clica no botão {string}")
    public void clicaNoBotão(String entrarEmContato) throws Exception {
        qartiTestesAction.clicarEntrarEmContato();
    }

    @Then("a página {string} deve ser exibida")
    public void aPáginaDeveSerExibida(String mensagemEsperada) {
        String mensagemObtida = qartiTestesAction.obterMensagemSucesso();
        Assert.assertEquals("A mensagem exibida não está correta!", mensagemEsperada, mensagemObtida);
    }

}








