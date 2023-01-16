package org.example.utils;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementsPositionUtils {
    private static final Logger log = LoggerFactory.getLogger(ElementsPositionUtils.class);

    public static boolean isElementRightUnder(SelenideElement upElement, SelenideElement downElement) {
        int x1 = upElement.getLocation().getX();
        int y1 = upElement.getLocation().getY();
        int height1 = upElement.getRect().getHeight();
        y1 = y1 + height1;

        int x2 = downElement.getLocation().getX();
        int y2 = downElement.getLocation().getY();

        log.info("Checked the element [{}, {}] is right under second element [{}, {}]", x1, y1, x2, y2);
        return (x1 == x2) && (y1 == y2);
    }
}
