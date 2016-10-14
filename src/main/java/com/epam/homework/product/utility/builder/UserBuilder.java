package com.epam.homework.product.utility.builder;

import com.epam.homework.product.bean.User;

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
        return user;
    }
}
