create table identity(
  id bigint not null auto_increment,
  name varchar(80) not null,
  email varchar(255) not null,
  password varchar(255) not null,
  registration_date datetime not null,

  primary key(id)
) engine = InnoDB default charset = utf8;

create table identity_role(
  identity_id bigint not null,
  role_id bigint not null,

  primary key(identity_id, role_id)
) engine = InnoDB default charset = utf8;

create table payment_method(
  id bigint not null auto_increment,
  description varchar(60) not null,

  primary key(id)
) engine=InnoDB default charset=utf8;

create table permission(
  id bigint not null auto_increment,
  name varchar(100) not null,
  description varchar(60) not null,

  primary key(id)
) engine=InnoDB default charset=utf8;

create table product(
  id bigint not null auto_increment,
  restaurant_id bigint not null,
  name varchar(80) not null,
  description text not null,
  price decimal(19, 2) not null,
  active tinyint(1) not null,

  primary key(id)
) engine=InnoDB default charset=utf8;

create table restaurant(
  id bigint not null auto_increment,
  cuisine_id bigint not null,
  name varchar(80) not null,
  delivery_fee decimal(19, 2) not null,
  registration_date datetime not null,
  last_update_date datetime not null,

  address_city_id bigint,
  address_postal_code varchar(9),
  address_street varchar(100),
  address_number varchar(20),
  address_complement varchar(60),
  address_district varchar(60),

  primary key(id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_method(
  restaurant_id bigint not null,
  payment_method_id bigint not null,

  primary key(restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8;

create table role(
  id bigint not null auto_increment,
  name varchar(60) not null,

  primary key (id)
) engine=InnoDB default charset=utf8;

create table role_permission(
  role_id bigint not null,
  permission_id bigint not null,

  primary key(role_id, permission_id)
) engine=InnoDB default charset=utf8;

alter table identity_role add constraint fk_identity_role_role
foreign key(role_id) references role(id);

alter table identity_role add constraint fk_identity_role_identity
foreign key(identity_id) references identity(id);

alter table product add constraint fk_product_restaurant
foreign key(restaurant_id) references restaurant(id);

alter table restaurant add constraint fk_restaurant_cuisine
foreign key(cuisine_id) references cuisine(id);

alter table restaurant add constraint fk_restaurant_city
foreign key(address_city_id) references city(id);

alter table restaurant_payment_method add constraint fk_restaurant_payment_method_payment_method
foreign key(payment_method_id) references payment_method(id);

alter table restaurant_payment_method add constraint fk_restaurant_payment_method_restaurant
foreign key(restaurant_id) references restaurant(id);

alter table role_permission add constraint fk_role_permission_permission
foreign key(permission_id) references permission(id);

alter table role_permission add constraint fk_role_permission_role
foreign key(role_id) references role(id);