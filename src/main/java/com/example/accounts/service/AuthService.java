package com.example.accounts.service;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.User;

public interface AuthService {
    int signIn(String username, String password);

    void createSession(int token, User user);
    boolean checkToken(int token);
    User getUserByToken(int token);
    Account getAccountByToken(int token);
    void deactivateToken(int token);
    boolean isAccountEligible(Account account);
}
