package com.epam.homework.framework.element;

import com.epam.homework.framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element {
    private By by;

    public Element(By by) {
        this.by = by;
    }

    public By getBy() {
        return by;
    }

    public WebElement getWrappedWebElement() {
        return Browser.getBrowser().findElement(by);
    }

    public boolean isPresent() {
        return Browser.getBrowser().isElementPresent(by);
    }

    public boolean isVisible() {
        return Browser.getBrowser().isElementVisible(by);
    }

    public void waitForAppear() {
        Browser.getBrowser().waitForElementIsVisible(by);
    }

    public void waitForClickable() {
    }

    public String getText() {
        return getWrappedWebElement().getText();
    }

    public void click() {
        getWrappedWebElement().click();
    }

    public void typeValue(String value) {
        WebElement element = getWrappedWebElement();
        element.clear();
        element.sendKeys(value);
    }

    public void waitAndClick() {
        waitForAppear();
        click();
    }
}
