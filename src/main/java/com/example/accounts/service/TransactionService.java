package com.example.accounts.service;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.Transaction;


import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions(Account account);

    Transaction getTransactionById(int id);
}
