package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.BankConfig;
import pl.maciejowsky.banksystem.model.Deposit;

import java.math.BigDecimal;
import java.util.List;

public interface EarningDAO {
    BankConfig getDepositInformation();

    int createDepositAndReturnId(Deposit deposit);

    void changeStatusToReady(int depositId);

    void changeStatusToEndAndSetMinutesWaited(int time,int userId);

    void changeDepositStatusToCancel(int depositId);

    void takeMoneyFromAccount(int userId, BigDecimal moneyToTransfer);

    BigDecimal calculateEarnedMoney(int userId);

    boolean checkIfIsReady(int userId);

    Deposit getActiveDeposit(int userId);

    Deposit getDepositByDepositId(int depositId);

    //also calculate earned money and fill in money_earned column to make further calculations
    void addMoneyToAccount(int userId, BigDecimal moneyToTransfer);

    void setEarnedMoney(int depositId, BigDecimal moneyEarned);

    List<Deposit> getHistoryForUser(int userId);
}
