package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

/*
    - The main page to start with: https://www.wiley.com/en-us
    - Search functionality. Test should enter “Java” in the search input box and do not press the search button (with magnifying glass icon)
        check area with related content is displayed right under the search header
 */
public class SearchDropDownAreaPositionTest extends SeleniumBaseTest {
    private static final String SEARCH_VALUE = "Java";

    @Test(description = "Search drop down area is displayed right under the search header")
    public void searchDropDownAreaPosition() {
        Pages.navigateToMainPage()
                .waitPageLoaded()
                .inputInSearch(SEARCH_VALUE)
                .checkSearchDropDownPosition();
    }
}
