package com.epam.homework.service.impl;

import com.epam.homework.product.beans.Message;
import com.epam.homework.product.beans.MessageWithAttach;
import com.epam.homework.product.utility.builders.MessageBuilder;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.service.exception.MessageSentException;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.product.pages.ComposePage;
import com.epam.homework.product.pages.MainPage;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.nio.file.Path;
import java.util.List;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;

    @Override
    public void sendMessage(Message message) {
        MainPage mainPage = new MainPage();
        mainPage.clickCompose();
        doSend(message);
        if (message.getBody().equals(EMPTY)) {
            new ComposePage().handleEmptyMailSendConfirmPopUp();
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
        MainPage mainPage = new MainPage();
        ComposePage page = mainPage.clickCompose();
        doSend(message);
        String text = page.handleIncorrectMessageAlert();
        throw new MessageSentException(text);
    }

    @Override
    public boolean isMessageSent(Message message) {
        return isMessageInInbox(message);
    }

    public void putInDraft() {
        new MainPage().clickCompose().clickSave();
    }

    public void deleteFromDraft(Message message) {
        MainPage mainPage = new MainPage();
        mainPage.clickDraft();
        mainPage.markMessage(INDEX_1);
        mainPage.clickDelete();
    }

    @Override
    public boolean isAllFilesAttached(List<Path> attaches) {
        new MainPage().clickInbox().getMessage(INDEX_1);
        return isLastMessageHasTargetAttaches(attaches);
    }

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
        boolean emailEquality = message.getEmail().equals(target.getEmail());
        boolean subjectEquality = message.getSubject().equals(target.getSubject());
        boolean emptySubjectEquality = message.getSubject().equals(EMPTY)
                && target.getSubject().equals(Constants.EMPTY_SUBJECT);
        return emailEquality && (subjectEquality || emptySubjectEquality);
    }

    private boolean isLastMessageHasTargetAttaches(List<Path> targets) {
        List<String> attaches = new MainPage().getListOfAttaches();
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
