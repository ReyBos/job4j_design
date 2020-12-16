create database car_storage;

create table body (
    id serial primary key,
    name varchar(255)
);
create table engine (
    id serial primary key,
    name varchar(255)
);
create table transmission (
    id serial primary key,
    name varchar(255)
);
create table car (
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body(name)
values ('body1'), ('body2'), ('body3');
insert into engine(name)
values ('engine1'), ('engine2'), ('engine3');
insert into transmission(name)
values ('transmission1'), ('transmission2'), ('transmission3');
insert into car(name, body_id, engine_id, transmission_id)
values ('car1', 1, 1, 1), ('car2', 2, 2, 2);

--1. Вывести список всех машин и все привязанные к ним детали.
select car.name as car, body.name as body, engine.name as engine, transmission.name as transmission
from car
left join body on car.body_id = body.id
left join engine on car.engine_id = engine.id
left join transmission on car.transmission_id = transmission.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select body.name
from body
left join car on body.id = car.body_id
where car.id is null
union
select engine.name
from engine
left join car on engine.id = car.body_id
where car.id is null
union
select transmission.name
from transmission
left join car on transmission.id = car.body_id
where car.id is null;