insert into shipment (id, item_name, destination) values ((select nextval('shipment_id_generator')), 'Test item', 'Norway');
insert into shipment (id, item_name, destination) values ((select nextval('shipment_id_generator')), 'Test item 2', 'Sweden');
insert into shipment (id, item_name, destination) values ((select nextval('shipment_id_generator')), 'Test item 3', 'USA');