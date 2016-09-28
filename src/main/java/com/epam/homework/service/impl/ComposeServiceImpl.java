package com.epam.homework.service.impl;

import com.epam.homework.service.ifaces.ComposeService;
import com.epam.homework.ui.pages.ComposePage;

/**
 * Created by Al on 28.09.2016.
 */
public class ComposeServiceImpl implements ComposeService {
    ComposePage composePage;

    public ComposeServiceImpl(ComposePage composePage) {
        this.composePage = composePage;
    }

    @Override
    public void composeMessage(String email, String subject, String message) {
        composePage.typeEmail(email);
        composePage.typeSubject(subject);
        composePage.typeBody(message);
    }

    @Override
    public void sendMessage() {
        composePage.sendMessage();
    }

}
