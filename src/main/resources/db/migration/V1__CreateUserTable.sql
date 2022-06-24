CREATE TABLE IF NOT EXISTS `users` (
                                       `id` int PRIMARY KEY AUTO_INCREMENT,
                                       `full_name` varchar(255),
                                       `birth_day` date,
                                       `email` varchar(255),
                                       `password` varchar(255),
                                       `street_address` varchar(255),
                                       `zip_code` varchar(255),
                                       `city` varchar(255),
                                       `country` varchar(255),
                                       `created_at` timestamp,
                                       `updated_at` timestamp,
                                       `account_type` ENUM ('regular', 'entrepreneur'),
                                       `authorities` varchar(255),
                                       `roles` varchar(255)
);
