package com.epam.homework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.beans.MessageWithAttach;
import com.epam.homework.product.utility.builders.MessageBuilder;
import com.epam.homework.product.utility.exception.MessageSentException;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.product.pages.ComposePage;
import com.epam.homework.product.pages.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.nio.file.Path;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;

    @Override
    public void sendMessage(Message message) {
        new MainPage().clickCompose();
        doSend(message);
        if (message.getBody().equals(EMPTY)) {
            Alert alert = (new WebDriverWait(Browser.getBrowser().getWrappedDriver(),
                    Browser.ELEMENT_WAIT_TIMEOUT_SECONDS))
                    .until(alertIsPresent());
            alert.accept();
        }
    }

    @Override
    public void sendMessage(MessageWithAttach message) {
        ComposePage page = new MainPage().clickCompose();
        for (Path path : message.getAttaches()) {
            page.attachFile(path.toAbsolutePath());
        }
        Message coreMessage = new MessageBuilder()
                .email(message.getEmail())
                .subject(message.getSubject())
                .body(message.getBody())
                .build();
        doSend(coreMessage);
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
        return isMessageInInbox(message) && isMessageInSent(message);
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
    public boolean isAllFilesAttached(List<Path> attaches) {
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

    private boolean isLastMessageHasTargetAttaches(List<Path> targets) {
        List<String> attaches = new MainPage().getListOfAttaches(INDEX_1);
        boolean result = false;
        for (String attach : attaches) {
            for (Path target : targets) {
                if (target.endsWith(attach)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
