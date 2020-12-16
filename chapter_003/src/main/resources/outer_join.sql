--Даны две сущности, представленные в таблицах departments и emploees.
-- Отношение one-to-many. В таблицах только поле name.
create database outer_join;
--1. Создать таблицы и заполнить их начальными данными
create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) VALUES ('dep1');
insert into departments(name) VALUES ('dep2');
insert into departments(name) VALUES ('dep3');

insert into employees(name, departments_id) VALUES ('emp1', 1);
insert into employees(name, departments_id) VALUES ('emp2', 1);
insert into employees(name, departments_id) VALUES ('emp3', 1);
insert into employees(name, departments_id) VALUES ('emp4', 2);
insert into employees(name, departments_id) VALUES ('emp5', 2);
insert into employees(name, departments_id) VALUES ('emp6', null);

--2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from employees
left join departments on employees.departments_id = departments.id;

select *
from departments
left join employees on departments.id = employees.departments_id;

select *
from employees
full join departments on employees.departments_id = departments.id;

select *
from departments cross join employees;

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select employees.*
from employees
left join departments on employees.departments_id = departments.id
where departments.id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select *
from departments
left join employees on departments.id = employees.departments_id;

select *
from employees
right join departments on employees.departments_id = departments.id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары
create table teens (
    name varchar(255),
    gender varchar(1)
);

insert into teens(name, gender) VALUES ('boy1', 'm');
insert into teens(name, gender) VALUES ('boy2', 'm');
insert into teens(name, gender) VALUES ('boy3', 'm');
insert into teens(name, gender) VALUES ('girl1', 'w');
insert into teens(name, gender) VALUES ('girl2', 'w');
insert into teens(name, gender) VALUES ('girl3', 'w');

select *
from (select name from teens where gender = 'm') as boys
cross join (select name from teens where gender = 'w') as girls;