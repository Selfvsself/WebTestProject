package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

import java.util.List;

/*
    - The main page to start with: https://www.wiley.com/en-us
    - Test needs to open the page and check titles under 'Shop' sub-head
        there should be 4 titles under resources sub-header
        titles are “Books”, “Courseware”, “Test Prep”, “BRANDS AND IMPRINTS”
 */
public class SubHeadMenuItemsTest extends SeleniumBaseTest {

    private static final List<String> EXPECTED_SUBMENU_HEADERS = List.of(
            "BOOKS",
            "COURSEWARE",
            "TEST PREP",
            "BRANDS AND IMPRINTS"
    );

    @Test(description = "Sub-head menu contain all items")
    public void subHeadMenuTest() {
        Pages.navigateToMainPage()
                .waitPageLoaded()
                .openSubHeadMenu("Shop")
                .checkHeaders(EXPECTED_SUBMENU_HEADERS);
    }
}
