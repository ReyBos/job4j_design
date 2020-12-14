create database country;
create table country (
    id serial primary key,
    name varchar(255),
    population integer
);
insert into country(name, population) VALUES ('Russia', 12345);
select * from country;
update country set name = 'USA';
select * from country;
delete from country;
select * from country;