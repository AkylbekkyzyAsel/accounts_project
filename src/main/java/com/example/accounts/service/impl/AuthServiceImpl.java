package com.example.accounts.service.impl;

import com.example.accounts.entity.Account;
import com.example.accounts.entity.Session;
import com.example.accounts.entity.User;
import com.example.accounts.repository.AuthRepository;
import com.example.accounts.repository.UserRepository;
import com.example.accounts.service.AccountService;
import com.example.accounts.service.AuthService;
import com.example.accounts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private UserService userService;

    User getUser(String username, String password) {
        User user = userRepository.getByUsername(username);
        if (user != null && Objects.equals(password, user.getPassword()))
            return user;
        return null;
    }

    @Override
    public int signIn(String username, String password) {

        User user = getUser(username, password);
        if (user == null)
            return 0;

        int token = Objects.hash(username, password, java.time.LocalDateTime.now());
        return token;
    }

    @Override
    public void createSession(int token, User user) {
        authRepository.save(new Session(
                token,
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()),
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now().plusMinutes(15)),
                user,
                user.getAccount(),
                true
                ));
    }

    public boolean checkToken(int token) {
        Session session = authRepository.getSessionByToken(token);
        if (session != null && session.getExpiredAt().after(Timestamp.valueOf(java.time.LocalDateTime.now())) && session.isStatus())
            return true;
        return false;
    }

    @Override
    public User getUserByToken(int token) {
        return authRepository.getSessionByToken(token).getUser();
    }

    @Override
    public Account getAccountByToken(int token) {
        return authRepository.getSessionByToken(token).getAccount();
    }

    public boolean isAccountEligible(Account account) {
        int numberOfUsers = userService.checkUserAccount(account);
        System.out.println(numberOfUsers);
        return numberOfUsers > 1;
    }

    public void deactivateToken(int token) {
        authRepository.deactivateToken(token);
    }


}
