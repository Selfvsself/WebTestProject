package org.example.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class DropDownMainMenu extends AbstractComponent<DropDownMainMenu> {
    private static final Logger log = LoggerFactory.getLogger(DropDownMainMenu.class);
    private static final By HEADER_ITEMS_BY = By.xpath(".//span[contains(@class, 'wl-subheading')]");
    private static final String SUB_HEADER_BY_TEXT = ".//span[contains(@class, 'cmp-list__item-title') and text() = '%s']";

    public DropDownMainMenu(SelenideElement mainElement) {
        super(mainElement);
    }

    private List<String> getHeaders() {
        return $$(HEADER_ITEMS_BY)
                .texts()
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public DropDownMainMenu checkHeaders(List<String> expectedItems) {
        var actualItems = getHeaders();
        var upperItems = expectedItems.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        log.info("Check headers in sub menu: expected {}", upperItems);
        Assert.assertEquals(actualItems, expectedItems);
        return this;
    }

    public DropDownMainMenu containsHeader(String header) {
        var actualItems = getHeaders();
        String expectedHeader = header.toUpperCase();
        log.info("Check headers in sub menu contains '{}'", header);
        Assert.assertTrue(actualItems.contains(expectedHeader),
                String.format("Headers %s don't contains the header '%s'",
                        actualItems,
                        expectedHeader));
        return this;
    }

    public DropDownMainMenu clickOnMenuSubItem(String itemName) {
        $(By.xpath(String.format(SUB_HEADER_BY_TEXT, itemName))).click();
        return this;
    }
}
