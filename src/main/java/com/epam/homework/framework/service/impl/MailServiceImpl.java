package com.epam.homework.framework.service.impl;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.bean.MessageWithAttach;
import com.epam.homework.product.utility.builder.MessageBuilder;
import com.epam.homework.product.utility.constant.Constants;
import com.epam.homework.framework.service.exception.MessageSentException;
import com.epam.homework.framework.service.iface.MailService;
import com.epam.homework.product.page.ComposePage;
import com.epam.homework.product.page.MainPage;
import com.epam.homework.framework.logging.Log;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MailServiceImpl implements MailService {
    private static final int INDEX_1 = 1;
    private static final String EMPTY_SUBJECT = "<Без темы>";

    @Override
    public void sendMessage(Message message) {
        Log.info("Sending message.\nEmail: " + message.getEmail() + ". Subject: " + message.getSubject());
        MainPage mainPage = new MainPage();
        mainPage.clickCompose();
        doSend(message);
        if (message.getBody().equals(EMPTY)) {
            new ComposePage().handleEmptyMailSendConfirmPopUp();
        }
    }

    @Override
    public void sendMessage(MessageWithAttach message) {
        Log.info("Sending message with attach.\nEmail: " + message.getEmail() + ". Subject: "
                + message.getSubject() + Arrays.toString(message.getAttaches().toArray()));
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
        Log.info("Sending message.\nEmail: " + message.getEmail() + ". Subject: "
                + message.getSubject());
        new ComposePage().typeEmail(message.getEmail())
                .typeSubject(message.getSubject())
                .typeBody(message.getBody())
                .sendMessage();
    }

    @Override
    public void sendIncorrectMessage(Message message) throws MessageSentException {
        Log.info("Sending incorrect message.\nEmail: " + message.getEmail() + " Subject: " + message.getSubject());
        MainPage mainPage = new MainPage();
        ComposePage page = mainPage.clickCompose();
        doSend(message);
        String text = page.handleIncorrectMessageAlert();
        throw new MessageSentException(text);
    }

    @Override
    public boolean isMessageSent(Message message) {
        Log.info("Check was message sent.\nEmail: " + message.getEmail() + " Subject: " + message.getSubject());
        return isMessageInInbox(message) && isMessageInSent(message);
    }

    public void createAndSaveEmptyMessage() {
        Log.info("Create empty message and save it.");
        MainPage mainPage = new MainPage();
        mainPage.clickCompose();
        new ComposePage().clickSave();
    }

    public void deleteLastMessageFromDraft() {
        Log.info("Delete last message from Draft folder");
        MainPage mainPage = new MainPage();
        mainPage.clickDraft();
        mainPage.markMessage(INDEX_1);
        mainPage.clickDelete();
    }

    @Override
    public boolean isAllFilesAttached(List<Path> attaches, String subject) {
        Log.info("Check are all files attached");
        MainPage mainPage = new MainPage();
        mainPage.clickInbox();
        mainPage.getMessageBySubject(subject);
        return isLastMessageHasTargetAttaches(attaches);
    }

    public boolean isMessageInTrash(Message message) {
        Log.info("Check is message located in Trash folder. \nEmail: "
                + message.getEmail() + " Subject: " + message.getSubject());
        new MainPage().clickTrash();
        return isMessageInListOfPage(message);
    }

    private boolean isMessageInInbox(Message message) {
        Log.info("Check is message located in Inbox. \nEmail: "
                + message.getEmail() + " Subject: " + message.getSubject());
        new MainPage().clickInbox();
        return isMessageInListOfPage(message);
    }

    private boolean isMessageInSent(Message message) {
        Log.info("Check is message located in Sent.\nEmail: "
                + message.getEmail() + " Subject: " + message.getSubject());
        new MainPage().clickSent();
        return isMessageInListOfPage(message);
    }

    private boolean isMessageInListOfPage(Message message) {
        Log.info("Check is message located in list of messages on the page.\nEmail: "
                + message.getEmail() + ". Subject: "
                + message.getSubject());
        Message target = new MainPage().getMessageBySubject(message.getSubject());
        boolean emailEquality = message.getEmail().equals(target.getEmail())
                || (message.getEmail().equals(EMPTY) && target.getEmail().equals(Constants.EMAIL_LOGIN));
        boolean subjectEquality = message.getSubject().equals(target.getSubject());
        boolean emptySubjectEquality = message.getSubject().equals(EMPTY)
                && target.getSubject().equals(EMPTY_SUBJECT);
        return emailEquality && (subjectEquality || emptySubjectEquality);
    }

    private boolean isLastMessageHasTargetAttaches(List<Path> targets) {
        Log.info("Check last sending message for attach files list.\n" + Arrays.toString(targets.toArray()));
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
