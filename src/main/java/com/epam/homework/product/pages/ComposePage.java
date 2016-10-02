package com.epam.homework.product.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ComposePage extends AbstractBasePage {
    private static final By FIELD_ADDRESS_LOCATOR = By.xpath(".//textarea[@data-original-name='To']");
    private static final By FIELD_SUBJECT_LOCATOR = By.xpath(".//input[@name='Subject']");
    private static final By FIELD_TEXT_LOCATOR = By.xpath(".//*[@id='tinymce']");
    private static final By FRAME_TEXT_LOCATOR = By.xpath("//iframe[contains(@id ,'composeEditor_ifr')]");
    private static final By SEND_EMAIL_LOCATOR = By.xpath("//*[@id='b-toolbar__right']//div[@data-name='send']/span");
    private static final By SAVE_EMAIL_LOCATOR = By.xpath("//div[@data-name='saveDraft']/span");

    public ComposePage() {
    }

    public ComposePage typeEmail(String email) {
        browser.findElement(FIELD_ADDRESS_LOCATOR).sendKeys(email);
        return this;
    }

    public ComposePage typeSubject(String subject) {
        browser.findElement(FIELD_SUBJECT_LOCATOR).sendKeys(subject);
        return this;
    }

    public ComposePage typeBody(String content) {
        browser.getWrappedDriver().switchTo().frame(browser.findElement(FRAME_TEXT_LOCATOR));
        browser.findElement(FIELD_TEXT_LOCATOR).sendKeys(content);
        browser.getWrappedDriver().switchTo().defaultContent();
        return this;
    }

    public MainPage sendMessage() {
        browser.findElement(SEND_EMAIL_LOCATOR).click();
        return PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }

    public MainPage clickSave() {
        browser.findElement(SAVE_EMAIL_LOCATOR).click();
        return PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }
}
