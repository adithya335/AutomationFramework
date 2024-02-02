package stepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.SearchBarPO;

import java.io.IOException;

public class SearchBarSteps extends BaseSteps {
    SearchBarPO searchBarPO;

    @Then("enter {string} in the search bar")
    public void enterMacbookInTheSearchBar(String searchTerm) throws IOException {
        if (searchBarPO == null) {
            searchBarPO = new SearchBarPO();
        }
        searchBarPO.enterSearchTermInSearchBar(searchTerm);
    }

    @Then("change the search category to {string}")
    public void changeTheSearchCategoryToComputersTabletsNetworking(String searchCategory) {
        if (searchBarPO == null) {
            searchBarPO = new SearchBarPO();
        }
        searchBarPO.selectSearchCategory(searchCategory);
    }

    @Then("click on search button")
    public void clickOnSearchButton() {
        if (searchBarPO == null) {
            searchBarPO = new SearchBarPO();
        }
        searchBarPO.clickOnSearchButton();
    }

    @Then("Verify that the first result name matches with the search string")
    public void verifyThatTheFirstResultNameMatchesWithTheSearchString() {
        if (searchBarPO == null) {
            searchBarPO = new SearchBarPO();
        }
        Assert.assertTrue(searchBarPO.verifyFirstSearchResult());
    }
}
