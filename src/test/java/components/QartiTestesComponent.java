package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QartiTestesComponent {

    public QartiTestesComponent(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id='rd-text_field-m9abvysz']")
    public WebElement QartCampoName;

    @FindBy(xpath = "//*[@id='rd-email_field-m9abvyt0']")
    public WebElement QartCampoEmail;

    @FindBy(xpath = "//*[@id='rd-phone_field-m9abvyt1']")
    public WebElement QartCampoCelular;

    @FindBy(xpath = "//*[@id='rd-text_field-m9abvyt2']")
    public WebElement QartCampoEmpresa;

    @FindBy(xpath = "//*[@id='rd-text_field-m9abvyt3']")
    public WebElement QartCampoCargo;

    @FindBy(xpath = "//*[@id='math_expression']")
    public WebElement QartPergunta;

    @FindBy(xpath = "//*[@id='captcha']")
    public WebElement QartCampoPergunta;

    @FindBy(xpath = "//*[@id='rd-button-joq3m2w0']")
    public WebElement QartBotaoQueroEntrarEmContato;

    @FindBy(xpath = "//strong[text()='Obrigada pelo interesse em nossos serviços']")
    public WebElement QartMensagemSucesso;


}
