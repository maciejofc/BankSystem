package pl.maciejowsky.banksystem.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.maciejowsky.banksystem.enums.TransferType;
import pl.maciejowsky.banksystem.model.Transfer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet rs, int i) throws SQLException {

        TransferType transferType = TransferType.valueOf(
                rs.getString("transfer_type").toUpperCase());

        return new Transfer(
                rs.getString("from_account"),
                rs.getString("to_account"),
                rs.getBigDecimal("amount"),
                transferType,
                rs.getTimestamp("date_of_execution").toInstant(),
                rs.getTimestamp("date_of_receiving").toInstant()
        );
    }
}
