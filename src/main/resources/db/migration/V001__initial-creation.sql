create table cuisine(
  id bigint not null auto_increment,
  name varchar(60) not null,

  primary key(id)
) engine=InnoDB default charset=utf8;