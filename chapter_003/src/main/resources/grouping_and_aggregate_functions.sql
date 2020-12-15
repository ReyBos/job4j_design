create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) VALUES ('phone', 2350);
insert into devices(name, price) VALUES ('computer', 5000);
insert into devices(name, price) VALUES ('laptop', 4000);

insert into people(name) VALUES ('vasya');
insert into people(name) VALUES ('andrey');
insert into people(name) VALUES ('dasha');

insert into devices_people(device_id, people_id) VALUES (1, 1);
insert into devices_people(device_id, people_id) VALUES (1, 3);
insert into devices_people(device_id, people_id) VALUES (2, 2);
insert into devices_people(device_id, people_id) VALUES (3, 2);
insert into devices_people(device_id, people_id) VALUES (3, 3);

--средняя цена всех устройств
select avg(devices.price)
from devices_people
join devices on devices_people.device_id = devices.id;

--средняя цена устройств каждого
select people.name, avg(devices.price)
from people
join devices_people on people.id = devices_people.people_id
join devices on devices_people.device_id = devices.id
group by people.name
order by people.name;

--средняя цена устройств больше 4000
select people.name, avg(devices.price)
from people
join devices_people on people.id = devices_people.people_id
join devices on devices_people.device_id = devices.id
group by people.name
having avg(devices.price) > 4000
order by people.name;