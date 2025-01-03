create table state(
  id bigint not null auto_increment,
  name varchar(80) not null,

  primary key(id)
) engine=InnoDB default charset=utf8;

insert into state(name) select distinct state_name from city;

alter table city add column state_id bigint not null;

update city city set city.state_id = (select state.id from state state where state.name = city.state_name);

alter table city add constraint fk_city_state
foreign key(state_id) references state(id);

alter table city drop column state_name;

alter table city change city_name name varchar(80) not null;