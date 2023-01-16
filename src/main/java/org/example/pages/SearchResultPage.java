package org.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.example.components.SearchResultCard;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends MainPage<SearchResultPage> {
    private static final By SEARCH_RESULT_CARD_BY = By.cssSelector(".product-item");
    private static final By PAGE_ANCHOR_BY = By.cssSelector(".footer");

    @Override
    public SearchResultPage waitPageLoaded() {
        $(PAGE_ANCHOR_BY).shouldBe(Condition.exist);
        return this;
    }

    @Step
    public SearchResultPage checkNumberOfCards(int expectedNumOfCards) {
        var actualNumOfCards = $$(SEARCH_RESULT_CARD_BY).size();
        log.info("Check numbers of cards, expected {}", expectedNumOfCards);
        Assert.assertEquals(actualNumOfCards, expectedNumOfCards);
        return this;
    }

    @Step
    public SearchResultPage performForAllCards(Consumer<SearchResultCard> action) {
        var list = getCardList();

        for (var card : list) {
            try {
                action.accept(card);
            } catch (Throwable e) {
                throw new AssertionError(e.getMessage(), e);
            }
        }

        return this;
    }

    @Step
    private List<SearchResultCard> getCardList() {
        var iterator = $$(SEARCH_RESULT_CARD_BY).listIterator();
        List<SearchResultCard> cards = new ArrayList<>();
        while (iterator.hasNext()) {
            cards.add(new SearchResultCard(iterator.next()));
        }
        return cards;
    }
}
