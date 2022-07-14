package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.Transfer;

import java.math.BigDecimal;

public interface TransferDAO {
    boolean checkIfEnoughFunds(String accountNumber, BigDecimal funds, BigDecimal fee);
    boolean checkIfAccountExist(String accountNumber);

    void makeTransferFromFirstAccount(Transfer transfer);

    void receiveTransferFromSecondAccount(Transfer transfer);
}
