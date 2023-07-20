package com.example.accounts.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TransferDto {
    @NotEmpty(message = "Account number should not be empty")
    @Size(min = 5, message = "Name should be more than 5 characters")
    private String  accountNumber;

    @NotEmpty(message = "Sum should not be empty")
    @Min(5)
    private int sum;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
