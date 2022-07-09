package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllUsersAndManagers();
    User findUserByEmail(String email);

    int registerUser(User user);
}
