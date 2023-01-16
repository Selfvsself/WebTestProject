package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SeleniumProperties {
    private final Properties properties = new Properties();
    private static SeleniumProperties INSTANCE = null;

    private SeleniumProperties() {
        try {
            properties.load(new FileInputStream("src/main/resources/selenium.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SeleniumProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SeleniumProperties();
        }
        return INSTANCE;
    }

    public static String getProperty(String key) {
        return getInstance().getProperties().getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
