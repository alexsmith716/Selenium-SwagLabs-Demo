package com.SeleniumSwagLabsDemo.gluecode;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features", glue = "com.SeleniumSwagLabsDemo.gluecode")
public class CucumberTestRunner {

}
