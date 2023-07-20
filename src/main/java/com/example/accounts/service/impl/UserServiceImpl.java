package com.example.accounts.service.impl;


import com.example.accounts.entity.User;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.UserRepository;
import com.example.accounts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.accounts.entity.Account;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User getById(int id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public boolean createUser(String username, String password) {
        User user = userRepository.getByUsername(username);
        if (user != null) {
            return false;
        }
        userRepository.save(new User(
                username,
                password,
                accountRepository.save(new Account(
                    2000,
                    "KGS",
                    "12345"
                )),
                null,
                null)
        );
        return true;
    }

    public int checkUserAccount(Account account) {
        List<User> accountUsers = userRepository.findAllByAccount(account);
        System.out.println(accountUsers.size());
        return accountUsers.size();
    }

}
