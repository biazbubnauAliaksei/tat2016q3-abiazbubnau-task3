package com.epam.homework.product.utility.builder;

import com.epam.homework.product.bean.User;
import com.epam.homework.product.utility.factory.UserFactory;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class UserBuilder {

    private User user = new User();

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public User build() {
        User person = UserFactory.createCorrectUser();
        if (user.getEmail().equals(EMPTY)) {
            email(person.getEmail());
        }
        if (user.getPassword().equals(EMPTY)) {
            password(person.getEmail());
        }
        return user;
    }
}
