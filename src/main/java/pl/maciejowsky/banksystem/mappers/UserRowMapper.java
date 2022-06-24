package pl.maciejowsky.banksystem.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.maciejowsky.banksystem.model.AccountType;
import pl.maciejowsky.banksystem.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        Instant createdAt = null;
        if (!(rs.getTimestamp("created_at") == null)) {
            createdAt = rs.getTimestamp("created_at").toInstant();
        }
        Instant updatedAt = null;
        if (!(rs.getTimestamp("updated_at") == null)) {
            updatedAt = rs.getTimestamp("updated_at").toInstant();
        }

        LocalDate birthDay = rs.getDate("birth_day").toLocalDate();
        return new User(
                rs.getInt("id"),
                rs.getString("full_name"),
                birthDay,
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("street_address"),
                rs.getString("country"),
                rs.getString("zip_code"),
                rs.getString("city"),
                createdAt,
                updatedAt,
                AccountType.valueOf(rs.getString("account_type").toUpperCase()),
                rs.getString("authorities"),
                rs.getString("roles")
        );


    }
}
