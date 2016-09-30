package com.epam.homework.service.ifaces;

import com.epam.homework.product.beans.User;

public interface LoginService {

    void login(User user);

    boolean isLoginSuccess();
}
