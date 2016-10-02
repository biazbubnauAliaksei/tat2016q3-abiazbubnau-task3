package com.epam.homework.product.pages;

import com.epam.homework.product.beans.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractBasePage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");
    private static final By NEW_MAIL_BUTTON_LOCATOR =
            By.xpath("//div[@id='b-toolbar__left']//a[@data-name='compose']/span");
    private static final By SENT_LINK_LOCATOR =
            By.xpath("//div[@data-mnemo='nav-folders']//a[@href='/messages/sent/']");
    private static final String CONCRETE_MESSAGE_LOCATOR_PATTERN =
            ".//div[contains(@class,'b-datalist__body')]/div[%s]//a";
    private static final By INBOX_LINK_LOCATOR = By.xpath("//span[contains(@class, 'b-nav__item__text b-nav__item__text_unread')]");
    private static final By DRAFT_LINK_LOCATOR =
            By.xpath("//div[@data-mnemo='nav-folders']//a[@href='/messages/drafts/']");
    private static final By TRASH_LINK_LOCATOR =
            By.xpath("//div[@data-mnemo='nav-folders']//a[@href='/messages/trash/']");
    private static final By MAIL_SUBJECT_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__head__subj__text')]");
    private static final By MAIL_EMAIL_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__head__addrs__from')]/span");
    private static final By MAIL_TEXT_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__body')]//div[@id]");
    private static final String EMAIL_ATTR = "data-contact-informer-email";
    private static final By DELETE_EMAIL_LOCATOR = By.xpath("//div[@id='b-toolbar__right']//div[@data-name='remove']");
    private static final String CONCRETE_MAIL_CHECKBOX_LOCATOR_PATTERN =
            "//div[contains(@class, 'b-datalist__body')]/div[%s]//div[contains(@class, 'b-checkbox__box')]";

    public MainPage() {
    }

    public String getUsernameTitle() {
        String message = browser.findElement(USERNAME_LOCATOR).getText().trim();
        return message;
    }

    public ComposePage clickCompose() {
        browser.clickElement(NEW_MAIL_BUTTON_LOCATOR);
        return PageFactory.initElements(browser.getWrappedDriver(), ComposePage.class);
    }

    public MainPage clickSent() {
        browser.waitForElementIsPresent(SENT_LINK_LOCATOR);
        browser.clickElement(SENT_LINK_LOCATOR);
        return this;
    }

    public MainPage clickInbox() {
        browser.waitForElementIsPresent(INBOX_LINK_LOCATOR);
        browser.clickElement(INBOX_LINK_LOCATOR);
        return this;
    }

    public MainPage clickTrash() {
        browser.clickElement(TRASH_LINK_LOCATOR);
        return this;
    }

    public MainPage clickDraft() {
        browser.clickElement(DRAFT_LINK_LOCATOR);
        return this;
    }

    public MainPage clickDelete() {
        browser.clickElement(DELETE_EMAIL_LOCATOR);
        return this;
    }

    public void markMessage(int index) {
        By locator = makeMessageLocator(CONCRETE_MAIL_CHECKBOX_LOCATOR_PATTERN, index);
        browser.clickElement(locator);
    }

    public Message getMessage(int index) {
        By locator = makeMessageLocator(CONCRETE_MESSAGE_LOCATOR_PATTERN, index);
        browser.clickElement(locator);
        String email = browser.findElement(MAIL_EMAIL_LOCATOR).getAttribute(EMAIL_ATTR);
        String subject = browser.findElement(MAIL_SUBJECT_LOCATOR).getText();
        String body = browser.findElement(MAIL_TEXT_LOCATOR).getText();
        return new Message(email, subject, body);
    }

    private By makeMessageLocator(String pattern, int index) {
        String target = String.format(pattern, index);
        By locator = By.xpath(target);
        return locator;
    }
}
