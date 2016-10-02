package com.epam.homework.product.pages;

import com.epam.homework.framework.Browser;

public abstract class AbstractBasePage {
    protected Browser browser;

    protected AbstractBasePage() {
        this.browser = Browser.getBrowser();
    }
}
