package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

import java.util.List;

/*
    - The main page to start with: https://www.wiley.com/en-us
    - Go to Subjects top menu, select "Education Resources"
    - Check “Learning Resources” sub-header is displayed
    - Click on "Search By Subject" item
    - 20 headers are displayed under “Subjects” on the page:
      - "AGRICULTURE",
      - "ARTS & ARCHITECTURE",
      - "BUSINESS & MANAGEMENT",
      - "CHEMISTRY",
      - "COMPUTING",
      - "CULINARY & HOSPITALITY",
      - "EARTH & SPACE SCIENCES",
      - "EDUCATION",
      - "ENGINEERING & MATERIALS SCIENCE",
      - "HUMANITIES",
      - "LAW & CRIMINOLOGY",
      - "LIFE SCIENCES",
      - "LIFESTYLE",
      - "MATHEMATICS & STATISTICS",
      - "MEDICINE, NURSING & DENTISTRY",
      - "PHYSICS & ASTRONOMY",
      - "REFERENCE",
      - "SOCIAL & BEHAVIORAL SCIENCES",
      - "VETERINARY MEDICINE",
      - "WORLD LANGUAGES"
 */
public class HeadersOnEducationPageTest extends SeleniumBaseTest {
    private static final String EXPECTED_HEADER_MAIN_PAGE = "Learning Resources";
    private static final String EXPECTED_SECTION_TITLE_EDUCATION_PAGE = "Subjects";
    private static final List<String> EXPECTED_HEADERS_EDUCATION_PAGE = List.of(
            "AGRICULTURE",
            "ARTS & ARCHITECTURE",
            "BUSINESS & MANAGEMENT",
            "CHEMISTRY",
            "COMPUTING",
            "CULINARY & HOSPITALITY",
            "EARTH & SPACE SCIENCES",
            "EDUCATION",
            "ENGINEERING & MATERIALS SCIENCE",
            "HUMANITIES",
            "LAW & CRIMINOLOGY",
            "LIFE SCIENCES",
            "LIFESTYLE",
            "MATHEMATICS & STATISTICS",
            "MEDICINE, NURSING & DENTISTRY",
            "PHYSICS & ASTRONOMY",
            "REFERENCE",
            "SOCIAL & BEHAVIORAL SCIENCES",
            "VETERINARY MEDICINE",
            "WORLD LANGUAGES"
    );
    private static final int EXPECTED_NUMBERS_OF_HEADERS_EDUCATION_PAGE = 20;

    @Test(description = "Education page contains all requirements headers")
    public void headersOnEducationPage() {
        Pages.navigateToMainPage()
                .waitPageLoaded()
                .openSubHeadMenu("Education Resources")
                .containsHeader(EXPECTED_HEADER_MAIN_PAGE)
                .clickOnMenuSubItem("Search By Subject");
        Pages.getEducationPage()
                .waitPageLoaded()
                .checkSectionTitle(EXPECTED_SECTION_TITLE_EDUCATION_PAGE)
                .checkContentHeaders(EXPECTED_HEADERS_EDUCATION_PAGE)
                .checkNumberOfHeaders(EXPECTED_NUMBERS_OF_HEADERS_EDUCATION_PAGE)
                .checkContentHeadersUnderSectionTitle();
    }
}
