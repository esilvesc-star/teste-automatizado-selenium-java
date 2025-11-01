package actions;

import components.LoginComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.Hooks;
import utils.Helpers;

public class ValidarLoginInvalidosAction extends Hooks {
    private final WebDriver webDriver = driver;
    private final LoginComponent loginComponent;
    private final Helpers helpers;

    public ValidarLoginInvalidosAction() {
        this.loginComponent = new LoginComponent(driver);
        this.helpers = new Helpers();
    }

    public void preencherUserName(String usuario) throws Exception {
        WebElement campoNome = Helpers.esperarVisibilidade(driver, loginComponent.UserName);
        campoNome.click();
        campoNome.sendKeys(usuario);
    }

    public void preencherPassWord(String senha) throws Exception {
        WebElement campoSenha = Helpers.esperarVisibilidade(driver, loginComponent.PassWord);
        campoSenha.click();
        campoSenha.sendKeys(senha);
    }

    public void clicarLogin() throws Exception {
        WebElement botaoLogin = Helpers.esperarVisibilidade(driver, loginComponent.BotaoLogin);
        botaoLogin.click();
    }

    public String obterMensagemErro() {
        WebElement mensagem = Helpers.esperarVisibilidade(driver, loginComponent.MensagemErro);
        return mensagem.getText().trim();
    }


}




