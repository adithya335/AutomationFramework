package stepDefinitions;

import Utils.CommonMethod;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePagePO;

import java.io.IOException;
import java.lang.reflect.Method;

public class Homepage extends BaseSteps {
    HomePagePO homepagePO;


    @Given("the {string} application")
    public void theEbayApplication(String app) {
        getDriver().get(getProperty(app));
        waitForPageLoad();
        takeScreenshot("Homepage is displayed");
    }

    @Then("click on shop by category dropdown")
    public void clickOnShopByCategoryDropdown() {
        if (homepagePO == null) {
            homepagePO = new HomePagePO();
        }
        homepagePO.clickOnShopByCategory();

    }

    @Then("click on {string}")
    public void clickOnCellPhonesAccessories(String subCategory) {
        if (homepagePO == null) {
            homepagePO = new HomePagePO();
        }
        homepagePO.clickOnSubcategory(subCategory);
    }


}
