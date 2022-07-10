INSERT INTO `account_details` (
                               `specific_account`,
                               `interest_rate`,
                               `account_number`,
                               `active`,
                               `balance`,
                               `fk_user_detail_id`)
VALUES (
        'corporate',
        '5.00',
        '0000123443211234',
        '1',
        '321312',
        '4');
INSERT INTO `account_details` (
                               `specific_account`,
                               `interest_rate`,
                               `account_number`,
                               `active`,
                               `balance`,
                               `fk_user_detail_id`)
VALUES (
        'normal',
        '5.00',
        '0001123443211234',
        '1',
        '321312.02',
        '3');
INSERT INTO `account_details` (
    `specific_account`,
    `interest_rate`,
    `account_number`,
    `active`,
    `balance`,
    `fk_user_detail_id`)
VALUES (
           'normal',
           '3.00',
           '0002123443211234',
           '1',
           '321312.02',
           '4');