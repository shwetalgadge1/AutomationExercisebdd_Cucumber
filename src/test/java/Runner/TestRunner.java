package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "D:\\workspace for cucucmber\\AutomationExercisebdd\\src\\test\\resources\\Features",
    glue = "StepDefination",
    plugin = {
        "pretty", 
        "html:target/cucumber-reports/html/report.html", 
        "json:target/cucumber-reports/json/cucumber.json",
        "junit:target/cucumber-reports/junit/cucumber.xml"
    }
)
public class TestRunner {
    
}