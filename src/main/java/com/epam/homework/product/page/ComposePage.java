package com.epam.homework.product.page;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.framework.element.Element;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import java.nio.file.Path;

public class ComposePage {
    private static final By FIELD_ADDRESS_LOCATOR = By.xpath(".//textarea[@data-original-name='To']");
    private static final By FIELD_SUBJECT_LOCATOR = By.xpath(".//input[@name='Subject']");
    private static final By FIELD_TEXT_LOCATOR = By.xpath(".//*[@id='tinymce']");
    private static final By FRAME_TEXT_LOCATOR = By.xpath("//iframe[contains(@id ,'composeEditor_ifr')]");
    private static final By SEND_EMAIL_LOCATOR = By.xpath("//div[@data-name='send']/span");
    private static final By SAVE_EMAIL_LOCATOR = By.xpath("//div[@data-name='saveDraft']/span");
    private static final By ATTACH_FILE_INPUT_LOCATOR = By.xpath("//input[@type='file']");
    private static final By ATTACH_FILENAMES_CONTAINING_TABLE_LOCATOR =
            By.xpath(".//div[contains(@class,'list upload')]//table");
    private static final By EMPTY_MAIL_SEND_CONFIRM_POPUP_BUTTON_LOCATOR =
            By.xpath(".//*[@id='MailRuConfirm']//div[contains(@class, 'is-compose-empty_in')]//button[@type='submit']");
    private static final By MESSAGE_SENT_TITLE_LOCATOR =
            By.xpath("//div[contains(@class, 'message-sent__title')]");

    private final Element fieldAddress = new Element(FIELD_ADDRESS_LOCATOR);
    private final Element fieldSubject = new Element(FIELD_SUBJECT_LOCATOR);
    private final Element fieldText = new Element(FIELD_TEXT_LOCATOR);
    private final Element sendMessageButton = new Element(SEND_EMAIL_LOCATOR);
    private final Element saveEmailButton = new Element(SAVE_EMAIL_LOCATOR);
    private final Element attachFileInput = new Element(ATTACH_FILE_INPUT_LOCATOR);
    private final Element attachFilenameContainingTable = new Element(ATTACH_FILENAMES_CONTAINING_TABLE_LOCATOR);
    private final Element emptyMailSendConfirmButton = new Element(EMPTY_MAIL_SEND_CONFIRM_POPUP_BUTTON_LOCATOR);
    private final Element messageSentTitle = new Element(MESSAGE_SENT_TITLE_LOCATOR);

    public ComposePage typeEmail(String email) {
        fieldAddress.typeValue(email);
        return this;
    }

    public ComposePage typeSubject(String subject) {
        fieldSubject.typeValue(subject);
        return this;
    }

    public ComposePage typeBody(String content) {
        Browser browser = Browser.getBrowser();
        browser.getWrappedDriver().switchTo().frame(new Element(FRAME_TEXT_LOCATOR).getWrappedWebElement());
        fieldText.typeValue(content);
        browser.getWrappedDriver().switchTo().defaultContent();
        return this;
    }

    public void sendMessage() {
        sendMessageButton.click();
        messageSentTitle.waitForAppear();
    }

    public MainPage clickSave() {
        saveEmailButton.waitAndClick();
        return new MainPage();
    }

    public ComposePage attachFile(Path path) {
        attachFileInput.typeValue(path.toString());
        attachFilenameContainingTable.waitForAppear();
        return this;
    }

    public String handleIncorrectMessageAlert() {
        Browser.getBrowser().waitForAlertIsPresent();
        Alert alert = Browser.getBrowser().getWrappedDriver().switchTo().alert();
        String message = alert.getText();
        alert.accept();
        Browser.getBrowser().getWrappedDriver().switchTo().defaultContent();
        return message;
    }

    public void handleEmptyMailSendConfirmPopUp() {
        emptyMailSendConfirmButton.waitAndClick();
    }
}
