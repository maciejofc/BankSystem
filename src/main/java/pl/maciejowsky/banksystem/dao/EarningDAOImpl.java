package pl.maciejowsky.banksystem.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.mappers.BankConfigMapper;
import pl.maciejowsky.banksystem.mappers.DepositMapper;
import pl.maciejowsky.banksystem.model.BankConfig;
import pl.maciejowsky.banksystem.model.Deposit;

import java.math.BigDecimal;
import java.util.List;

@Component
public class EarningDAOImpl implements EarningDAO {


    JdbcTemplate jdbcTemplate;

    public EarningDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BankConfig getDepositInformation() {

        //It should refresh whenever admin make change
        String sql = "select * from admin_config_bank where id = " + " (select max(id) from admin_config_bank)";

        return jdbcTemplate.queryForObject(sql, new BankConfigMapper());
    }

    public Deposit getDepositByDepositId(int depositId) {
        String sql = "select * from bank_deposit where id =?";
        return jdbcTemplate.queryForObject(sql, new DepositMapper(), depositId);
    }

    @Override
    public Deposit getActiveDeposit(int userId) {

        try {
            String sql = "select * from bank_deposit where fk_user_id = ? and (status= 'started' or status ='ready')";
            return jdbcTemplate.queryForObject(sql, new DepositMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("no active deposit");
            return null;
        }
    }


    public boolean checkIfIsReady(int depositId) {
        String sql = "select count(*) from bank_deposit where id =? and status ='ready'";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, depositId);
        boolean exists;
        exists = count > 0;
        return exists;
    }


    // inserting
    @Override
    public int createDepositAndReturnId(Deposit deposit) {
        String sql = "insert into bank_deposit (interest_rate,time_in_minutes,deposit_amount,fk_user_id) values (?,?,?,?)";

        jdbcTemplate.update(sql, deposit.getInterestRate(), deposit.getDepositTime(), deposit.getDepositAmount(), deposit.getUserId());
        String sql2 = "select id from bank_deposit where id = last_insert_id()";
        int id = jdbcTemplate.queryForObject(sql2, Integer.class);

        return id;
    }

    public void takeMoneyFromAccount(int userId, BigDecimal moneyToTransfer) {
        String sql = "update account_details set balance = balance - ? where specific_account = 'normal' and  fk_user_detail_id = ? ";
        jdbcTemplate.update(sql, moneyToTransfer, userId);

    }

    @Override
    public void addMoneyToAccount(int userId, BigDecimal moneyToTransfer) {
        String sql = "update account_details set balance = balance + ?  where specific_account = 'normal' and " + " fk_user_detail_id = ? ";
        jdbcTemplate.update(sql, moneyToTransfer, userId);
    }

    @Override
    public void setEarnedMoney(int depositId, BigDecimal moneyEarned) {
        String sql = "update bank_deposit set money_earned = ? where id = ? ";
        jdbcTemplate.update(sql, moneyEarned, depositId);
    }

    @Override
    public List<Deposit> getHistoryForUser(int userId) {
        String sql = "select * from bank_deposit where fk_user_id = ?";
        return jdbcTemplate.query(sql, new DepositMapper(), userId);
    }

    @Override
    public void changeStatusToReady(int depositId) {
        String sql = "update bank_deposit set status = 'ready' where id =? ";
        jdbcTemplate.update(sql, depositId);
    }

    @Override
    public void changeStatusToEndAndSetMinutesWaited(int minutes, int depositId) {
        String sql = "update bank_deposit set status = 'ended', time_in_minutes = ? where id = ? ";
        jdbcTemplate.update(sql, minutes, depositId);
    }

    @Override
    public void changeDepositStatusToCancel(int depositId) {
        String sql = "update bank_deposit set status = 'canceled' where id = ?";
        jdbcTemplate.update(sql, depositId);
    }

    @Override
    public BigDecimal calculateEarnedMoney(int userId) {

        String sql = "select SUM(money_earned) from bank_deposit where fk_user_id =?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

    }
}
