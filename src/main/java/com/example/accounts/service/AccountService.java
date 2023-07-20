package com.example.accounts.service;

import com.example.accounts.entity.Account;

public interface AccountService {
    Account getAccount(String accountNumber);

    void transfer(Account sender, Account receiver, double sum);

    void updateFunds(Account account, double sum);


}
