package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/json/cucumber.json",
                "junit:target/junit/cucumber.xml"
                , "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
//        monochrome = true,
        glue = {"steps"},
        features = "./src/test/resources/features/",
        tags = "@testeQarti",
        snippets = SnippetType.CAMELCASE
)


public class TestRunner {

}
