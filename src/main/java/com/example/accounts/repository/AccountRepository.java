package com.example.accounts.repository;

import com.example.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByNumber(String accountNumber);



    @Modifying
    @Transactional
    @Query("update Account a set a.moneyAmount = ?1 where a.number = ?2")
    void setUserFundsByAccountNumber(double sum, String account_number);

}
