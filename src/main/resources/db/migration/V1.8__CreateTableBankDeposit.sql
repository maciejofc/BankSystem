CREATE TABLE bank_deposit
(
    id              int primary key auto_increment,
    interest_rate   decimal(4, 2)  NOT NULL,
    time_in_minutes int            NOT NULL,
    deposit_amount  decimal(19, 4) NOT NULL,
    start_date      timestamp                             default CURRENT_TIMESTAMP(),
    fk_user_id      int            NOT NULL,
    status          enum ('started','canceled','ready','ended') default 'started',
    money_earned    decimal(19,4) default 0,
    CONSTRAINT fk_user_id_constraint FOREIGN KEY (fk_user_id) REFERENCES users (id)
)

