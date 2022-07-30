package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.EarningDAO;
import pl.maciejowsky.banksystem.enums.DepositStatus;
import pl.maciejowsky.banksystem.model.BankConfig;
import pl.maciejowsky.banksystem.model.Deposit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class EarningService {


    @Autowired
    private EarningDAO earningDAO;


    public BankConfig getDepositInformation() {
        return earningDAO.getDepositInformation();

    }


    public int establishDurationAndReturnMinimumDeposit(Deposit deposit) {
        double interestRate1Min = getDepositInformation().getInterestRateOneMinute();
        double interestRate5Min = getDepositInformation().getInterestRateFiveMinute();
        double interestRate10Min = getDepositInformation().getInterestRateTenMinute();

        //Setting min time of deposit depending which interest rate
        //And returning minimum amount of money that is needed to make deposit

        if (deposit.getInterestRate() == interestRate1Min) {
            deposit.setDepositTime(1);
            return getDepositInformation().getMinAmountOneMinuteDeposit();
        } else if (deposit.getInterestRate() == interestRate5Min) {
            deposit.setDepositTime(5);
            return getDepositInformation().getMinAmountFiveMinuteDeposit();
        } else if (deposit.getInterestRate() == interestRate10Min) {
            deposit.setDepositTime(10);
            return getDepositInformation().getMinAmountTenMinuteDeposit();
        }
        return 0;
    }

    public Deposit getActiveDeposit(int userId) {

        Deposit deposit = earningDAO.getActiveDeposit(userId);
        if (deposit == null)
            return null;
        return deposit;

    }

    public String validateDeposit(Deposit deposit, BigDecimal availableFunds) {
        String noMoney = "You have not enough money or you are passing not enough to make deposit";

        BigDecimal minimumDeposit = BigDecimal.valueOf(establishDurationAndReturnMinimumDeposit(deposit));
        //checking if we pass no more than we have and also minimum amount that we must have

        boolean isEnoughMoneyToDeposit = deposit.getDepositAmount().compareTo(availableFunds) <= 0 &&
                deposit.getDepositAmount().compareTo(minimumDeposit) >= 0;
        if (isEnoughMoneyToDeposit) {
            return null;
        } else
            return noMoney;
    }

    public void makeDeposit(Deposit deposit) {
        Timer timer = new Timer();
        long timeOfDepositInSec = deposit.getDepositTime() * 60;
        earningDAO.takeMoneyFromAccount(deposit.getUserId(), deposit.getDepositAmount());
        int depositId = earningDAO.createDepositAndReturnId(deposit);


        //Changing status of deposit after given time
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //preventing from changing status when we canceled
                boolean isDepositAlreadyCanceled = earningDAO.getDepositByDepositId(depositId).getDepositStatus() == DepositStatus.CANCELED;
                if (isDepositAlreadyCanceled) {
                    timer.cancel();
                } else {
                    earningDAO.changeStatusToReady(depositId);
                    timer.cancel();
                }

            }
        }, timeOfDepositInSec * 1000);


    }


    public String cancelDeposit(Deposit deposit) {
        int depositId = deposit.getId();
        int userId = deposit.getUserId();

        boolean isReadyStatus = earningDAO.checkIfIsReady(depositId);
        if (!isReadyStatus) {
            // if it is not ready means is started and we can cancel

            BigDecimal amountToSendBack = deposit.getDepositAmount();
            earningDAO.addMoneyToAccount(userId, amountToSendBack);
            earningDAO.changeDepositStatusToCancel(depositId);


            return null;
        } else {
            // already to finalize mean that status is ready not STARTED
            return "Deposit can not be canceled now !";
        }

    }

    public BigDecimal calculateMoneyToReturn(Deposit deposit) {
        Instant startDate = deposit.getStartDate();
        Instant endDate = Instant.now();
        long depositDurationInMinutes = Duration.between(startDate, endDate).toMinutes();
        BigDecimal initialAmount = deposit.getDepositAmount();

        BigDecimal interestRate = BigDecimal.valueOf(deposit.getInterestRate() / 100);

        //BigDecimal finalAmount = initialAmount * (1+interestRate)^depositDurationInMinutes

        BigDecimal adding = BigDecimal.valueOf(1).add(interestRate);
        BigDecimal powering = adding.pow((int) depositDurationInMinutes);
        BigDecimal multiplying = initialAmount.multiply(powering);
        BigDecimal finalAmount = multiplying;
        BigDecimal finalAmountTo4Places = finalAmount.round(new MathContext(finalAmount.toBigInteger().toString().length() + 4));

        return finalAmountTo4Places;
    }



    public void finalizeDeposit(Deposit deposit) {
        int depositId = deposit.getId();
        BigDecimal finalAmount = calculateMoneyToReturn(deposit);

        BigDecimal moneyEarned = finalAmount.subtract(deposit.getDepositAmount());
        int depositDurationInMinutes = (int) Duration.between(deposit.getStartDate(), Instant.now()).toMinutes();
        earningDAO.changeStatusToEndAndSetMinutesWaited(depositDurationInMinutes, depositId);
        earningDAO.addMoneyToAccount(deposit.getUserId(), finalAmount);
        earningDAO.setEarnedMoney(depositId, moneyEarned);

    }

    public BigDecimal getAllEarnedMoney(int userId) {
        return earningDAO.calculateEarnedMoney(userId);
    }

    public List<Deposit> getHistoryForUser(int userId) {
        return earningDAO.getHistoryForUser(userId);
    }
}
