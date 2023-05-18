package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
	    plugin = {"json:target/json/cucumber.json", "junit:target/junit/cucumber.xml"},
//        monochrome = true,
        glue = {"steps"},
        features = "./src/main/java/features/",
//        tags = "@MORCIR-11967",
        snippets = SnippetType.CAMELCASE
        )


public class TestRunner {


}
