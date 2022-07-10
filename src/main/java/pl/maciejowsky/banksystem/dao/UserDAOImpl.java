package pl.maciejowsky.banksystem.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.mappers.UserRowMapper;
import pl.maciejowsky.banksystem.model.FormUser;
import pl.maciejowsky.banksystem.model.User;
import pl.maciejowsky.banksystem.model.UserDetail;
import pl.maciejowsky.banksystem.utility.AccountNumberGenerator;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;

import static pl.maciejowsky.banksystem.utility.AccountNumberGenerator.generateNumber;

@Component
public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), new Object[]{email});
        return user;
    }


    public int findUserIdByEmail(String email) {
        String sql = "SELECT id FROM users WHERE email =?";
        return jdbcTemplate.queryForObject(
                sql, new Object[]{email}, Integer.class);
    }

    public void insertUserDetailTable(int id, UserDetail userDetail) {

        String sql = " insert into user_details(id,funds,account_type) values (?,?,?);";
        jdbcTemplate.update(sql,
                id,
                userDetail.getFunds(),
                userDetail.getUserType().name()
        );
    }


    public void insertUserTable(User user) {
        Date birthDay = Date.valueOf(user.getBirthDay());
        Timestamp dateOfCreation = Timestamp.from(Instant.now());
        String userRole = "user";
        String sql = "insert into users(full_name, birth_day,email,password,street_address,zip_code,city,country,created_at,roles)  " +
                " values (?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql,
                user.getFullName(),
                birthDay,
                user.getEmail(),
                user.getPassword(),
                user.getStreetAddress(),
                user.getZipCode(),
                user.getCity(),
                user.getCountry(),
                dateOfCreation,
                userRole);
    }

    public String createNonExistingAccountNumber() {
        String sql = "SELECT exists(select * from account_details where account_number=?)";

        while (true) {
            String possibleNewAccountNumber = AccountNumberGenerator.generateNumber();

            boolean exists = jdbcTemplate.queryForObject(sql, new Object[]{possibleNewAccountNumber}, Boolean.class);
            if (!exists) {
                return possibleNewAccountNumber;
            }
        }


    }

    public void insertAccountDetails(int balance, int id) {
        String sql = "insert into account_details(specific_account, interest_rate,account_number,active,balance,fk_user_detail_id)" +
                " values (?,?,?,?,?,?);";
        jdbcTemplate.update(sql,
                "normal",
                0,
                createNonExistingAccountNumber(),
                1,
                balance,
                id);
    }

    @Override
    public void registerUser(FormUser user) {
//Registering user - inserting data into 3 tables (overall info and creating first normal account with balance)


        //retrieving user object and userDetail object

        User userWithMainAttributes = user.getUser();
        UserDetail userWithAdditionalAttributes = user.getUserDetail();
        //email is needed to pair one to one relationship
        String emailToRegister = userWithMainAttributes.getEmail();

        //inserting 2 overall info tables

        insertUserTable(userWithMainAttributes);

        //it is primary key in users and also primary&foreign in userDetails
        int id = findUserIdByEmail(emailToRegister);

        insertUserDetailTable(id, userWithAdditionalAttributes);


        //inserting data into account_details - first normal account
        int funds = user.getUserDetail().getFunds();

        insertAccountDetails(funds, id);
    }

    @Override
    public List<User> findAllUsersAndManagers() {
        String sql = "SELECT * FROM users ";
        List<User> users = jdbcTemplate.query(
                sql, new UserRowMapper()
        );
        return users;
    }
}
