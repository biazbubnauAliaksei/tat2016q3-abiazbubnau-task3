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

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;

    @Override
    public void sendMessage(Message message) {
        new MainPage().clickCompose();
        doSend(message);
        if (message.getBody().equals(Constants.EMPTY)) {
            Alert alert = (new WebDriverWait(Browser.getBrowser().getWrappedDriver(),
                    Browser.ELEMENT_WAIT_TIMEOUT_SECONDS))
                    .until(alertIsPresent());
            alert.accept();
        }
    }

    @Override
    public void sendMessage(Message message, List<String> attaches) {
        ComposePage page = new MainPage().clickCompose();
        for (String path : attaches) {
            String absolutePath = new File(path).getAbsolutePath();
            page.attachFile(absolutePath);
        }
        doSend(message);
    }

    private void doSend(Message message) {
        new ComposePage().typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
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
    public void putInDraft() {
        new MainPage().clickCompose().clickSave();
    }

    @Override
    public void deleteMessage(Message message) {
        MainPage mainPage = new MainPage();
        mainPage.markMessage(INDEX_1);
        mainPage.clickDelete();
    }

    @Override
    public boolean isAllFilesAttached(List<String> attaches) {
        new MainPage().getMessage(INDEX_1);
        return isLastMessageHasTargetAttaches(attaches);
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

    private boolean isLastMessageHasTargetAttaches(List<String> targets) {
        List<String> attaches = new MainPage().getListOfAttaches(INDEX_1);
        boolean result = false;
        for (String attach : attaches) {
            for (String target : targets) {
                if (target.endsWith(attach)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
