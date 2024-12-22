use jfoods;

insert into cuisine(id, name) values(1, 'Tailandesa');
insert into cuisine(id, name) values(2, 'Indiana');

insert into restaurant(name, delivery_fee, cuisine_id) values('Thai Gourmet', 10, 1);
insert into restaurant(name, delivery_fee, cuisine_id) values('Thai Delivery', 9.50, 1);
insert into restaurant(name, delivery_fee, cuisine_id) values('Tuk Tuk Comida Indiana', 15, 2);