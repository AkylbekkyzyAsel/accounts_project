package com.example.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int id;
    private double moneyAmount;
    private String currency;
    @Column(name = "number")
    private String number;


    public Account(double moneyAmount, String currency, String number) {
        this.moneyAmount = moneyAmount;
        this.currency = currency;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }
}
