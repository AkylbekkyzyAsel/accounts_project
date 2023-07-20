package com.example.accounts.repository;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);
    User findUserByUsername(String username);

    List<User> findAllByAccount(Account account);
}
