package pl.maciejowsky.banksystem.model;

import pl.maciejowsky.banksystem.enums.DepositStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class Deposit {
    private int id;
    private double interestRate;
    private int depositTime;
    private BigDecimal depositAmount;
    private Instant startDate;
    private int userId;
    private DepositStatus depositStatus;

    private BigDecimal moneyEarned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(int depositTime) {
        this.depositTime = depositTime;
    }


    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DepositStatus getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(DepositStatus depositStatus) {
        this.depositStatus = depositStatus;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(BigDecimal moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public Deposit() {
    }

    public Deposit(int id, double interestRate, int depositTime, BigDecimal depositAmount, Instant startDate, int userId, DepositStatus depositStatus, BigDecimal moneyEarned) {
        this.id = id;
        this.interestRate = interestRate;
        this.depositTime = depositTime;
        this.depositAmount = depositAmount;
        this.startDate = startDate;
        this.userId = userId;
        this.depositStatus = depositStatus;
        this.moneyEarned = moneyEarned;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", interestRate=" + interestRate +
                ", depositTime=" + depositTime +
                ", depositAmount=" + depositAmount +
                ", startDate=" + startDate +
                ", userId=" + userId +
                ", depositStatus=" + depositStatus +
                ", moneyEarned=" + moneyEarned +
                '}';
    }
}
