package com.epam.homework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.exception.MessageSentException;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.product.pages.ComposePage;
import com.epam.homework.product.pages.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;

    @Override
    public void sendMessage(Message message) {
        new MainPage().clickCompose();
        new ComposePage().typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
        if (message.getBody().equals(Constants.EMPTY)) {
            Alert alert = (new WebDriverWait(Browser.getBrowser().getWrappedDriver(),
                    Browser.ELEMENT_WAIT_TIMEOUT_SECONDS))
                    .until(alertIsPresent());
            alert.accept();
        }
    }

    @Override
    public void sendIncorrectMessage(Message message) throws MessageSentException {
        new MainPage().clickCompose();
        sendMessage(message);
        Alert alert = Browser.getBrowser().getWrappedDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        throw new MessageSentException(text);
    }

    @Override
    public boolean isMessageSent(Message message) {
        return isMessageInSent(message) && isMessageInInbox(message);
    }

    @Override
    public void putInDraft(Message message) {
        new MainPage().clickCompose().clickSave();
    }

    @Override
    public void deleteMessage(Message message) {
        MainPage mainPage = new MainPage();
        mainPage.getMessage(INDEX_1);
        mainPage.markMessage(INDEX_1);
        mainPage.clickDelete();
    }

    @Override
    public boolean isMessageInTrash(Message message) {
        new MainPage().clickTrash();
        return isLastMessageTarget(message);
    }

    private boolean isMessageInInbox(Message message) {
        new MainPage().clickInbox();
        return isLastMessageTarget(message);
    }

    private boolean isMessageInSent(Message message) {
        new MainPage().clickSent();
        return isLastMessageTarget(message);
    }

    private boolean isLastMessageTarget(Message message) {
        Message target = new MainPage().getMessage(INDEX_1);
        return message.getEmail().equals(target.getEmail()) && message.getSubject().equals(target.getSubject());
    }
}
