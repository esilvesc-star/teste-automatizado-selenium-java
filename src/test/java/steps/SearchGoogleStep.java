package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import actions.NTTDATAAction;
import actions.SearchGoogleAction;
import browser.Chrome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Reports;
import static steps.Hooks.openBrowser;

public class SearchGoogleStep extends Chrome{
	private SearchGoogleAction searchGoogleAction;
	private NTTDATAAction nttdataAction;
	private Reports reports;
	
//	public SearchGoogleStep(WebDriver webdriver) {
//		Chrome.webdriver = webdriver;
//	}

	public SearchGoogleStep() throws Exception {
		searchGoogleAction = new SearchGoogleAction();
		nttdataAction = new NTTDATAAction();
//		reports = new Reports();
	}
	
	@Given("que Marcelo faz uma busca no google {string} com pesquisa {string} e acessar o Primeiro link apresentado na pesquisa.")
	public void que_marcelo_faz_uma_busca_no_google_com_pesquisa_e_acessar_o_primeiro_link_apresentado_na_pesquisa(String site, String palavraBusca) throws Exception {
		openBrowser(site);
		searchGoogleAction.Search(palavraBusca);
//		reports.takeScreenShot("Busca Google");
//		Thread.sleep(3000);
//		searchGoogleAction.FirstSearchNTTDATA();
//		Thread.sleep(3000);
//		nttdataAction.ButtonPopupCookiesAll();
//		nttdataAction.ButtonMenuCarreira();
//		nttdataAction.ButtonEquipe();
		
	}

	@When("estiver na pagina, clicar em carreira e acessar junte-se a nossa equipe pesquisar {string}.")
	public void estiver_na_pagina_clicar_em_carreira_e_acessar_junte_se_a_nossa_equipe_pesquisar(String vaga) throws Exception {
//		nttdataAction.SearchVaga(vaga);
//		nttdataAction.GetElements();
		
	}

	@Then("devera conter {int} vagas e ser vaga de {string}.")
	public void devera_conter_vagas_e_ser_vaga_de(Integer quantidadeVaga, String cargoVaga) {
//	   Assert.assertEquals("Quantidades de itens é igual a 3",nttdataAction.getQuantidadeElemento(),(int) quantidadeVaga);
//	   Assert.assertTrue("Elemento 1 contém Arquiteto",nttdataAction.getElementoVaga_1().contains(cargoVaga));
//	   Assert.assertTrue("Elemento 2 contém Arquiteto",nttdataAction.getElementoVaga_2().contains(cargoVaga));
//	   Assert.assertTrue("Elemento 3 contém Arquiteto",nttdataAction.getElementoVaga_3().contains(cargoVaga));
	}
}