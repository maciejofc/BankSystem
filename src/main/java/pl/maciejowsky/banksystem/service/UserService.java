package pl.maciejowsky.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.FormUser;
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


    public boolean registerUser(FormUser user) {

//        if (userDAO.findUserByEmail(user.getUser().getEmail()) == null) {
//            userDAO.registerUser(user);
//            System.out.println("niby mejl nie istnieje");
//            return true;
//        } else {
//            System.out.println("niby mejl istnieje");
//            return false;
//        }
        userDAO.registerUser(user);
        return true;
    }
}


