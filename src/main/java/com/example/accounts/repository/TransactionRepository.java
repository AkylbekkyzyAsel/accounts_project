package com.example.accounts.repository;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllBySenderOrReceiverOrderByDateDesc(Account sender, Account receiver);

    Transaction getTransactionById(int id);

}
