package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.FormUser;
import pl.maciejowsky.banksystem.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllUsersAndManagers();
    User findUserByEmail(String email);

    void registerUser(FormUser user);
}
