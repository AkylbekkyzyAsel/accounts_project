package com.example.accounts.repository;

import com.example.accounts.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthRepository extends JpaRepository<Session, Integer> {
    Session getSessionByToken(int token);


    @Modifying
    @Transactional
    @Query("update Session s set s.status = false where s.token = ?1")
    void deactivateToken(int token);
}
