package org.sample.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features= {"classpath:features/crypto2.feature"},
	plugin= {"pretty","html:target/cucumber-reports.html"},
	glue= {"org.sample.stepdefinitions"},
	tags= "@smoketest"
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
