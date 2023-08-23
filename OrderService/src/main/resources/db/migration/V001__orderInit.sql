create table order_entity
(
      order_number integer,
      ordered_item_name varchar(255),
      ordered_item_id integer,
      sum decimal,
      payed bool,
      shipped bool,
      time_of_order date,

      CONSTRAINT pk_order PRIMARY KEY (order_number)
);

create sequence order_number_generator;