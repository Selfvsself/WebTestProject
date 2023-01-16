package org.example.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.Browser.CHROME;
import static org.openqa.selenium.remote.Browser.FIREFOX;

public class SetupDriver {

    public static WebDriver initDriver(MutableCapabilities capabilities) {
        WebDriver webDriver = createDriver(capabilities);
        WebDriverRunner.setWebDriver(webDriver);

        Configuration.timeout = Integer.parseInt(
                SeleniumProperties.getProperty("timeout.global")) * 1000L;
        Configuration.pageLoadTimeout = Integer.parseInt(
                SeleniumProperties.getProperty("timeout.pageLoad")) * 1000L;
        return webDriver;
    }

    public static WebDriver createDriver(MutableCapabilities capabilities) {
        if (CHROME.is(capabilities)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(new ChromeOptions().merge(capabilities));
        } else if (FIREFOX.is(capabilities)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(new FirefoxOptions().merge(capabilities));
        } else {
            throw new RuntimeException("Browser not supported: " + capabilities.getBrowserName());
        }
    }

    public static MutableCapabilities getDefaultCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", SeleniumProperties.getProperty("selenium.driver"));
        return caps;
    }
}
