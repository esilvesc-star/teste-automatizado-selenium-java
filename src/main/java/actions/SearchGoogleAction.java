package actions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;


import components.SearchGoogleComponent;
import utils.GlobalDriver;


public class SearchGoogleAction {
	    private final WebDriver webdriver = GlobalDriver.webdriver;
		SearchGoogleComponent searchGoogleComponent;
		
		public void Search(String palavraPesquisa) throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			searchGoogleComponent.search.sendKeys(palavraPesquisa);
		}
		
		public void ButtonSearch() throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			searchGoogleComponent.buttonGoogleSearch.click();
		}
		
		public void FirstSearchNTTDATA() throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(searchGoogleComponent.presentRoleSearchNTTDATA.isDisplayed() == true) {
				searchGoogleComponent.firstSearchElementRoleNTTDATA.click();
				searchGoogleComponent.SearchNTTDATA.click();
			}
			else{
				ButtonSearch();
				searchGoogleComponent.SearchNTTDATA.click();
			}
		}
	}
