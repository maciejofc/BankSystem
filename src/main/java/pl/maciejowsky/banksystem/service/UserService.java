package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.FormUser;
import pl.maciejowsky.banksystem.model.User;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    public User getUserInformation(String email) {

        User user = userDAO.findUserByEmail(email);
        return user;
    }

    public boolean registerUser(FormUser user) {
        userDAO.registerUser(user);
        return true;
    }
}


