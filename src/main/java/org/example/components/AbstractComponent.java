package org.example.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public abstract class AbstractComponent<P extends AbstractComponent<P>> {
    private final SelenideElement mainElement;

    public AbstractComponent(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

    protected SelenideElement getMainElement() {
        return mainElement;
    }

    protected SelenideElement $(By by) {
        return getMainElement().$(by);
    }

    protected ElementsCollection $$(By by) {
        return getMainElement().$$(by);
    }
}
