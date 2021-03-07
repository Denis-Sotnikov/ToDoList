create table item(
    id SERIAL PRIMARY KEY,
    description text,
    created timestamp,
    done boolean
);

create table j_role (
    id serial primary key,
    name varchar(2000)
);

create table j_user (
    id serial primary key,
    name varchar(2000) unique,
    password varchar(2000),
    role_id int not null references j_role(id)
);

insert into j_role(name) values ('user');
insert into j_role(name) values ('admin');