CREATE TABLE IF NOT EXISTS `account_details` (
                                       `id` int PRIMARY KEY AUTO_INCREMENT,
                                       `specific_account` ENUM ('normal', 'corporate','saving','debit') NOT NULL,
                                       `interest_rate` DECIMAL(4,2),
                                       `account_number` VARCHAR(16) NOT NULL,
                                       `active` boolean,
                                       `balance` DECIMAL(19,4) NOT NULL,
                                       `fk_user_detail_id` int,
                                       CONSTRAINT `FK_USER_DETAIL_ID` FOREIGN KEY (`fk_user_detail_id`) REFERENCES `user_details` (`id`)

);
