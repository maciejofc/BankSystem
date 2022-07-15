package pl.maciejowsky.banksystem.model;

import pl.maciejowsky.banksystem.enums.TransferType;

import java.math.BigDecimal;
import java.time.Instant;

public class Transfer {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private TransferType transferType;

    private Instant sendAt;

    private Instant receiveAt;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public Instant getSendAt() {
        return sendAt;
    }

    public void setSendAt(Instant sendAt) {
        this.sendAt = sendAt;
    }

    public Instant getReceiveAt() {
        return receiveAt;
    }

    public void setReceiveAt(Instant receiveAt) {
        this.receiveAt = receiveAt;
    }

    public Transfer() {
    }

    public Transfer(String fromAccount, String toAccount, BigDecimal amount, TransferType transferType, Instant sendAt, Instant receiveAt) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transferType = transferType;
        this.sendAt = sendAt;
        this.receiveAt = receiveAt;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", transferType=" + transferType +
                '}';
    }
}
