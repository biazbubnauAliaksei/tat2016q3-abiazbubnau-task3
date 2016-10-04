package com.epam.homework.product.pages;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.framework.element.Element;
import com.epam.homework.product.beans.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");
    private static final By NEW_MAIL_BUTTON_LOCATOR =
            By.xpath("//div[@id='b-toolbar__left']//a[@data-name='compose']/span");
    private static final By SENT_LINK_LOCATOR =
            By.xpath("//div[@data-mnemo='nav-folders']//a[@href='/messages/sent/']");
    private static final String CONCRETE_MESSAGE_LOCATOR_PATTERN =
            ".//div[contains(@class,'b-datalist__body')]/div[%s]//a";
    private static final By INBOX_LINK_LOCATOR =
            By.xpath("//span[contains(@class, 'b-nav__item__text b-nav__item__text_unread')]");
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
    private static final By FILENAME_CONTAINING_ELEMENTS_LOCATOR = By.xpath("//div[contains(@class, 'b-filename__spacer')]");

    private final Element userName = new Element(USERNAME_LOCATOR);
    private final Element newMailButton = new Element(NEW_MAIL_BUTTON_LOCATOR);
    private final Element sentLink = new Element(SENT_LINK_LOCATOR);
    private final Element inboxLink = new Element(INBOX_LINK_LOCATOR);
    private final Element trashLink = new Element(TRASH_LINK_LOCATOR);
    private final Element draftLink = new Element(DRAFT_LINK_LOCATOR);
    private final Element deleteLink = new Element(DELETE_EMAIL_LOCATOR);

    public String getUsernameTitle() {
        String message = userName.getText();
        return message;
    }

    public ComposePage clickCompose() {
        clickOnElement(newMailButton);
        return new ComposePage();
    }

    public MainPage clickSent() {
        clickOnElement(sentLink);
        return this;
    }

    public MainPage clickInbox() {
        clickOnElement(inboxLink);
        return this;
    }

    public MainPage clickTrash() {
        clickOnElement(trashLink);
        return this;
    }

    public MainPage clickDraft() {
        clickOnElement(draftLink);
        return this;
    }

    public MainPage clickDelete() {
        clickOnElement(deleteLink);
        return this;
    }

    public void markMessage(int index) {
        Element locator = new Element(makeMessageLocator(CONCRETE_MAIL_CHECKBOX_LOCATOR_PATTERN, index));
        clickOnElement(locator);
    }

    public Message getMessage(int index) {
        clickMessage(index);
        String email = new Element(MAIL_EMAIL_LOCATOR).getWrappedWebElement().getAttribute(EMAIL_ATTR);
        String subject = new Element(MAIL_SUBJECT_LOCATOR).getText();
        String body = new Element(MAIL_TEXT_LOCATOR).getText();
        return new Message(email, subject, body);
    }

    private By makeMessageLocator(String pattern, int index) {
        String target = String.format(pattern, index);
        By locator = By.xpath(target);
        return locator;
    }

    private void clickMessage(int index){
        Element locator = new Element(makeMessageLocator(CONCRETE_MESSAGE_LOCATOR_PATTERN, index));
        clickOnElement(locator);
    }

    public List<String> getListOfAttaches(int index){
        List<String> results = new ArrayList<>();
        clickMessage(index);
        List<WebElement> elements = Browser.getBrowser().findElements(FILENAME_CONTAINING_ELEMENTS_LOCATOR);
        for (WebElement element: elements){
            String text = element.getText();
            results.add(text);
        } return results;
    }

    private void clickOnElement(Element element) {
        element.waitAndClick();
    }
}
