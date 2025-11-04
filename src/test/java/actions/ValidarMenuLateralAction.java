package actions;

import components.SauceDemoComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.Hooks;
import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class ValidarMenuLateralAction extends Hooks {
    private final WebDriver webDriver = driver;
    private final SauceDemoComponent sauceDemoComponent;
    private final Helpers helpers;

    public ValidarMenuLateralAction() {
        this.sauceDemoComponent = new SauceDemoComponent(driver);
        this.helpers = new Helpers();
    }

    public void clicarMenuLateral() throws Exception {
        WebElement menuLateral = Helpers.esperarVisibilidade(driver, sauceDemoComponent.MenuLateral);
        menuLateral.click();
    }
    // Metodo que obtém todas as opções do menu lateral e retorna seus textos em uma lista de String.
    // Ele percorre a lista de elementos WebElement e extrai o texto de cada um, removendo espaços extras.
    public List<String> obterOpcoesMenuLateral() {
        List<WebElement> opcoes = sauceDemoComponent.listaOpcoesMenu;
        List<String> textos = new ArrayList<>();

        for (WebElement opcao : opcoes) {
            textos.add(opcao.getText().trim());
        }

        return textos;
    }
}