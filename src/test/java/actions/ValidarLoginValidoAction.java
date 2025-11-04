package actions;

import components.SauceDemoComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.Hooks;
import utils.Helpers;

public class ValidarLoginValidoAction extends Hooks {
    private final WebDriver webDriver = driver;
    private final SauceDemoComponent sauceDemoComponent;
    private final Helpers helpers;

    public ValidarLoginValidoAction() {
        this.sauceDemoComponent = new SauceDemoComponent(driver);
        this.helpers = new Helpers();
    }

    public void preencherUserName(String usuario) throws Exception {
        WebElement campoNome = Helpers.esperarVisibilidade(driver, sauceDemoComponent.UserName);
        campoNome.click();
        campoNome.clear();
        campoNome.sendKeys(usuario);
    }

    public void preencherPassWord(String senha) throws Exception {
        WebElement campoSenha = Helpers.esperarVisibilidade(driver, sauceDemoComponent.PassWord);
        campoSenha.click();
        campoSenha.clear();
        campoSenha.sendKeys(senha);
    }

    public void clicarLogin() throws Exception {
        WebElement botaoLogin = Helpers.esperarVisibilidade(driver, sauceDemoComponent.BotaoLogin);
        botaoLogin.click();
    }

    public String homeLogada() {
        WebElement homeLogada = Helpers.esperarVisibilidade(driver, sauceDemoComponent.HomeLogada);
        return homeLogada.getText().trim();
    }

}
