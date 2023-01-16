package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EducationPage extends MainPage<EducationPage> {
    private static final Logger log = LoggerFactory.getLogger(EducationPage.class);
    private static final By PAGE_ANCHOR_BY = By.cssSelector(".footer");
    private static final By SECTION_TITLE_BY = By.cssSelector(".sg-title-h1");
    private static final By CONTENT_HEADERS_BY = By.xpath("//div[contains(@class, 'cke-content')]//table//strong");

    @Override
    public EducationPage waitPageLoaded() {
        $(PAGE_ANCHOR_BY).shouldBe(Condition.exist);
        return this;
    }

    @Step
    public EducationPage checkSectionTitle(String expectedTitle) {
        $(SECTION_TITLE_BY).shouldHave(Condition.text(expectedTitle));
        log.info("Checked section title, expected '{}'", expectedTitle);
        return this;
    }

    @Step
    public EducationPage checkContentHeaders(List<String> expectedHeaders) {
        var actualHeaders = $$(CONTENT_HEADERS_BY).texts()
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        var upperExpHeaders = expectedHeaders.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        log.info("Check headers on page: expected {}", expectedHeaders);
        Assert.assertEquals(actualHeaders, upperExpHeaders);
        return this;
    }

    @Step
    public EducationPage checkNumberOfHeaders(int expectedNumOfHeaders) {
        var actualNumOfHeaders = $$(CONTENT_HEADERS_BY).size();
        log.info("Check numbers of headers, expected {}", expectedNumOfHeaders);
        Assert.assertEquals(actualNumOfHeaders, expectedNumOfHeaders);
        return this;
    }

    @Step
    public EducationPage checkContentHeadersUnderSectionTitle() {
        int sectionTitlePosY = $(SECTION_TITLE_BY).getLocation().getY();
        var headers = getContentHeadersElements();
        headers.forEach(header -> {
            header.scrollIntoView(false);
            int headerPosY = header.getLocation().getY();
            log.info("Check header '{}' pos: [y={}] should be below title of section [y={}}]",
                    header.getText(),
                    headerPosY,
                    sectionTitlePosY);
            Assert.assertTrue(headerPosY > sectionTitlePosY,
                    String.format("Header '%s' pos: [y=%s] should be below title of section [y=%s]",
                            header.getText(),
                            headerPosY,
                            sectionTitlePosY));
        });
        return this;
    }

    @Step
    private List<SelenideElement> getContentHeadersElements() {
        var iterator = $$(CONTENT_HEADERS_BY).listIterator();
        List<SelenideElement> elements = new ArrayList<>();
        while (iterator.hasNext()) {
            elements.add(iterator.next());
        }
        return elements;
    }
}
