package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import stepDefinitions.BaseSteps;

import java.io.IOException;
import java.util.HashMap;

public class SearchBarPO extends BaseSteps {

    private final By inputSearchBar = By.xpath("//input[@placeholder='Search for anything']");
    private final By searchBarCategoryDropdown = By.xpath("//select[@aria-label='Select a category for search']");
    private final By buttonSearch = By.xpath("//input[@value='Search']");
    private final By searchResultsItems = By.xpath("//div[@id='srp-river-results']//li[@data-viewport]//div[@class='s-item__info clearfix']//div[@class='s-item__title']//span[@class='BOLD']");
    private HashMap<String, String> testData = new HashMap<>();

    public void enterSearchTermInSearchBar(String searchTerm) {
        getDriver().findElement(inputSearchBar).sendKeys(searchTerm);
        testData.put("searchTerm", searchTerm);
        takeScreenshot(searchTerm + " is entered in the search bar");
    }

    public void selectSearchCategory(String category) {

        Select select = new Select(getDriver().findElement(searchBarCategoryDropdown));
        select.selectByVisibleText(category);
        takeScreenshot(category + " is selected from the category dropdown");
    }


    public void clickOnSearchButton() {
        getDriver().findElement(buttonSearch).click();
        waitForPageLoad();
        takeScreenshot("Search results are displayed for "+testData.get("searchTerm"));
    }

    public boolean verifyFirstSearchResult() {
        return getDriver().findElement(searchResultsItems).getText().toLowerCase().contains(testData.get("searchTerm").toLowerCase());
    }


}
