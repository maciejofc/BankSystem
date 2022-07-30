package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.BankConfig;

public interface BankConfigDAO {

    BankConfig getBankConfiguration();

    void createNewConfiguration(BankConfig bankConfig,int authorId);

}
