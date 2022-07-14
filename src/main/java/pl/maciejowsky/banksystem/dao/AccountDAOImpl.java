package pl.maciejowsky.banksystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.mappers.AccountRowMapper;
import pl.maciejowsky.banksystem.model.Account;
import pl.maciejowsky.banksystem.model.User;

import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {


    private JdbcTemplate jdbcTemplate;

    public AccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> listAllSpecificAccountsForUser(String email) {
        String sql = "SELECT account_details.specific_account, " +
                "account_details.interest_rate, " +
                "account_details.account_number, " +
                "account_details.active, " +
                "account_details.balance " +
                "FROM account_details" +
                " LEFT JOIN users" +
                " ON account_details.fk_user_detail_id = users.id" +
                " WHERE users.email=?";

        List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper(), new Object[]{email});
        return accounts;
    }
}
