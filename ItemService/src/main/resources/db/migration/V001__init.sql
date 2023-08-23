create table item
(
    id integer,
    name varchar(255),
    price decimal,
    quantity integer,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

create sequence item_id_sequence;