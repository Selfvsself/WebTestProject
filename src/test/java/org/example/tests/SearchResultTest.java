package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

/*
    - The main page to start with: https://www.wiley.com/en-us
    - Enter “Java” in the search input, press the search button and apply checks to verify that
        all titles containing “Java” are displayed
        there are 10 titles on the page
*/
public class SearchResultTest extends SeleniumBaseTest {
    private static final String SEARCH_VALUE = "Java";
    private static final int EXPECTED_NUMBERS_OF_CARDS = 10;

    @Test(description = "Search results contains the search word in title")
    public void searchResultTest() {
        Pages.navigateToMainPage()
                .waitPageLoaded()
                .search(SEARCH_VALUE)
                .waitPageLoaded()
                .performForAllCards(card -> card
                        .checkTitleContain(SEARCH_VALUE))
                .checkNumberOfCards(EXPECTED_NUMBERS_OF_CARDS);
    }
}
