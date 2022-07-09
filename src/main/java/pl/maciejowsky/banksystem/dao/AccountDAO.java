package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.Account;

import java.util.List;

public interface AccountDAO {
   List<Account> listAllSpecificAccountsForUser(String email);


}
