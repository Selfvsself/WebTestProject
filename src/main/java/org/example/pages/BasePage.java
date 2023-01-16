package org.example.pages;

public abstract class BasePage<P extends BasePage<P>> {

    public abstract P waitPageLoaded();
}
