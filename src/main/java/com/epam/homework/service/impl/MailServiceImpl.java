package com.epam.homework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.product.pages.ComposePage;
import com.epam.homework.product.pages.MainPage;
import org.eclipse.jetty.io.RuntimeIOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;
    private ComposePage composePage;
    private MainPage mainPage;
    private Browser browser;

    public MailServiceImpl() {
        this.composePage = new ComposePage();
        browser = Browser.getBrowser();
        mainPage = PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }

    @Override
    public void sendMessage(Message message) {
        mainPage.clickCompose();
        composePage.typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
        if (message.getBody() == Constants.EMPTY) {
            Alert alert = (new WebDriverWait(browser.getWrappedDriver(), Browser.ELEMENT_WAIT_TIMEOUT_SECONDS))
                    .until(alertIsPresent());
            alert.accept();
        }
    }

    @Override
    public void sendIncorrectMessage(Message message) throws RuntimeException {
        mainPage.clickCompose();
        sendMessage(message);
        Alert alert = browser.getWrappedDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        throw new RuntimeIOException(text);
    }

    @Override
    public boolean isMessageSent(Message message) {
        return isMessageInSent(message) && isMessageInInbox(message);
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
