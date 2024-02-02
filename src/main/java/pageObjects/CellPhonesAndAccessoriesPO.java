package pageObjects;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.BaseSteps;

import java.util.List;

public class CellPhonesAndAccessoriesPO extends BaseSteps {

    private final By cellPhonesAndSmartphones = By.linkText("Cell Phones & Smartphones");
    private final By allFilters = By.xpath("//button[text()='All Filters']");
    private final String filters = "//span[text()='%s' and @class='x-overlay-aspect__label']";
    private final By filterOptions = By.xpath("//fieldset[@class='x-overlay-sub-panel__aspect-fieldset']");
    private final By filtersOverlay = By.id("x-overlay__form");
    private final String filterInputCheckboxOrRadio = "//span[text()='%s']/../..//input";
    private final By filterInputFrom = By.xpath("(//input[@type='text'])[1]");
    private final By filterInputTo = By.xpath("(//input[@type='text'])[2]");
    private final By applyFilters = By.xpath("//button[text()='Apply']");

    public void clickOnCellPhonesAndSmartPhones() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(cellPhonesAndSmartphones));
        getDriver().findElement(cellPhonesAndSmartphones).click();
        waitForPageLoad();
        takeScreenshot("");
    }

    public void clickOnAllFilters() {
        getDriver().findElement(allFilters).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(filtersOverlay));
        takeScreenshot("All filters pop up is displayed");

    }

    public void waitForFilterValues() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(filterOptions));
    }

    public void selectFilters(DataTable dataTable) {
        List<List<String>> filtersToApply = dataTable.asLists();
        for (List<String> a : filtersToApply) {
            switch (a.get(0)) {
                case "Condition":
                case "Item Location":
                    getDriver().findElement(By.xpath(getXpath(filters, a.get(0)))).click();
                    waitForFilterValues();
                    getDriver().findElement(By.xpath(getXpath(filterInputCheckboxOrRadio, a.get(1)))).click();
                    takeScreenshot(a.get(0) + " filter is applied");
                    break;
                case "Price":
                    getDriver().findElement(By.xpath(getXpath(filters, a.get(0)))).click();
                    waitForFilterValues();
                    String[] prices = a.get(1).split("-");
                    getDriver().findElement(filtersOverlay).findElement(filterInputFrom).sendKeys(prices[0]);
                    getDriver().findElement(filtersOverlay).findElement(filterInputTo).sendKeys(prices[1]);
                    takeScreenshot(a.get(0) + " range is entered");
                    break;
                default:
                    System.out.println("Please enter valid filters");
            }
        }
    }

    public void clickOnApplyButton() {
        getDriver().findElement(applyFilters).click();
        waitForPageLoad();
        takeScreenshot("Results are displayed based on filters entered");
    }

}
