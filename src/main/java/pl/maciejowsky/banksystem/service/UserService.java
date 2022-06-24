package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    public User getUserInformation(String email) {
        //business processing
        User user = userDAO.findUserByEmail(email);
        return user;
    }

//    List<User> users getUsersInformations() {
//
//    }


    public boolean registerUser(User user) {

        if (userDAO.findUserByEmail(user.getEmail()) == null) {
            userDAO.registerUser(user);
            return true;
        } else {
            return false;
        }
    }
}


