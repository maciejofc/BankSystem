CREATE TABLE IF NOT EXISTS `users`
(
    `id`             int PRIMARY KEY AUTO_INCREMENT,
    `full_name`      varchar(255) NOT NULL,
    `birth_day`      date         NOT NULL,
    `email`          varchar(255) NOT NULL,
    `password`       varchar(255) NOT NULL,
    `street_address` varchar(255) NOT NULL,
    `zip_code`       varchar(255) NOT NULL,
    `city`           varchar(255) NOT NULL,
    `country`        varchar(255) NOT NULL,
    `created_at`     timestamp,
    `updated_at`     timestamp,
    `authorities`    varchar(255) NOT NULL,
    `roles`          varchar(255) NOT NULL
);
