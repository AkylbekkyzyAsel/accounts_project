package com.example.accounts.service.impl;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.Transaction;
import com.example.accounts.repository.TransactionRepository;
import com.example.accounts.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions(Account account){
        System.out.println(transactionRepository.getAllBySenderOrReceiverOrderByDateDesc(account, account));
        return transactionRepository.getAllBySenderOrReceiverOrderByDateDesc(account, account);
    }

    public Transaction getTransactionById(int id) {
        return transactionRepository.getTransactionById(id);
    }
}
