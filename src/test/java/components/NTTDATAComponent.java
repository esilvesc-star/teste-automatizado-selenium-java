package components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actions.NTTDATAAction;

public class NTTDATAComponent extends NTTDATAAction{
	
	public NTTDATAComponent(WebDriver webdriver) {
		PageFactory.initElements(webdriver, this);
	}
	
	@FindBy(id="all")
	public WebElement buttonPopupCookiesAll;
	
	@FindBy(xpath = "//li[@id='menu-item-32094']//a[@href='https://nttdata-solutions.com/br/carreira-na-nttdata/']")
	public WebElement buttonMenuCarreira;
	
	@FindBy(xpath = "//a[contains(text(),'Junte-se a nossa equipe')]")
	public WebElement buttonJunteseanossaequipe;
	
	@FindBy(id = "pesquisar-vaga-localidade-etc")
	public WebElement campoSearchVagas;
	
	@FindBy(xpath = "//div[@class='portlet-body']//div[@ng-repeat='vacancy in vm.model.vacancies | limitTo:vm.paginationModel.pageSize']//div//div//h4")
	public List<WebElement>vagasDaPesquisa;




}
	
	

