create table class (
    id serial primary key,
    number int,
    char varchar(1)
);
create table pupil (
    id serial primary key,
    name varchar(255),
    class_id int references class(id)
);

insert into class(number, char) VALUES (1, 'a');
insert into class(number, char) VALUES (1, 'b');
insert into class(number, char) VALUES (2, 'a');
insert into class(number, char) VALUES (2, 'b');
insert into pupil(name, class_id) VALUES ('andrey', 1);
insert into pupil(name, class_id) VALUES ('artem', 3);

select * from pupil join class on pupil.class_id = class.id;
select pup.name, cl.number, cl.char from pupil as pup join class cl on pup.class_id = cl.id;
select pup.name as Имя, cl.number as Номер, cl.char as Буква from pupil as pup join class cl on pup.class_id = cl.id;