package actions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browser.Chrome;
import components.SearchGoogleComponent;
import steps.Hooks;
import steps.SearchGoogleStep;
import utils.GlobalDriver;


public class SearchGoogleAction extends Hooks{
	    private final WebDriver webdriver = driver;
	   
	   
		SearchGoogleComponent searchGoogleComponent;
		
		public SearchGoogleAction() {
	    }
		
		public void Search(String palavraPesquisa) throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
	        wait.until(ExpectedConditions.visibilityOf(searchGoogleComponent.search));
			System.out.println(palavraPesquisa);
			Thread.sleep(10000);
			searchGoogleComponent.search.sendKeys(palavraPesquisa);
		}
		
		public void ButtonSearch() throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ZERO);
	        wait.until(ExpectedConditions.visibilityOf(searchGoogleComponent.buttonGoogleSearch));
			searchGoogleComponent.buttonGoogleSearch.click();
		}
		
		public void FirstSearchNTTDATA() throws Exception {
			searchGoogleComponent = new SearchGoogleComponent(webdriver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ZERO);
	        wait.until(ExpectedConditions.visibilityOf(searchGoogleComponent.presentRoleSearchNTTDATA));

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
