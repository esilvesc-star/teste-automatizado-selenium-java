package actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import components.NTTDATAComponent;
import utils.GlobalDriver;

public class NTTDATAAction {
	 private final WebDriver webdriver = GlobalDriver.webdriver;
	 NTTDATAComponent nttdataComponent;
		
	private int quantidadeElemento;
	private String elementoVaga_1;
	private String elementoVaga_2;
	private String elementoVaga_3;
	
	public void ButtonPopupCookiesAll() throws Exception {
		nttdataComponent = new NTTDATAComponent(webdriver);
		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		nttdataComponent.buttonPopupCookiesAll.click();
	}
	
	public void ButtonMenuCarreira() throws Exception {
		nttdataComponent = new NTTDATAComponent(webdriver);
		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		nttdataComponent.buttonMenuCarreira.click();
	}
	
	public void ScrollElement() throws Exception {
		nttdataComponent = new NTTDATAComponent(webdriver);
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("arguments[0].scrollIntoView();", nttdataComponent.buttonJunteseanossaequipe);
	}
	
	public void ButtonEquipe() throws Exception {
		nttdataComponent = new NTTDATAComponent(webdriver);
		ScrollElement();
		nttdataComponent.buttonJunteseanossaequipe.click();
	}
	
	public void SearchVaga(String palavraPesquisa) throws Exception {
		nttdataComponent = new NTTDATAComponent(webdriver);
		ArrayList<String> tabs = new ArrayList<String>(webdriver.getWindowHandles());
		webdriver.switchTo().window(tabs.get(1));
		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		nttdataComponent.campoSearchVagas.sendKeys(palavraPesquisa);
	}
	
	public void GetElements() throws Exception {
		Thread.sleep(5000);
		List<WebElement> options = webdriver.findElements(By.xpath("//div[@class='portlet-body']//div[@ng-repeat='vacancy in vm.model.vacancies | limitTo:vm.paginationModel.pageSize']//div//div//h4"));
		int add = 0;
			for(WebElement elem  : options) {
				add = add + 1;
				System.out.println(elem.getText());
			}
		
		quantidadeElemento = add;
		elementoVaga_1 = options.get(0).getText();
		elementoVaga_2 = options.get(1).getText();
		elementoVaga_3 = options.get(2).getText();
	}

	public int getQuantidadeElemento() {
		return quantidadeElemento;
	}

	public void setQuantidadeElemento(int quantidadeElemento) {
		this.quantidadeElemento = quantidadeElemento;
	}
	
	public String getElementoVaga_1() {
		return elementoVaga_1;
	}

	public void setElementoVaga_1(String elementoVaga_1) {
		this.elementoVaga_1 = elementoVaga_1;
	}

	public String getElementoVaga_2() {
		return elementoVaga_2;
	}

	public void setElementoVaga_2(String elementoVaga_2) {
		this.elementoVaga_2 = elementoVaga_2;
	}

	public String getElementoVaga_3() {
		return elementoVaga_3;
	}

	public void setElementoVaga_3(String elementoVaga_3) {
		this.elementoVaga_3 = elementoVaga_3;
	}
}
	
	

