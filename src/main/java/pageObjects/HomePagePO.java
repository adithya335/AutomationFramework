package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.BaseSteps;

public class HomePagePO extends BaseSteps {

    private final By shopByCategory = By.id("gh-shop-a");
    private final String subCategory = "//a[text()='%s']";
    private final By shopByCategoryPopUp = By.xpath("//table[@id='gh-sbc']");


    public void clickOnShopByCategory() {
        getDriver().findElement(shopByCategory).click();
        getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopByCategoryPopUp));
        takeScreenshot("Shop by category is expanded");
    }

    public void clickOnSubcategory(String subCategory) {
        getDriver().findElement(By.xpath(getXpath(this.subCategory, subCategory))).click();
        waitForPageLoad();
        takeScreenshot(subCategory + " is opened");
    }
}
