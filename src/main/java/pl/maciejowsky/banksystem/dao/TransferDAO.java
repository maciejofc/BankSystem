package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDAO {
    boolean checkIfEnoughFunds(String accountNumber, BigDecimal funds, BigDecimal fee);
    boolean checkIfAccountExist(String accountNumber);

    void makeTransferFromFirstAccount(Transfer transfer);

    void receiveTransferFromSecondAccount(Transfer transfer);

    void saveTransferToHistory(Transfer transfer,int senderId,int receiverId);

    int findUserIdByAccountNumber(String accountNumber);

    List<Transfer> getHistoryOfSentForUser(int senderUserId);

    List<Transfer> getHistoryOfReceivedForUser(int receiverUserid);
}
