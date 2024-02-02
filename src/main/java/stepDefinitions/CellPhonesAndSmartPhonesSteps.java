package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import pageObjects.CellPhonesAndAccessoriesPO;

public class CellPhonesAndSmartPhonesSteps {
    CellPhonesAndAccessoriesPO cellPhonesAndAccessoriesPO;

    @Then("click on Cell Phones & Smartphones in the left hand side navigation section")
    public void clickOnCellPhonesAndSmartphonesInTheLeftHandSideNavigationSection() {
        if (cellPhonesAndAccessoriesPO == null) {
            cellPhonesAndAccessoriesPO = new CellPhonesAndAccessoriesPO();
        }
        cellPhonesAndAccessoriesPO.clickOnCellPhonesAndSmartPhones();
    }

    @Then("click on All Filters option")
    public void clickOnAllFiltersOption() {
        if (cellPhonesAndAccessoriesPO == null) {
            cellPhonesAndAccessoriesPO = new CellPhonesAndAccessoriesPO();
        }
        cellPhonesAndAccessoriesPO.clickOnAllFilters();
    }

    @Then("apply the following filters")
    public void applyTheFollowingFilters(DataTable dataTable) {
        if (cellPhonesAndAccessoriesPO == null) {
            cellPhonesAndAccessoriesPO = new CellPhonesAndAccessoriesPO();
        }
        cellPhonesAndAccessoriesPO.selectFilters(dataTable);
    }

    @Then("click on the apply button")
    public void clickOnTheApplyButton() {
        if (cellPhonesAndAccessoriesPO == null) {
            cellPhonesAndAccessoriesPO = new CellPhonesAndAccessoriesPO();
        }
        cellPhonesAndAccessoriesPO.clickOnApplyButton();
    }


}
