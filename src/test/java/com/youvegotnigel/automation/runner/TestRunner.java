package com.youvegotnigel.automation.runner;

import com.youvegotnigel.automation.tests.TestBase;
import com.youvegotnigel.automation.utils.ReportHelper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/youvegotnigel/automation/stepdefs"},
        tags = "@regression and not @low",
        dryRun = false,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"
        })
public class TestRunner extends TestBase {

        private TestNGCucumberRunner testNGCucumberRunner;
        public static final Logger log = LogManager.getLogger(TestRunner.class.getName());

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
                testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }

        @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
        public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
                // the 'featureWrapper' parameter solely exists to display the feature
                // file in a test report
                testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        }

        @DataProvider
        public Object[][] scenarios() {
                return testNGCucumberRunner.provideScenarios();
        }


        @AfterClass(alwaysRun = true)
        public void tearDownClass() {

                testNGCucumberRunner.finish();
                try {
                        ReportHelper.generateCucumberReport();
                }catch (Exception e){
                        log.warn("No feature files found");
                        log.error(e.getMessage());
                }

                //baseClass.tearDown();
        }
}
