package org.example.pages;

import java.lang.reflect.InvocationTargetException;

import static com.codeborne.selenide.Selenide.open;

public class Pages {

    public static MainPage navigateToMainPage() {
        open(getMainPageUrl());
        return getPageObject(MainPage.class);
    }

    public static SearchResultPage getSearchResultPage() {
        return getPageObject(SearchResultPage.class);
    }

    public static EducationPage getEducationPage() {
        return getPageObject(EducationPage.class);
    }

    public static String getMainPageUrl() {
        return "https://www.wiley.com/en-us";
    }

    public static <E extends BasePage> E getPageObject(final Class<E> pageClass) {
        try {
            return pageClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
