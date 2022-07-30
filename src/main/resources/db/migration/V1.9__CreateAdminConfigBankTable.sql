CREATE TABLE admin_config_bank
(
    id                                 int primary key auto_increment,
    announcement                       VARCHAR(255)  default 'Monkey make money :)',
    interest_rate_one_minute           decimal(4, 2) default 1,
    minimum_amount_one_minute_deposit  int           default 1000,
    interest_rate_five_minute          decimal(4, 2) default 5,
    minimum_amount_five_minute_deposit int           default 5000,
    interest_rate_ten_minute           decimal(4, 2) default 10,
    minimum_amount_ten_minute_deposit  int           default 10000,
    interest_rate_saving_regular       decimal(4, 2) default 5,
    interest_rate_saving_entrepreneur  decimal(4, 2) default 10,
    bank_status                        boolean       default true,
    fk_admin_id                        int NULL,
    CONSTRAINT fk_admin_id_constraint FOREIGN KEY (fk_admin_id) REFERENCES users (id)

)

