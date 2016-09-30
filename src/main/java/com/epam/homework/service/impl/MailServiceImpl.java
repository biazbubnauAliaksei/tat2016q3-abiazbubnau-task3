package com.epam.homework.service.impl;

import com.epam.homework.beans.Message;
import com.epam.homework.service.ifaces.MailService;
import com.epam.homework.ui.pages.ComposePage;
import com.epam.homework.ui.pages.MainPage;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;
    ComposePage composePage;
    MainPage mainPage;
    WebDriver driver;

    public MailServiceImpl() {
        this.composePage = new ComposePage();
        driver = WebDriverFactory.getInstance();
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Override
    public void sendMessage(Message message) {
        mainPage.clickCompose();
        composePage.typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
    }

    @Override
    public boolean isMessageSent(Message message) {
        return isMessageInInbox(message);
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
