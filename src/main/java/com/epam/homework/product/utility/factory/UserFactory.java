package com.epam.homework.product.utility.factory;

import com.epam.homework.product.bean.User;
import com.epam.homework.product.utility.constant.Constants;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UserFactory {
    private static final String CORRECT_PASS = "pass000";

    public static User createCorrectUser() {
        User user = new User();
        user.setEmail(Constants.EMAIL_LOGIN);
        user.setPassword(CORRECT_PASS);
        return user;
    }

    public static User createRandomUser() {
        User user = new User();
        user.setEmail(randomAlphabetic(Constants.CONTENT_INDEX));
        user.setPassword(randomAlphanumeric(Constants.CONTENT_INDEX));
        return user;
    }
}
