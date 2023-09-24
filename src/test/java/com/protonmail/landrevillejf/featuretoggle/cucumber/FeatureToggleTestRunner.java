package com.protonmail.landrevillejf.featuretoggle.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Path to feature files
        glue = "com.protonmail.landrevillejf.featuretoggle.cucumber", // Package where step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"} // Plugins for reporting
)
public class FeatureToggleTestRunner {
}


