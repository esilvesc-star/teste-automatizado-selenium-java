package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginComponent {
    public LoginComponent(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    // Elementos Pagina login

    @FindBy(xpath = "//*[@id='user-name']")
    public WebElement UserName;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement PassWord;

    @FindBy(xpath = "//*[@id='login-button']")
    public WebElement BotaoLogin;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement MensagemErro;

    @FindBy(xpath = "//div[@class='app_logo']")
    public WebElement HomeLogada;

    //Elementos da tela principal apos login.
    //Contém o menu lateral e suas opções.

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    public WebElement MenuLateral;

    @FindBy(xpath = "//nav[@class='bm-item-list']//a")
    public List<WebElement> listaOpcoesMenu;

    //Contem o menu Filtro Nome(A to Z)

    @FindBy(xpath = "//*[@class='product_sort_container']")
    public WebElement MenuFiltro;

    @FindBy(xpath = "//select[@data-test='product-sort-container']/option[@value='az']")
    public WebElement OpcaoAZ;

    @FindBy(xpath = "//select[@data-test='product-sort-container']/option[@value='za']")
    public WebElement OpcaoZA;

    @FindBy(xpath = "//select[@data-test='product-sort-container']/option[@value='hilo']")
    public WebElement OpcaoHilo;

    @FindBy(xpath = "//select[@data-test='product-sort-container']/option[@value='lohi']")
    public WebElement OpcaoLohi;

}
