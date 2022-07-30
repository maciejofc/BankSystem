CREATE TABLE IF NOT EXISTS `user_details`
(
    `id`           int PRIMARY KEY,
    `funds`        int                              NOT NULL,
    `account_type` ENUM ('regular', 'entrepreneur') NOT NULL,
    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
);
