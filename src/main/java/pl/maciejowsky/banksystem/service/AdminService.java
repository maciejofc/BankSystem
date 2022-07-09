package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.User;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserDAO userDAO;


    public List<User> getAllUsersAndManagers(){
        return userDAO.findAllUsersAndManagers();
    }

}
