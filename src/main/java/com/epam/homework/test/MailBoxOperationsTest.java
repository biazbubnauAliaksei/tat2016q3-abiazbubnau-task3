package com.epam.homework.test;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.utility.factory.MessageFactory;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class MailBoxOperationsTest extends BaseLoginTest {
    private MailServiceImpl service;
    private Message message;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        service = new MailServiceImpl();
    }

    @Test(description = "Message could be puts in folder 'Trash'.")
    public void messagePutsInTrash() {
        message = MessageFactory.createEmptyMessage();
        service.clickDraft();
        service.deleteFromDraft();
        assertTrue("Message should be in trash.", service.isMessageInTrash(message));
    }

    @Test(dependsOnMethods = "messagePutsInTrash",
            description = "Message should be desappeared from 'Trash' folder")
    public void deleteMessageFromTrash() {
        service.deleteFromDraft();
        assertFalse("Message could not be in trash folder", service.isMessageInTrash(message));
    }
}
