create database testdb;
use testdb;
create table users (
    id int primary key ,
    name varchar(25) not null,
    email varchar(25) not null unique
);

insert into users (id, name, email) values (1, 'Alice', 'alice@example.com');

select * from users;