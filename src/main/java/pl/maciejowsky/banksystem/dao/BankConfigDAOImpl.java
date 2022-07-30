package pl.maciejowsky.banksystem.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.maciejowsky.banksystem.mappers.BankConfigMapper;
import pl.maciejowsky.banksystem.model.BankConfig;

@Component
public class BankConfigDAOImpl implements BankConfigDAO {

    JdbcTemplate jdbcTemplate;

    public BankConfigDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BankConfig getBankConfiguration() {

        String sql = "select * from admin_config_bank where id =  (select MAX(id) from admin_config_bank)";
        BankConfig bankConfig = jdbcTemplate.queryForObject(sql, new BankConfigMapper());
        return bankConfig;
    }


    @Override
    public void createNewConfiguration(BankConfig bankConfig, int authorId) {

        String sql = "insert into admin_config_bank(announcement, interest_rate_one_minute," +
                " interest_rate_five_minute, interest_rate_ten_minute," +
                " interest_rate_saving_regular, interest_rate_saving_entrepreneur," +
                " bank_status, fk_admin_id) values (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                bankConfig.getAnnouncement(),
                bankConfig.getInterestRateOneMinute(),
                bankConfig.getInterestRateFiveMinute(),
                bankConfig.getInterestRateTenMinute(),
                bankConfig.getInterestRateSavingRegular(),
                bankConfig.getInterestRateSavingEntrepreneur(),
                bankConfig.isBankStatus(),
                bankConfig.getFkAdminId()
        );
    }
}
