package com.epam.homework.service.iface;

import com.epam.homework.product.bean.User;

public interface LoginService {

    void login(User user);

    boolean isLoginSuccess();
}
