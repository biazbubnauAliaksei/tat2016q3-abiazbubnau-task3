package com.epam.homework.service.impl;

import com.epam.homework.beans.Message;
import com.epam.homework.service.ifaces.MailService;
import com.epam.homework.ui.pages.ComposePage;
import com.epam.homework.ui.pages.MainPage;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Al on 28.09.2016.
 */
public class MailServiceImpl implements MailService {
    ComposePage composePage;
    MainPage mainPage;
    WebDriver driver;

    public MailServiceImpl() {
        this.composePage = new ComposePage();
        driver = WebDriverFactory.getInstance();
    }

    private void clickCompose() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.clickCompose();
    }

    @Override
    public void sendMessage(Message message) {
        clickCompose();
        composePage.typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
    }

    @Override
    public boolean isMessageSent(Message message) {
        return false;
    }

    @Override
    public boolean isMessageRecieved(Message message) {
        return false;
    }

    public boolean isMessageInInbox(Message message) {

        return false;
    }
}
