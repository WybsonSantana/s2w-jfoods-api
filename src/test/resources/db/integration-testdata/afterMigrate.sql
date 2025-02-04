set foreign_key_checks = 0;

delete from city;
delete from cuisine;
delete from identity;
delete from identity_role;
delete from payment_method;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from role;
delete from role_permission;
delete from state;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table cuisine auto_increment = 1;
alter table identity auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table role auto_increment = 1;
alter table state auto_increment = 1;

insert into cuisine(id, name) values(1, 'Brasileira');
insert into cuisine(id, name) values(2, 'Americana');

insert into restaurant(id, name, delivery_fee, cuisine_id, registration_date, last_update_date) values(1, 'Comida Goiana', 10.0, 1, utc_timestamp, utc_timestamp);
insert into restaurant(id, name, delivery_fee, cuisine_id, registration_date, last_update_date) values(2, 'Burger Top', 10.0, 2, utc_timestamp, utc_timestamp);