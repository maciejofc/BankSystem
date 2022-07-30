package pl.maciejowsky.banksystem.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.maciejowsky.banksystem.model.BankConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankConfigMapper implements RowMapper<BankConfig> {

    @Override
    public BankConfig mapRow(ResultSet rs, int i) throws SQLException {

        return new BankConfig(
                rs.getString("announcement"),
                rs.getDouble("interest_rate_one_minute"),
                rs.getInt("minimum_amount_one_minute_deposit"),
                rs.getDouble("interest_rate_five_minute"),
                rs.getInt("minimum_amount_five_minute_deposit"),
                rs.getDouble("interest_rate_ten_minute"),
                rs.getInt("minimum_amount_ten_minute_deposit"),
                rs.getDouble("interest_rate_saving_regular"),
                rs.getDouble("interest_rate_saving_entrepreneur"),
                rs.getBoolean("bank_status"),
                rs.getInt("fk_admin_id")
        );
    }
}
