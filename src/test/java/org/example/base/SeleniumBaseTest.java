package org.example.base;

import com.codeborne.selenide.WebDriverRunner;
import org.example.utils.SetupDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class SeleniumBaseTest {
    protected static final int DEFAULT_WIDTH = 1920;
    protected static final int DEFAULT_HEIGHT = 1080;

    @BeforeMethod
    protected void setupDriverBeforeMethod() {
        SetupDriver.initDriver(SetupDriver.getDefaultCapabilities());
        setupDriverWindowSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    protected void setupDriverWindowSize(int width, int height) {
        WebDriverRunner.getWebDriver().manage().window().setPosition(new Point(0, 0));
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(width, height));
    }

    @AfterMethod
    protected void logoutAfterMethod() {
        WebDriverRunner.getWebDriver().quit();
    }
}
