package pl.maciejowsky.banksystem.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.model.Transfer;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Component
public class TransferDAOImpl implements TransferDAO {


    private JdbcTemplate jdbcTemplate;

    public TransferDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean checkIfEnoughFunds(String accountNumber, BigDecimal funds, BigDecimal fee) {
        BigDecimal fundsInvolvedInTransfer = funds.add(fee);
        String sql = "SELECT count(*) FROM account_details WHERE account_number =?" + " AND balance >=?";

        int count = jdbcTemplate.queryForObject(sql, Integer.class, accountNumber, fundsInvolvedInTransfer);

        boolean exists;
        exists = count > 0;
        return exists;
    }

    @Override
    public boolean checkIfAccountExist(String accountNumber) {
        String sql = "SELECT count(*) FROM account_details WHERE account_number = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, accountNumber);
        boolean exists;
        exists = count > 0;
        return exists;
    }

    @Override
    public void makeTransferFromFirstAccount(Transfer transfer) {
        String fromAccount = transfer.getFromAccount();
        BigDecimal fundsInvolvedInTransfer = transfer.getAmount().add(transfer.getTransferType().getFee());

        //Taking away funds from first account to send + fee
        String sql = "UPDATE account_details" + " SET balance =balance - ? WHERE account_number = ?";
        jdbcTemplate.update(sql, fundsInvolvedInTransfer, fromAccount);


    }

    @Override
    public void receiveTransferFromSecondAccount(Transfer transfer) {
        String toAccount = transfer.getToAccount();
        BigDecimal fundsToReceive = transfer.getAmount();

        //Adding funds to second account
        String sql = "UPDATE account_details" + " SET balance =balance + ? WHERE account_number = ?";
        jdbcTemplate.update(sql, fundsToReceive, toAccount);

    }

    @Override
    public void saveTransferToHistory(Transfer transfer) {
        String sql = "INSERT INTO transfer_history (from_account,to_account," + "amount,date_of_execution,date_of_receiving,transfer_type) VALUES (?,?,?,?,?,?)";

        Timestamp timestampSendAt = Timestamp.from(transfer.getSendAt());
        Timestamp timestampReceiveAt = Timestamp.from(transfer.getReceiveAt());
        jdbcTemplate.update(sql, transfer.getFromAccount(), transfer.getToAccount(), transfer.getAmount(), timestampSendAt, timestampReceiveAt, transfer.getTransferType().name().toLowerCase());
    }
}
