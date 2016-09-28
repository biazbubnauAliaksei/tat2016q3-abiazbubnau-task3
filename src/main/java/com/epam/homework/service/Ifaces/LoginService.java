package com.epam.homework.service.ifaces;

public interface LoginService {

    boolean isLoginSuccess();
    void login(String login, String pass);
}
