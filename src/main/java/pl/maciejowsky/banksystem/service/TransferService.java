package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.AccountDAO;
import pl.maciejowsky.banksystem.dao.TransferDAO;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.Account;
import pl.maciejowsky.banksystem.model.Transfer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class TransferService {
    @Autowired
    private TransferDAO transferDAO;

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private UserDAO userDAO;

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
        //Saving to history table with recognizing which number is
        //associated with which user to save foreign keys to make easy select for history of transfer for given user(id)

        int senderId = transferDAO.findUserIdByAccountNumber(transfer.getFromAccount());
        int receiverId = transferDAO.findUserIdByAccountNumber(transfer.getToAccount());

        transferDAO.saveTransferToHistory(transfer,senderId,receiverId);
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

    public List<List<Transfer>> getTransferHistoryForUser(String email) {
        int id = userDAO.findUserByEmail(email).getId();
        List<Transfer> historyOfSent = transferDAO.getHistoryOfSentForUser(id);
        List<Transfer> historyOfReceived = transferDAO.getHistoryOfReceivedForUser(id);
        List<List<Transfer>> fullHistory = new ArrayList<>();
        fullHistory.add(historyOfSent);
        fullHistory.add(historyOfReceived);
        return fullHistory;
    }

}
