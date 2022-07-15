package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.TransferDAO;
import pl.maciejowsky.banksystem.model.Transfer;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

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

    public void makePassableSpecificTransfer(Transfer transfer) {
        Timer timer = new Timer();
        //Taking away money from first account
        transferDAO.makeTransferFromFirstAccount(transfer);
        //Determinating sending and receiving dates
        int delayInSec = transfer.getTransferType().getTimeOfSendingInSec();
        transfer.setSendAt(Instant.now());
        transfer.setReceiveAt(transfer.getSendAt().plusMillis(delayInSec));
        //Saving to history table
        transferDAO.saveTransferToHistory(transfer);
        //Receiving money from first to second account
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("AFTER" + delayInSec / 100 + ",seconds i send " + transfer.getAmount());
                transferDAO.receiveTransferFromSecondAccount(transfer);
                timer.cancel();
            }
        }, delayInSec);


    }

}
