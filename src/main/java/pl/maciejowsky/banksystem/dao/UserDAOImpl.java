package pl.maciejowsky.banksystem.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.mappers.UserRowMapper;
import pl.maciejowsky.banksystem.model.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;

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
        System.out.println(user.getEmail() + "," + user.getPassword());
        return user;
    }

    @Override
    public int registerUser(User user) {
        String sql = "INSERT INTO users (full_name,birth_day,email,password ,street_address,zip_code,city,country,created_at,roles)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        Date birthDay = Date.valueOf(user.getBirthDay());

        Timestamp dateOfCreation = Timestamp.from(Instant.now());
        return jdbcTemplate.update(sql, user.getFullName(),
                birthDay,
                user.getEmail(),
                user.getPassword(),
                user.getStreetAddress(),
                user.getZipCode(),
                user.getCity(),
                user.getCountry(),
                dateOfCreation,

                user.getRoles()
        );
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
