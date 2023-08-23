create table shipment(
    id int not null ,
    item_name VARCHAR(255),
    destination varchar(255),

    constraint pk_shipment PRIMARY KEY (id)
);

create sequence shipment_id_generator;