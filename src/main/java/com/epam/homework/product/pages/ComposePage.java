package com.epam.homework.product.pages;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.framework.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ComposePage {
    private static final By FIELD_ADDRESS_LOCATOR = By.xpath(".//textarea[@data-original-name='To']");
    private static final By FIELD_SUBJECT_LOCATOR = By.xpath(".//input[@name='Subject']");
    private static final By FIELD_TEXT_LOCATOR = By.xpath(".//*[@id='tinymce']");
    private static final By FRAME_TEXT_LOCATOR = By.xpath("//iframe[contains(@id ,'composeEditor_ifr')]");
    private static final By SEND_EMAIL_LOCATOR = By.xpath("//*[@id='b-toolbar__right']//div[@data-name='send']/span");
    private static final By SAVE_EMAIL_LOCATOR = By.xpath("//div[@data-name='saveDraft']/span");

    private Browser browser;

    public ComposePage() {
        browser = Browser.getBrowser();
    }

    public ComposePage typeEmail(String email) {
        new Element(FIELD_ADDRESS_LOCATOR).typeValue(email);
        return this;
    }

    public ComposePage typeSubject(String subject) {
        new Element(FIELD_SUBJECT_LOCATOR).typeValue(subject);
        return this;
    }

    public ComposePage typeBody(String content) {
        browser.getWrappedDriver().switchTo().frame(new Element(FRAME_TEXT_LOCATOR).getWrappedWebElement());
        new Element(FIELD_TEXT_LOCATOR).typeValue(content);
        browser.getWrappedDriver().switchTo().defaultContent();
        return this;
    }

    public MainPage sendMessage() {
        new Element(SEND_EMAIL_LOCATOR).click();
        return PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }

    public MainPage clickSave() {
        new Element(SAVE_EMAIL_LOCATOR).click();
        return PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }
}
