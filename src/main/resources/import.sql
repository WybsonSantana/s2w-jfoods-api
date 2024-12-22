use jfoods;

insert into cuisine(id, name) values(1, 'Tailandesa');
insert into cuisine(id, name) values(2, 'Indiana');

insert into restaurant(name, delivery_fee, cuisine_id) values('Thai Gourmet', 10, 1);
insert into restaurant(name, delivery_fee, cuisine_id) values('Thai Delivery', 9.50, 1);
insert into restaurant(name, delivery_fee, cuisine_id) values('Tuk Tuk Comida Indiana', 15, 2);

insert into state(id, name) values(1, 'Minas Gerais');
insert into state(id, name) values(2, 'São Paulo');
insert into state(id, name) values (3, 'Ceará');
insert into state(id, name) values (4, 'Goiás');

insert into city(id, name, state_id) values(1, 'Uberlândia', 1);
insert into city(id, name, state_id) values(2, 'Belo Horizonte', 1);
insert into city(id, name, state_id) values(3, 'São Paulo', 2);
insert into city(id, name, state_id) values(4, 'Campinas', 2);
insert into city(id, name, state_id) values(5, 'Fortaleza', 3);
insert into city(id, name, state_id) values(6, 'Goiânia', 4);
insert into city(id, name, state_id) values(7, 'Niquelândia', 4);

insert into payment_method(id, description) values(1, 'Cartão de crédito');
insert into payment_method(id, description) values(2, 'Cartão de débito');
insert into payment_method(id, description) values(3, 'Dinheiro');
insert into payment_method(id, description) values(4, 'Pix');

insert into permission(id, name, description) values(1, 'GET_CUISINES', 'Allows you to retrieve cuisines');
insert into permission(id, name, description) values(2, 'PUT_CUISINES', 'Allows you to update cuisines');