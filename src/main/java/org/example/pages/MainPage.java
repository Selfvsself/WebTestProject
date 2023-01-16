package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.components.DropDownMainMenu;
import org.example.utils.ElementsPositionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage<P extends MainPage<P>> extends BasePage<P> {
    Logger log = LoggerFactory.getLogger(MainPage.class);
    private static final By PAGE_ANCHOR_BY = By.cssSelector("#footer-");
    private static final By SUBMENU_ITEMS_BY = By.xpath("//div[contains(@class, 'wl-desktop-dropdown--menu')]//li[contains(@class, 'cmp-navigation__item--level-0')]");
    private static final By DROPDOWN_MAIN_MENU_RELATIVE_BY = By.xpath(".//div[contains(@class, 'wl-main-menu__dropdown')]");
    private static final By SEARCH_INPUT_BY = By.xpath("//input[@name='search']");
    private static final By SEARCH_DROP_DOWN_BY = By.xpath("//div[@class='auto-suggestion-box']");

    @Override
    public P waitPageLoaded() {
        $(PAGE_ANCHOR_BY).shouldBe(Condition.exist);
        return (P) this;
    }

    @Step
    public DropDownMainMenu openSubHeadMenu(String menuItem) {
        SelenideElement mainMenu = $$(SUBMENU_ITEMS_BY).find(Condition.text(menuItem));
        mainMenu.click();
        log.info("Click on '{}' sub menu item", menuItem);
        return new DropDownMainMenu(mainMenu.$(DROPDOWN_MAIN_MENU_RELATIVE_BY));
    }

    @Step
    public MainPage inputInSearch(String value) {
        $(SEARCH_INPUT_BY).setValue(value);
        log.info("Input in search {}", value);
        return this;
    }

    @Step
    public SearchResultPage search(String value) {
        inputInSearch(value);
        $(SEARCH_INPUT_BY).press(Keys.ENTER);
        return Pages.getSearchResultPage();
    }

    @Step
    public MainPage checkSearchDropDownPosition() {
        log.info("Check search drop down content is displayed right under the search header");
        var search = $(SEARCH_INPUT_BY).shouldBe(Condition.visible);
        var dropDown = $(SEARCH_DROP_DOWN_BY).shouldBe(Condition.visible);
        Assert.assertTrue(ElementsPositionUtils
                .isElementRightUnder(search, dropDown));
        return this;
    }
}
