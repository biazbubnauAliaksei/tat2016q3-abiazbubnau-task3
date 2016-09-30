package com.epam.homework.ui.pages;

import com.epam.homework.beans.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractBasePage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");
    private static final By NEW_MAIL_BUTTON_LOCATOR =
            By.xpath("//div[@id='b-toolbar__left']//a[@data-name='compose']/span");
    private static final By SENT_LINK_LOCATOR = By.xpath(".//div[@data-id='500000']/a");
    private static final String CONCRETE_MESSAGE_LOCATOR_PATTERN =
            ".//div[contains(@class,'b-datalist__body')]/div[%s]//a";
    private static final By INBOX_LINK_LOCATOR = By.xpath("//div[@data-id='0']/a");
    private static final By MAIL_SUBJECT_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__head__subj__text')]");
    private static final By MAIL_EMAIL_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__head__addrs__from')]/span");
    private static final By MAIL_TEXT_LOCATOR = By.xpath("//*[contains(@class, 'b-letter__body')]//div[@id]");
    private static final String EMAIL_ATTR = "data-contact-informer-email";

    public MainPage() {
        super();
    }

    public String getUsernameTitle() {
        String message = driver.findElement(USERNAME_LOCATOR).getText().trim();
        return message;
    }

    public ComposePage clickCompose() {
        driver.findElement(NEW_MAIL_BUTTON_LOCATOR).click();
        return PageFactory.initElements(driver, ComposePage.class);
    }

    public MainPage clickSent() {
        driver.findElement(SENT_LINK_LOCATOR).click();
        return PageFactory.initElements(driver, MainPage.class);
    }

    public MainPage clickInbox() {
        driver.findElement(INBOX_LINK_LOCATOR).click();
        return PageFactory.initElements(driver, MainPage.class);
    }

    public Message getMessage(int index) {
        String target = String.format(CONCRETE_MESSAGE_LOCATOR_PATTERN, index);
        By locator = By.xpath(target);
        waitForElement(locator).click();
        String email = driver.findElement(MAIL_EMAIL_LOCATOR).getAttribute(EMAIL_ATTR);
        String subject = driver.findElement(MAIL_SUBJECT_LOCATOR).getText();
        String body = driver.findElement(MAIL_TEXT_LOCATOR).getText();
        System.out.println("getMessage passed");
        return new Message(email, subject, body);
    }
}
