package com.youvegotnigel.automation.runner;

import com.youvegotnigel.automation.tests.TestBase;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        tags = "@regression and not @low",
        dryRun = false,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"
        })
public class TestRunner extends TestBase {
}
