package com.example.accounts.service;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.User;

public interface UserService {
    User getByUsername(String username);
    User getById(int id);

    boolean createUser(String username, String password);

    int checkUserAccount(Account account);
}
