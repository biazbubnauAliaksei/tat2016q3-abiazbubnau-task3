package com.epam.homework.service.impl;

import com.epam.homework.service.ifaces.SendMailService;
import com.epam.homework.ui.pages.ComposePage;
import com.epam.homework.ui.pages.MainPage;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SendMailServiceImpl implements SendMailService {

    private MainPage mainPage;
    private WebDriver driver;
    private ComposePage composePage;

    public SendMailServiceImpl(MainPage mainPage){
        driver = WebDriverFactory.getInstance();
        this.mainPage = mainPage;
    }

    public void clickCompose(){
        mainPage.clickCompose();
        composePage = PageFactory.initElements(driver, ComposePage.class);
    }

    public MainPage sendEmail(String email, String subject, String content){
       return composePage.typeEmail(email)
                .typeSubject(subject)
                .typeBody(content)
                .sendMessage();
    }

}
