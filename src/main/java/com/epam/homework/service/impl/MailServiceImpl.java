package com.epam.homework.service.impl;

import com.epam.homework.product.beans.Message;
import com.epam.homework.product.utility.exception.MessageSentException;
import com.epam.homework.service.ifaces.MailService;
import com.epam.homework.product.pages.ComposePage;
import com.epam.homework.product.pages.MainPage;
import com.epam.homework.product.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;
    private ComposePage composePage;
    private MainPage mainPage;
    private WebDriver driver;

    public MailServiceImpl() {
        this.composePage = new ComposePage();
        driver = WebDriverFactory.getInstance();
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Override
    public void sendMessage(Message message) throws MessageSentException {
        mainPage.clickCompose();
        composePage.typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
        if (isAlertPresents()) {
            driver.switchTo().alert().accept();
        }
    }

    @Override
    public boolean isMessageSent(Message message) {
        return !isAlertPresents() && isMessageInInbox(message) && isMessageInSent(message);
    }

    @Override
    public void putInDraft(Message message) {
        mainPage.clickCompose().clickSave();
    }

    @Override
    public void deleteMessage(Message message) {
        mainPage.getMessage(INDEX_1);
        mainPage.markMessage(INDEX_1);
        mainPage.clickDelete();
    }

    @Override
    public boolean isAlertPresents() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (MessageSentException e) {
            return false;
        }
    }

    @Override
    public boolean isMessageInTrash(Message message) {
        mainPage.clickTrash();
        return isLastMessageTarget(message);
    }

    private boolean isMessageInInbox(Message message) {
        mainPage.clickInbox();
        return isLastMessageTarget(message);
    }

    private boolean isMessageInSent(Message message) {
        mainPage.clickSent();
        return isLastMessageTarget(message);
    }

    private boolean isLastMessageTarget(Message message) {
        Message target = mainPage.getMessage(INDEX_1);
        return message.getEmail().equals(target.getEmail()) && message.getSubject().equals(target.getSubject());
    }
}
