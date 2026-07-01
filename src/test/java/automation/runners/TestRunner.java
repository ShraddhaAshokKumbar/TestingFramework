package automation.runners;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "src/test/resources/features",

        glue = {"org.automation.stepdefinitions"}, // Pointing strictly to stepdefs & hooks package

        plugin = {"pretty", "html:target/cucumber-reports.html"},

        monochrome = true

)

public class TestRunner {

}
