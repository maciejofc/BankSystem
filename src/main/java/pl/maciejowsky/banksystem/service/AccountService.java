package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.AccountDAO;
import pl.maciejowsky.banksystem.model.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    public List<Account> getAllAccounts(String email) {


        List<Account> accounts = accountDAO.listAllSpecificAccountsForUser(email);
        for (int i = 0; i < accounts.size(); i++) {

            Account actualAccount = accounts.get(i);
            BigDecimal roundedBalance = new BigDecimal(String.valueOf(actualAccount.getBalance()));
            roundedBalance = roundedBalance.setScale(2, RoundingMode.HALF_UP);
            actualAccount.setBalance(roundedBalance);


        }
        return accounts;
    }
}
