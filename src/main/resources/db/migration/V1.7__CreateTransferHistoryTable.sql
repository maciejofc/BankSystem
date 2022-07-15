CREATE table transfer_history(
                                 id int AUTO_INCREMENT PRIMARY KEY,
                                 from_account varchar(16) NOT NULL,
                                 to_account varchar(16) NOT NULL,
                                 amount decimal(19,4) NOT NULL,
                                 date_of_execution timestamp ,
                                 date_of_receiving timestamp ,
                                 transfer_type ENUM ('normal', 'express','instant') NOT NULL,
                                 CONSTRAINT fk_from_account FOREIGN KEY (from_account) REFERENCES account_details(account_number) ,
                                 CONSTRAINT fk_to_account FOREIGN KEY (to_account) REFERENCES account_details(account_number)
);