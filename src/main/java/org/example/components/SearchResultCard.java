package org.example.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class SearchResultCard extends AbstractComponent<SearchResultCard> {
    private static final Logger log = LoggerFactory.getLogger(SearchResultCard.class);
    private static final By CARD_TITLE_BY = By.cssSelector("h3.product-title");
    public SearchResultCard(SelenideElement mainElement) {
        super(mainElement);
    }

    public SearchResultCard checkTitleContain(String value) {
        String title = $(CARD_TITLE_BY).scrollIntoView(false).getText();
        log.info("Check title '{}' contain '{}' in card", title, value);
        Assert.assertTrue(
                title.toLowerCase()
                        .contains(value.toLowerCase()));
        return this;
    }
}
