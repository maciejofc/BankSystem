package pl.maciejowsky.banksystem.mappers;


import org.springframework.jdbc.core.RowMapper;
import pl.maciejowsky.banksystem.enums.DepositStatus;
import pl.maciejowsky.banksystem.model.Deposit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class DepositMapper implements RowMapper<Deposit> {

    @Override
    public Deposit mapRow(ResultSet rs, int i) throws SQLException {
        DepositStatus depositStatus = DepositStatus.valueOf(rs.getString("status").toUpperCase());
        Instant startDate = rs.getTimestamp("start_date").toInstant();
        return new Deposit(rs.getInt("id"),
                rs.getDouble("interest_rate"),
                rs.getInt("time_in_minutes"),
                rs.getBigDecimal("deposit_amount"),
                startDate,
                rs.getInt("fk_user_id"),
                depositStatus,
                rs.getBigDecimal("money_earned")
        );
    }
}
