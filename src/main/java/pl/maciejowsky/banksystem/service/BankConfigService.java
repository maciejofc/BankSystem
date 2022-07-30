package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.BankConfigDAO;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.BankConfig;

@Service
public class BankConfigService {

    @Autowired
    BankConfigDAO bankConfigDAO;


    @Autowired
    UserDAO userDAO;

    public BankConfig getBankConfiguration() {
        return bankConfigDAO.getBankConfiguration();
    }

    public String getAuthorOfConfiguration() {

        int id = bankConfigDAO.getBankConfiguration().getFkAdminId();
        if(id==0){
            return "Default configuration";
        }
        return userDAO.findUserById(id).getFullName();
    }

    public void createNewConfig(BankConfig bankConfig,int authorId) {
         bankConfigDAO.createNewConfiguration(bankConfig,authorId);
    }

}
