create table type (
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    expired_date timestamp,
    price decimal,
    type_id int references type(id)
);

insert into type(name) VALUES ('сыр');
insert into type(name) VALUES ('мороженое');
insert into type(name) VALUES ('молоко');

insert into product(name, expired_date, price, type_id) VALUES ('плавленный', '2021.01.01', 205.12, 1);
insert into product(name, expired_date, price, type_id) VALUES ('творожный', '2021.10.01', 15.25, 1);
insert into product(name, expired_date, price, type_id) VALUES ('с плесенью', '2020.01.01', 2015.75, 1);
insert into product(name, expired_date, price, type_id) VALUES ('ванильное мороженое', '2022.5.28', 111.75, 2);
insert into product(name, expired_date, price, type_id) VALUES ('шоколадное', '2020.12.21', 200.75, 2);
insert into product(name, expired_date, price, type_id) VALUES ('цельное', '2020.11.01', 50, 3);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select product.*
from product
join type on product.type_id = type.id
where type.name = 'сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select *
from product
where name like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select *
from product
where expired_date >= date_trunc('month', CURRENT_DATE) + INTERVAL '1 month'
    and expired_date < date_trunc('month', CURRENT_DATE) + INTERVAL '2 month';

--4. Написать запрос, который выводит самый дорогой продукт.
select *
from product
order by price desc
limit 1;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select type.*, count(product) as amount
from type
join product on type.id = product.type_id
group by type.id
order by type.id;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select product.*
from product
join type on product.type_id = type.id
where type.name = 'сыр'
    or type.name = 'молоко';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select type.name, count(product) as amount
from type
join product on type.id = product.type_id
group by type.name
having count(product) < 10;

--8. Вывести все продукты и их тип.
select product.name as product, type.name as type_name
from product
join type on product.type_id = type.id;