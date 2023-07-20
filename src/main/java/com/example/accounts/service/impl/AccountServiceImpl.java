package com.example.accounts.service.impl;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.Transaction;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.TransactionRepository;
import com.example.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Account getAccount(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }

    public void transfer(Account sender, Account receiver, double sum) {
        updateFunds(sender, sender.getMoneyAmount() - sum);
        updateFunds(receiver, receiver.getMoneyAmount() + sum);

        transactionRepository.save(new Transaction(
                sender,
                receiver,
                sum,
                sender.getCurrency(),
                "SUCCESS",
                Timestamp.valueOf(java.time.LocalDateTime.now()))
        );
    }

    public void updateFunds(Account account, double sum) {
        accountRepository.setUserFundsByAccountNumber(sum, account.getNumber());
    }


}
