package components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import actions.SearchGoogleAction;

public class SearchGoogleComponent extends SearchGoogleAction {
	
	public SearchGoogleComponent(WebDriver webdriver) throws Exception{
		PageFactory.initElements(webdriver, this);
	}
	
		@FindBy(xpath = "//textarea[@title='Pesquisar']")
		public WebElement search;
		
		@FindBy(xpath = "//div[4]//input[@value='Pesquisa Google']")
		public WebElement buttonGoogleSearch;

		@FindBy(xpath = "//a[@href='https://nttdata-solutions.com/br/']")
		public WebElement SearchNTTDATA;
		
		@FindBy(xpath = "//li[1]//div[@role='option']")
		public WebElement firstSearchElementRoleNTTDATA;
		
		@FindBy(xpath = "//textarea[@aria-expanded='true']")
		public WebElement presentRoleSearchNTTDATA;
		
	}
