CREATE table transfer_history(
                                 id int AUTO_INCREMENT PRIMARY KEY,
                                 from_account varchar(16) NOT NULL,
                                 to_account varchar(16) NOT NULL,
                                 amount decimal(19,4) NOT NULL,
                                 date_of_execution timestamp ,
                                 date_of_receiving timestamp ,
                                 transfer_type ENUM ('normal', 'express','instant') NOT NULL,
                                 fk_sender_user_id int NOT NULL,
                                 fk_receiver_user_id int NOT NULL,

                                 CONSTRAINT fk_sender_user_id_constraint FOREIGN KEY (fk_sender_user_id) REFERENCES users(id) ,
                                 CONSTRAINT fk_receiver_user_id_constraint FOREIGN KEY (fk_receiver_user_id) REFERENCES users(id)
);