insert into payment (id, item_name, amount) values ((select nextval('payment_id_generator')), 'Test item', 500);
insert into payment (id, item_name, amount) values ((select nextval('payment_id_generator')), 'Test item 2', 750);
insert into payment (id, item_name, amount) values ((select nextval('payment_id_generator')), 'Test item 3', 1000);