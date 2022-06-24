package pl.maciejowsky.banksystem.dao;

import pl.maciejowsky.banksystem.model.User;

public interface UserDAO {

    //ist<User> findAllUsers();
    User findUserByEmail(String email);

    int registerUser(User user);
}
