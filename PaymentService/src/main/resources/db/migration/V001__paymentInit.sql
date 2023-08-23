create table payment(
    id INTEGER NOT NULL,
    amount decimal,
    item_name varchar(255),

    constraint pk_payment PRIMARY KEY (id)
);

create sequence payment_id_generator;