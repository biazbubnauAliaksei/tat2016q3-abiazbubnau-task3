package com.epam.homework.product.utility.factories;

import com.epam.homework.product.beans.User;
import com.epam.homework.product.utility.constants.Constants;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UserFactory {

    public static User createCorrectUser() {
        User user = new User();
        user.setEmail(Constants.EMAIL_LOGIN);
        user.setPassword(Constants.CORRECT_PASS);
        return user;
    }

    public static User createRandomUser() {
        User user = new User();
        user.setEmail(randomAlphabetic(Constants.CONTENT_INDEX));
        user.setPassword(randomAlphanumeric(Constants.CONTENT_INDEX));
        return user;
    }
}
