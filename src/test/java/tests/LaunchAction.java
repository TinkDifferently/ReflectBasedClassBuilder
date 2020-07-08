package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "my.stepdefs",
        features = "src\\test\\resources\\features"
)
public class LaunchAction {
}
