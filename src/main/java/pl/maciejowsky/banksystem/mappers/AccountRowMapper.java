package pl.maciejowsky.banksystem.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.maciejowsky.banksystem.enums.SpecificAccount;
import pl.maciejowsky.banksystem.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {
        String specificAccountString = rs.getString("specific_account").toUpperCase();
        SpecificAccount specificAccount = SpecificAccount.valueOf(specificAccountString);
        return new Account(
                specificAccount,
                rs.getDouble("interest_rate"),
                rs.getString("account_number"),
                rs.getBoolean("active"),
                rs.getBigDecimal("balance")
        );
    }
}
