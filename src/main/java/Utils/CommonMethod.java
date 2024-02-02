package Utils;

import org.openqa.selenium.WebDriver;
import stepDefinitions.BaseSteps;

import static com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.getCurrentStep;

public class CommonMethod extends BaseSteps {


    public static void attachScreenshot(String msg) {

        getCurrentStep().info(msg);
    }
}
