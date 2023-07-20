package com.example.accounts.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

//@Component
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Account sender;
    @OneToOne
    private Account receiver;
    private double sum;
    private String currency;
    private String status;
    private Timestamp date;


    public Transaction(Account sender, Account receiver, double sum, String currency, String status, Timestamp date) {
        this.sender = sender;
        this.receiver = receiver;
        this.sum = sum;
        this.currency = currency;
        this.status = status;
        this.date = date;

    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }


    public double getSum() {
        return sum;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getDate() {

        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(Timestamp date) {

        this.date = date;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}
