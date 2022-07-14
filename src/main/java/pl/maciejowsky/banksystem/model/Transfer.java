package pl.maciejowsky.banksystem.model;

import pl.maciejowsky.banksystem.enums.TransferType;

import java.math.BigDecimal;

public class Transfer {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private TransferType transferType;

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

    public Transfer() {
    }

    public Transfer(String fromAccount, String toAccount, BigDecimal amount, TransferType transferType) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transferType = transferType;
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
