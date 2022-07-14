package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.TransferDAO;
import pl.maciejowsky.banksystem.model.Transfer;

import java.math.BigDecimal;

@Service
public class TransferService {
    @Autowired
    private TransferDAO transferDAO;

    public String[] validateTransfer(Transfer transfer) {
        boolean isEnoughMoney = transferDAO.checkIfEnoughFunds(transfer.getFromAccount(), transfer.getAmount(), transfer.getTransferType().getFee());
        boolean doesAccountExist = transferDAO.checkIfAccountExist(transfer.getToAccount());

        String noAccount = "This account does not exists";
        String noMoney = "You have not enough money to make transfer";
        if (!isEnoughMoney && !doesAccountExist) {
            return new String[]{noAccount, noMoney};
        } else if (!isEnoughMoney) {
            return new String[]{"", noMoney};
        } else if (!doesAccountExist) {
            return new String[]{noAccount, ""};
        } else {
            return null;
        }

    }

    public void makePassableTransfer(Transfer transfer) {
        transferDAO.makeTransferFromFirstAccount(transfer);
        transferDAO.receiveTransferFromSecondAccount(transfer);
    }

}
