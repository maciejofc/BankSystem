package pl.maciejowsky.banksystem.model;

import pl.maciejowsky.banksystem.enums.SpecificAccount;

import java.math.BigDecimal;

public class Account {
    private int id;
    private SpecificAccount specificAccount;
    //interest rate -> 3 actual interest rate = 3% OR 0,03
    private double interestRate;
    private String AccountNumber;
    private boolean isActive;
    private BigDecimal balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpecificAccount getSpecificAccount() {
        return specificAccount;
    }

    public void setSpecificAccount(SpecificAccount specificAccount) {
        this.specificAccount = specificAccount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(int id, SpecificAccount specificAccount, double interestRate, String accountNumber, boolean isActive, BigDecimal balance) {
        this.id = id;
        this.specificAccount = specificAccount;
        this.interestRate = interestRate;
        AccountNumber = accountNumber;
        this.isActive = isActive;
        this.balance = balance;
    }

    public Account(SpecificAccount specificAccount, double interestRate, String accountNumber, boolean isActive, BigDecimal balance) {
        this.specificAccount = specificAccount;
        this.interestRate = interestRate;
        AccountNumber = accountNumber;
        this.isActive = isActive;
        this.balance = balance;
    }
}
