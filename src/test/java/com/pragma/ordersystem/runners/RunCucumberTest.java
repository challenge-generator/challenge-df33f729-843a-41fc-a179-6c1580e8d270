package com.pragma.ordersystem.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.ordersystem.steps"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@ordenes",
    dryRun = false
)
public class RunCucumberTest {
}