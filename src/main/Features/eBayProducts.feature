@allScenarios
Feature: Access a product on ebay

  Background:
    Given the "ebay" application

  @filterSearch
  Scenario:  Access a Product via category after applying multiple filters
    Then click on shop by category dropdown
    Then click on "Cell phones & accessories"
    Then click on Cell Phones & Smartphones in the left hand side navigation section
    Then click on All Filters option
    Then apply the following filters
      | Condition     | New      |
      | Price         | 0-100000 |
      | Item Location | US Only  |
    Then click on the apply button
#    Then verify that the filters are applied ----> this step has not been implemented as application was unstable.


  @searchBarSearch1
  Scenario:  Access a Product via Search
    Then enter "Macbook" in the search bar
    Then change the search category to "Computers/Tablets & Networking"
    Then click on search button
    Then Verify that the first result name matches with the search string

  @filterSearch
  Scenario:  Access a Product via category after applying multiple filters
    Then click on shop by category dropdown
    Then click on "Cell phones & accessories"
    Then click on Cell Phones & Smartphones in the left hand side navigation section
    Then click on All Filters option
    Then apply the following filters
      | Condition     | New      |
      | Price         | 0-100000 |
      | Item Location | US Only  |
    Then click on the apply button
#    Then verify that the filters are applied ----> this step has not been implemented as application was unstable.


  @searchBarSearch
  Scenario:  Access a Product via Search
    Then enter "Macbook" in the search bar
    Then change the search category to "Computers/Tablets & Networking"
    Then click on search button
    Then Verify that the first result name matches with the search string