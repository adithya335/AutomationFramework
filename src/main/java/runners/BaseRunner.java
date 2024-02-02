package runners;

import Utils.RunnerParallel;
import Utils.RunnerSequential;
import io.cucumber.testng.CucumberOptions;
import stepDefinitions.BaseSteps;

@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, glue = "stepDefinitions", features = "src//main//Features")
public class BaseRunner extends RunnerParallel {
}
