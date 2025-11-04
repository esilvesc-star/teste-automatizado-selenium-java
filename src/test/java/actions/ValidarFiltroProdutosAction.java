package actions;

import components.SauceDemoComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.Hooks;
import utils.Helpers;

public class ValidarFiltroProdutosAction extends Hooks {

    private final WebDriver webDriver = driver;
    private final SauceDemoComponent sauceDemoComponent;
    private final Helpers helpers;

    public ValidarFiltroProdutosAction() {
        this.sauceDemoComponent = new SauceDemoComponent(driver);
        this.helpers = new Helpers();
    }

    public void clicarFiltroNomeAtoZ() throws Exception {
        WebElement menuFiltro = Helpers.esperarVisibilidade(driver, sauceDemoComponent.MenuFiltro);
        menuFiltro.click();
    }




}
